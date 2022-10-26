package com.korede.dronefordeliveryservice.serviceImpl;

import com.korede.dronefordeliveryservice.exception.FoodPackNotFoundException;
import com.korede.dronefordeliveryservice.exception.FoodPackNotSavedException;
import com.korede.dronefordeliveryservice.model.FoodPack;
import com.korede.dronefordeliveryservice.repository.FoodPackRepository;
import com.korede.dronefordeliveryservice.service.FoodPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class FoodPackServiceImpl implements FoodPackService {

    private final FoodPackRepository foodPackRepository;
@Autowired
    public FoodPackServiceImpl(FoodPackRepository foodPackRepository) {
        this.foodPackRepository = foodPackRepository;
    }

    @Override
    public FoodPack createFoodPack(FoodPack foodPack) {
    if (validateName(foodPack.getMenuName())){
        if (validateCode(foodPack.getCode())){
            foodPackRepository.save(foodPack);
        }else {
            throw new FoodPackNotSavedException(foodPack.getCode() + "is an invalid code format");
        }
    }else{
        throw new FoodPackNotSavedException(foodPack.getMenuName() + "is an invalid name format");
    }
        return foodPack;
    }


    public List<FoodPack> getFoodPack() {
        return foodPackRepository.findAll();
    }


    public FoodPack findFoodPackByCode(String code) {
        return foodPackRepository.findById(code)
                .orElseThrow(() -> new FoodPackNotFoundException(code));
    }


    public boolean validateCode(String code) {
    Pattern pattern = Pattern.compile("^\\w*$" , Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(code);
    return matcher.find();
    }

    public boolean validateName(String name) {
        Pattern pattern = Pattern.compile ("^[A-Za-z\\d_-]*$" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
