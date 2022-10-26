package com.korede.dronefordeliveryservice.controller;

import com.korede.dronefordeliveryservice.model.FoodPack;
import com.korede.dronefordeliveryservice.service.FoodPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/foodpack")
public class FoodPackController {
    private final FoodPackService foodPackService;

    @Autowired
    public FoodPackController( FoodPackService foodPackService){
        this.foodPackService= foodPackService;
    }
    @GetMapping(value ="/addfoodpack")
    public FoodPack addFoodPack (@RequestBody FoodPack foodPack){
        return foodPackService.createFoodPack(foodPack);
    }
}

