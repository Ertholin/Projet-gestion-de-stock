package com.ertho.gestiondestosck.dto;

import com.ertho.gestiondestosck.model.LigneVente;
import com.ertho.gestiondestosck.model.Ventes;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
public class VentesDto {

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;

    public static VentesDto fromEntity(Ventes ventes){
        if(ventes == null){
            return null;
        }
        return VentesDto.builder()
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .idEntreprise(ventes.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto){
        if(ventesDto == null){
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setCode(ventesDto.getCode());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());
        return ventes;
    }


}
