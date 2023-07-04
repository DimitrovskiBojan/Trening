package com.example.trening.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DietPlanNotFoundException extends RuntimeException {

    public DietPlanNotFoundException(long id){
        super(String.format("DietPlan NOT FOUND"));
    }

}
