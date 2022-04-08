package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.dto.CommandeFournisseurDto;
import com.ertho.gestiondestosck.dto.LigneCommandeClientDto;
import com.ertho.gestiondestosck.dto.LigneCommandeFournisseurDto;
import com.ertho.gestiondestosck.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto updateEtatCommande(Integer id, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

    // Delete article ==> delete LigneCommandeClient
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

    void delete(Integer id);
}
