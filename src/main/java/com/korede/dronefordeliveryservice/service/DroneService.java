package com.korede.dronefordeliveryservice.service;

import com.korede.dronefordeliveryservice.model.Drone;
import com.korede.dronefordeliveryservice.responses.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DroneService {
    Drone findDroneById(String serialNumber);
    List<Drone> getAllDrones();
    RegisterResponse createDrone(Drone drone);
    AvailableDroneResponse getAvailableDrones(List<Drone> allDroneLists);
    BatteryLevelResponse checkBatteryLevel (String serialNumber);
    LoadDroneResponse loadFoodPack(String droneSerialCode, String foodPackCode);
    LoadedFoodPackResponse loadedFoodPackForADrone(String serialNumber);
    void periodCheckForBatteryHealth(List<Drone> drones);
}


//}

