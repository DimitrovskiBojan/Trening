package com.example.trening.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUsernameOrPasswordException extends RuntimeException{

    public InvalidUsernameOrPasswordException(){
        super(String.format("Trainer NOT FOUND"));
    }

}
