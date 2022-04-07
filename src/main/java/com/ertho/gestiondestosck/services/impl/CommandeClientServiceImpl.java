package com.ertho.gestiondestosck.services.impl;

import com.ertho.gestiondestosck.dto.ClientDto;
import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.dto.LigneCommandeClientDto;
import com.ertho.gestiondestosck.exception.EntityNotFoundException;
import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.exception.InvalidEntityException;
import com.ertho.gestiondestosck.exception.InvalidOperationException;
import com.ertho.gestiondestosck.model.*;
import com.ertho.gestiondestosck.repository.ArticleRepository;
import com.ertho.gestiondestosck.repository.ClientRepository;
import com.ertho.gestiondestosck.repository.CommandeClientRepository;
import com.ertho.gestiondestosck.repository.LigneCommandeClientRepository;
import com.ertho.gestiondestosck.services.CommandeClientService;
import com.ertho.gestiondestosck.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.dnd.InvalidDnDOperationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     ClientRepository clientRepository, ArticleRepository articleRepository){
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Commande Client n'est pas valide");
            throw new InvalidEntityException("La Commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        if(dto.getId() != null && dto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifeir la commande lorqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + dto.getClient().getId() + " n'a pas ete trouve dans la BD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                if(ligneCommandeClientDto.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " + ligneCommandeClientDto.getArticle().getId() + " n'existe pas");
                    } else {
                        articleErrors.add("Inmpossible d'enregistrer une commande avec un article NULL");
                    }
                }
            });
        }

        if(articleErrors.isEmpty()){
            log.warn("Article ID {} was not found in the DB ");
            throw new InvalidEntityException("Article n'existe pas dans la BD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if(dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommandeClientDto);
                ligneCommandeClient.setCommandeClient(saveCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(saveCmdClt);
    }


    @Override
    public CommandeClientDto findById(Integer id) {
        if(id == null){
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commmande client n'a ete trouvee avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande Client CODE is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande client ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }



    @Override
    public CommandeClientDto updateEtatCommande(Integer id, EtatCommande etatCommande) {
        if(StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("L'etat de la commande client is NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = findById(id);

        if(commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifeir la commande lorqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        commandeClientDto.setEtatCommande(etatCommande);

        return CommandeClientDto.fromEntity
                (commandeClientRepository.save
                        (CommandeClientDto.toEntity(commandeClientDto)
        ));
    }

    @Override
    public CommandeClientDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

        if(idCommande == null){
            log.error("L'ID commande est NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        if(idLigneCommande == null){
            log.error("L'ID de la ligne commande est NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande nulle",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        if(quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0){
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite nulle ou zero ",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifier la commande lorqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);

        if(ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucune Commande client n'a ete trouve avec l'ID " + idLigneCommande, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        if(idCommande == null){
            log.error("L'ID de la ligne commande est NULL");
            throw new InvalidOperationException("Impossible de modifier le client avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        if(idClient == null){
            log.error("L'ID client est NULL");
            throw new InvalidOperationException("Impossible de modifier le client avec un idClient null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifier la commande lorqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucun client n'a ete trouve avec l'ID " + idClient, ErrorCodes.CLIENT_NOT_FOUND);
        }

        commandeClientDto.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto))
        );
    }



}
