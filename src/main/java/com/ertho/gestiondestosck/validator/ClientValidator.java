package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.ArticleDto;
import com.ertho.gestiondestosck.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuilez renseigner le nom du client");
            errors.add("Veuilez renseigner le prenom du client");
            errors.add("Veuilez renseigner le mail du client");
            errors.add("Veuilez renseigner le numero de telephone du client");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuilez renseigner le nom du client");
        }
        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("Veuilez renseigner le prenom du client");
        }
        if(!StringUtils.hasLength(dto.getMail())){
            errors.add("Veuilez renseigner le mail du client");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuilez renseigner le numero de telephone du client");
        }
        errors.addAll(AdresseValidator.validate(dto.getAdresse()));

    return errors;
    }

}
