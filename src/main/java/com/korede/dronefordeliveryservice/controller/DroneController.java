package com.korede.dronefordeliveryservice.controller;

import com.korede.dronefordeliveryservice.model.Drone;
import com.korede.dronefordeliveryservice.model.FoodPack;
import com.korede.dronefordeliveryservice.responses.*;
import com.korede.dronefordeliveryservice.service.DroneService;
import com.korede.dronefordeliveryservice.service.FoodPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class DroneController {
    private final DroneService droneService;
    private final FoodPackService foodPackService;

    @Autowired
    public DroneController(DroneService droneService, FoodPackService foodPackService) {
        this.droneService = droneService;
        this.foodPackService = foodPackService;
    }
   @GetMapping(value = "/drones")
    public ResponseEntity<List<Drone>> ListOfDrones(){

        return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
   }
    @GetMapping(value = "/foodies")
    public List<FoodPack> ListOfFoodPacks(){
        return foodPackService.getFoodPack();
    }
    @PostMapping(value = "/register")
    public RegisterResponse registerDrone(@RequestBody Drone drone){
        return droneService.createDrone(drone);
    }

    @GetMapping(value ="/availabledrones")
    public AvailableDroneResponse listOfAvailableDrones(){
        droneService.periodCheckForBatteryHealth(droneService.getAllDrones());
        return droneService.getAvailableDrones(droneService.getAllDrones());
    }

    @GetMapping(value="/drone/{serialNumber}")
    public Drone getDroneById(@PathVariable("serialnumber") String serialNumber){
        return droneService.findDroneById(serialNumber);
    }
    @GetMapping(value ="/{serialNumber}/foodpacks")
    public LoadedFoodPackResponse getFoodPacks(@PathVariable("serialNumber") String serialNumber){
        return droneService.loadedFoodPackForADrone (serialNumber);
    }
    @GetMapping(value = "/{serialNumber}/battery")
    public BatteryLevelResponse getBattery(@PathVariable("serialNumber") String serialNumber){
        return droneService.checkBatteryLevel(serialNumber);
    }
    @GetMapping(value ="/{serialNumber}/load/{foodCode}")
    public LoadDroneResponse loadDrone(@PathVariable(value ="serialNumber") String serialNumber ,
                                       @PathVariable(value = "foodCode") String foodCode){
        return droneService.loadFoodPack(serialNumber, foodCode);
    }

}

