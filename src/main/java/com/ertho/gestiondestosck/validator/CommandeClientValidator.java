package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto dto){

        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code de la commande");
        }
        if(dto.getDateCommande() == null){
            errors.add("Veuillez renseigner la date de la commande");
        }

        return errors;
    }
}
