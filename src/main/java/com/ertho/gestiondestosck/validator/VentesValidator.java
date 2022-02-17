package com.ertho.gestiondestosck.validator;

import com.ertho.gestiondestosck.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto dto){
        List<String> errors = new ArrayList<>();
        if(dto == null || !StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code de la vente");
        }

        return errors;
    }
}
