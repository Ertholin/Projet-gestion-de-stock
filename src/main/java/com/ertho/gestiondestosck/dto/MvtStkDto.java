package com.ertho.gestiondestosck.dto;

import com.ertho.gestiondestosck.model.MvtStk;
import com.ertho.gestiondestosck.model.SourceMvtStk;
import com.ertho.gestiondestosck.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private ArticleDto article;
    private TypeMvtStk typeMvt;

    private SourceMvtStk sourceMvt;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if (mvtStk == null) {
            return null;
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .sourceMvt(mvtStk.getSourceMvt())
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto){
        if(mvtStkDto == null){
            return null;
        }
        MvtStk mvtStk = new MvtStk();
            mvtStk.setId(mvtStkDto.getId());
            mvtStk.setDateMvt(mvtStkDto.getDateMvt());
            mvtStk.setQuantite(mvtStkDto.getQuantite());
            mvtStk.setSourceMvt(mvtStkDto.getSourceMvt());
            mvtStk.setIdEntreprise(mvtStk.getIdEntreprise());
        return mvtStk;
    }

}
