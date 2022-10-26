package com.korede.dronefordeliveryservice.service;

import com.korede.dronefordeliveryservice.model.FoodPack;

import java.util.List;

public interface FoodPackService {
    FoodPack createFoodPack(FoodPack foodPack);
    List<FoodPack> getFoodPack();
    FoodPack findFoodPackByCode(String code);
    boolean validateCode(String code);

    boolean validateName(String name);
}
