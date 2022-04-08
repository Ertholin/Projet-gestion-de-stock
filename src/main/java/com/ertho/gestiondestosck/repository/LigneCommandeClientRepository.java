package com.ertho.gestiondestosck.repository;

import com.ertho.gestiondestosck.model.LigneCommandeClient;
import com.ertho.gestiondestosck.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

    List<LigneCommandeClient> findAllByCommandeClientId(Integer idCommande);

    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}
