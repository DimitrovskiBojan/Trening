package com.example.trening.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DietPlanAlreadyBought extends RuntimeException{

    public DietPlanAlreadyBought(long id){
        super(String.format("You have already bought this plan"));
    }
}
