package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.controller.api.CommandeClientApi;
import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.model.EtatCommande;
import com.ertho.gestiondestosck.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientControllery implements CommandeClientApi {

    @Autowired
    private CommandeClientService commandeClientService;

    public CommandeClientControllery(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        //return ResponseEntity.ok(commandeClientService.save(dto));
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.updateQuantiteCommandee(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.updateClient(idCommande, idClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
