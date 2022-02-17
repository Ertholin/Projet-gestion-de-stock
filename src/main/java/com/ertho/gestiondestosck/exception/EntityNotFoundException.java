package com.ertho.gestiondestosck.exception;

/*
Exception Generique
Par exemple, si on cherche un article par son code, et qu'il est introuvable,
il faut renvoyer une exception de type EntityNotFoundException
Exception levee lorsqu'une entitee n'existe pas dans la BD
*/

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{

    @Getter
    private ErrorCodes errorCode;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String message, ErrorCodes errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
