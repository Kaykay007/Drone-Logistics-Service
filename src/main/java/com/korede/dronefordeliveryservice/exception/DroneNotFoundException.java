package com.korede.dronefordeliveryservice.exception;

public class DroneNotFoundException  extends RuntimeException{
    public DroneNotFoundException(String droneId) {
        super("Drone with id " + droneId + " not found");
    }
}
