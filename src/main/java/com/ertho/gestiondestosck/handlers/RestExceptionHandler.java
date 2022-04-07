package com.ertho.gestiondestosck.handlers;

import com.ertho.gestiondestosck.exception.EntityNotFoundException;
import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

/**
 * L' ResponseEntityExceptionHandler", c'est pour utiliser son modele pour la gestion
 * des exceptions
 * L'annotation @RestControllerAdvice, pour eviter de mettre @ResponseBody a chaque methode
 */


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *  Lorqu'on leve une Exception dans l'application avec EntityNotFoundException,
     *  elle sera catchee par le RestControllerAdvice, et on va aller directement a
     *   cette methode et implementee la logique qu'on veut
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerException(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    //TODO Handler InvalidOperationException

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handlerException(BadCredentialsException exception, WebRequest webRequest){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(ErrorCodes.BAD_CREDENTIALS)
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et/ou mot de passe incorrecte"))
                .build();

        return new ResponseEntity<>(errorDto, badRequest);

    }
}
