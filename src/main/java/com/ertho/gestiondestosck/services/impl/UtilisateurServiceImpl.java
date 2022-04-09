package com.ertho.gestiondestosck.services.impl;

import com.ertho.gestiondestosck.dto.ChangerMotDePasseUtilisateurDto;
import com.ertho.gestiondestosck.dto.UtilisateurDto;
import com.ertho.gestiondestosck.exception.EntityNotFoundException;
import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.exception.InvalidEntityException;
import com.ertho.gestiondestosck.exception.InvalidOperationException;
import com.ertho.gestiondestosck.model.Utilisateur;
import com.ertho.gestiondestosck.repository.UtilisateurRepository;
import com.ertho.gestiondestosck.services.UtilisateurService;
import com.ertho.gestiondestosck.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Utilisateur is not valid {}", dto);
            throw new InvalidEntityException("L'untilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(dto))
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id == null){
            log.error("Utilisateur ID is null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID " + id + " n'a pas ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email " + email + " n'a pas ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
        validate(dto);
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
        if(utilisateurOptional.isEmpty()){
            log.warn("Aucun utilisateur n'a ete trouve avec l'ID " +dto.getId());
            throw new EntityNotFoundException("Aucun utilisateur n'a ete trouve avec l'ID " +dto.getId(), ErrorCodes.UTILISATEUR_NOT_FOUND);
        }

        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setMoteDePasse(dto.getMotDePasse());

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(utilisateur)
        );
    }

    private void validate(ChangerMotDePasseUtilisateurDto dto){
        if (dto == null){
            log.warn("Impossible de modifier le mot de passe avec un Objet null");
            throw new InvalidOperationException("Aucune informtion n'a ete fournie pour changer le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }
        if(dto.getId() == null){
            log.warn("Impossible de modifier le mot de passe avec un ID null");
            throw new InvalidOperationException("ID utilisateur null:: impossible de modifier le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

        if(!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse())){
            log.warn("Impossible de modifier le mot de passe avec un mot de passe vide");
            throw new InvalidOperationException("Mot de passe utilisateur null:: impossible de modifier le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

        if(dto.getMotDePasse().equals(dto.getConfirmMotDePasse())){
            log.warn("Impossible de modifier le mot de passe avec deux mot de passe différent");
            throw new InvalidOperationException("Mots de passe utilisateur non conformes:: impossible de modifier le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }
    }
}
