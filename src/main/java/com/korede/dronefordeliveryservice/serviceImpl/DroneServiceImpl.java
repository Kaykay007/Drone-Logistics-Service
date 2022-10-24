package com.korede.dronefordeliveryservice.serviceImpl;

import com.korede.dronefordeliveryservice.enumeration.State;
import com.korede.dronefordeliveryservice.exception.DroneNotFoundException;
import com.korede.dronefordeliveryservice.exception.ExcessWeightException;
import com.korede.dronefordeliveryservice.exception.FoodPackNotFoundException;
import com.korede.dronefordeliveryservice.model.Drone;
import com.korede.dronefordeliveryservice.model.FoodPack;
import com.korede.dronefordeliveryservice.repository.DroneRepository;
import com.korede.dronefordeliveryservice.repository.FoodPackRepository;
import com.korede.dronefordeliveryservice.responses.*;
import com.korede.dronefordeliveryservice.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final FoodPackRepository foodPackRepository;
    static List<FoodPack> foodPacks = new ArrayList<>();
    static Map<FoodPack, String> foodPacksList = new HashMap<>();

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, FoodPackRepository foodPackRepository) {
        this.droneRepository = droneRepository;
        this.foodPackRepository = foodPackRepository;
    }

    public Drone findDroneById(String serialNumber) {
        Drone searchedDrone = droneRepository.findById(serialNumber).orElseThrow(() -> new DroneNotFoundException(serialNumber));
        return searchedDrone;
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public RegisterResponse createDrone(Drone drone) {
        droneRepository.save(drone);
        return new RegisterResponse("success", LocalDateTime.now(), drone.getSerialNumber());
    }

    @Override
    public AvailableDroneResponse getAvailableDrones(List<Drone> allDroneLists) {
        List<Drone> availableDrones = allDroneLists.stream().filter(drone -> drone.getBatteryCapacity() >=25 && drone.getStateOfDuty() == State.IDLE)
                .toList();
        return new AvailableDroneResponse("success", LocalDateTime.now(), availableDrones);
    }

    @Override
    public LoadDroneResponse loadFoodPack(String droneSerialCode, String foodPackCode) {
        LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
        Drone drone = findDroneById(droneSerialCode);
        FoodPack foodPack = foodPackRepository.findById(foodPackCode)
                .orElseThrow(() -> new FoodPackNotFoundException(foodPackCode));
        List<FoodPack> foodPacks = new ArrayList<>();
        int totalLoadedDroneWeight= drone.getFoodPack().stream().map(FoodPack::getWeight)
                .toList().stream().reduce(0, Integer::sum);
        if(drone.getStateOfDuty() == State.IDLE){
            if(drone.getBatteryCapacity() >= 25){
                if(totalLoadedDroneWeight <= 500){
                    if(totalLoadedDroneWeight + foodPack.getWeight() <=500){
                        drone.setStateOfDuty(State.LOADING);
                        if(!drone.getFoodPack().contains(foodPack)){
                            foodPacksList.put(foodPack, droneSerialCode);
                            foodPacksList.forEach((key, value) ->{
                                if(value.equalsIgnoreCase(droneSerialCode)){
                                    drone.getFoodPack().add(key);
                                }
                            });
                            drone.setStateOfDuty(State.LOADED);
                            loadDroneResponse = new LoadDroneResponse("success", LocalDateTime.now() , droneSerialCode ,drone.getFoodPack());

                        }
                    }else{
                        throw new ExcessWeightException("The food pack weight has exceeded the accepted weight");
                    }
                }else{
                    throw new ExcessWeightException("yOu cannot add more than 500gm ti the Drone");
                }
            }
        }

        return loadDroneResponse;
    }

    @Override
    public LoadedFoodPackResponse loadedFoodPackForADrone(String serialNumber) {
        Drone drone = findDroneById(serialNumber);
        foodPacksList.forEach((key, value) ->{
          if (value.equalsIgnoreCase(serialNumber)){
              drone.getFoodPack().add(key);
          }
        });
        return new LoadedFoodPackResponse("success" , LocalDateTime.now() , drone.getSerialNumber(), drone.getFoodPack());
    }


    @Override
    public void periodCheckForBatteryHealth(List<Drone> drones) {
        Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drones.forEach(drone -> logger.info("Battery Level for this .." +
                        drone.getSerialNumber() +" is " + drone.getBatteryCapacity()));
            }
        } , 2000, 200000);

    }
    @Override
    public BatteryLevelResponse checkBatteryLevel(String serialNumber) {
        int batteryLevel = findDroneById(serialNumber).getBatteryCapacity();
        return new BatteryLevelResponse("success" , LocalDateTime.now() , serialNumber , batteryLevel);
    }

}


