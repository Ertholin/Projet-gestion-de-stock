package com.ertho.gestiondestosck.repository;

import com.ertho.gestiondestosck.model.LigneCommandeClient;
import com.ertho.gestiondestosck.model.LigneCommandeFournisseur;
import com.ertho.gestiondestosck.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {
    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);
}
