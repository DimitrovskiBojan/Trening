package com.example.trening.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainerTermsNotFoundException extends RuntimeException{
    public TrainerTermsNotFoundException(long id){
        super(String.format("Training term NOT FOUND"));
    }
}
