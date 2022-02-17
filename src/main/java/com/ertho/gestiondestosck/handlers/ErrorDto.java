package com.ertho.gestiondestosck.handlers;

/*
* L'objet qu'on va renvoyer l'orsqu'on capte une exception
* */

import com.ertho.gestiondestosck.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors = new ArrayList<>();

}
