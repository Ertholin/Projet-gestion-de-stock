package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    
    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner l'email de l'utilisateur");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }
        
        if(!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }
        if(!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }
        if(!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        }
        if(!StringUtils.hasLength(utilisateurDto.getMoteDePasse())){
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }
        if(utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veuillez renseigner la date naissance de l'utilisateur");
        }
        errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));
        return errors;
    }
}
