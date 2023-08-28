package com.example.trening.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException  extends RuntimeException{

    public ShoppingCartNotFoundException(long id){
        super(String.format("Shopping cart NOT FOUND"));
    }

}
