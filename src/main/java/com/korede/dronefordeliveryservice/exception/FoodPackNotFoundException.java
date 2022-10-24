package com.korede.dronefordeliveryservice.exception;

public class FoodPackNotFoundException extends RuntimeException {
    public FoodPackNotFoundException(String foodPackId) {
        super("Food pack with id " + foodPackId + " not found");
    }
}

