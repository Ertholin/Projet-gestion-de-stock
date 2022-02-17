package com.ertho.gestiondestosck.dto;

import com.ertho.gestiondestosck.model.Article;
import com.ertho.gestiondestosck.model.LigneVente;
import com.ertho.gestiondestosck.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;
    private Ventes vente;
    private ArticleDto article;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null) {
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if(ligneVenteDto == null){
            return null;
        }
        LigneVente ligneVente = new LigneVente();
            ligneVente.setId(ligneVenteDto.getId());
            ligneVente.setQuantite(ligneVenteDto.getQuantite());
            ligneVente.setPrixUnitaire(ligneVente.getPrixUnitaire());
            ligneVente.setIdEntreprise(ligneVente.getIdEntreprise());
        return ligneVente;
    }
}
