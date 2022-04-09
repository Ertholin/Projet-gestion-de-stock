package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.ClientDto;
import com.ertho.gestiondestosck.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();
        if(fournisseurDto == null){
            errors.add("Veuilez renseigner le nom du fournisseur");
            errors.add("Veuilez renseigner le prenom du fournisseur");
            errors.add("Veuilez renseigner le mail du fournisseur");
            errors.add("Veuilez renseigner le numero de telephone du fournisseur");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

        if(!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Veuilez renseigner le nom du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Veuilez renseigner le prenom du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getMail())){
            errors.add("Veuilez renseigner le mail du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Veuilez renseigner le numero de telephone du fournisseur");
        }
        errors.addAll(AdresseValidator.validate(fournisseurDto.getAdresse()));

        return errors;
    }

}
