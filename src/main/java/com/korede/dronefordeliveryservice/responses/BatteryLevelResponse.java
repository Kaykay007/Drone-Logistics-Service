package com.korede.dronefordeliveryservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryLevelResponse {
    private String message;
    private LocalDateTime timestamp;
    private String serialNumber;
    private int batteryCapacity;
}
