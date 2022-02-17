package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.LigneCommandeClientDto;
import com.ertho.gestiondestosck.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto dto){

        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("La quantite de l'article n'a pas ete renseignee");
            errors.add("Le prix unitaire de l'article n'a pas ete renseigne");
        }

        if(dto.getQuantite() == null){
            errors.add("La quantite de l'article n'a pas ete renseignee");
        }
        if(dto.getPrixUnitaire() == null){
            errors.add("Le prix unitaire de l'article n'a pas ete renseigne");
        }

        return errors;
    }

}
