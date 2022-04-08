package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.controller.api.CommandeFournisseurApi;
import com.ertho.gestiondestosck.dto.CommandeFournisseurDto;
import com.ertho.gestiondestosck.dto.LigneCommandeFournisseurDto;
import com.ertho.gestiondestosck.model.EtatCommande;
import com.ertho.gestiondestosck.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    @Autowired
    private CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return commandeFournisseurService.updateQuantiteCommandee(idCommande, idLigneCommande, quantite);
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return commandeFournisseurService.findByCode(code);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande));
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);
    }
}
