package com.ertho.gestiondestosck.exception;

/*Exception levee lorsqu'on essaie d'enregistrer quelque chose dans la base de donnees
* Et qu'en passant par les validation et que cet enregistrement n'est pas valide
*
* Exception levee, pour une entitee qui n'est pas valide lors de l'enregistrement
* ou de la mise a jour dans la BD
* */

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException{

    @Getter
    private ErrorCodes errorCode;
    @Getter
    private List<String> errors;

    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors){
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }


}
