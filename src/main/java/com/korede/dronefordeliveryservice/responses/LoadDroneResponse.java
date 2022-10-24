package com.korede.dronefordeliveryservice.responses;

import com.korede.dronefordeliveryservice.model.FoodPack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDroneResponse {
    private String message;
    private LocalDateTime timestamp;
    private String SerialNumber;
    private List<FoodPack> foodPacks;

}
