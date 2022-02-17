package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto dto){

        List<String> errors = new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigne le nom de l'entreprise");
            errors.add("Veuillez renseigne la descriptipon de l'entreprise");
            errors.add("Veuillez renseigne le code ficale de l'entreprise");
            errors.add("Veuillez renseigne l'email de l'entreprise");
            errors.add("Veuillez renseigne le telephone de l'entreprise");
            errors.add("Le champ 'Adresse 1' est obligatoire");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigne le nom de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getDescription())){
            errors.add("Veuillez renseigne la descriptipon de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getCodeFiscal())){
            errors.add("Veuillez renseigne le code ficale de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("Veuillez renseigne l'email de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigne le telephone de l'entreprise");
        }
        if(dto.getAdresse() == null){
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        }else {
            if (!StringUtils.hasLength(dto.getAdresse().getAdresse1())) {
                errors.add("Le champ 'Adresse 1' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getVille())) {
                errors.add("Le champ 'Ville' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getCodePostal())) {
                errors.add("Le champ 'Code Postal' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getPays())) {
                errors.add("Le champ 'Pays' est obligatoire");
            }
        }
        return errors;

    }
}
