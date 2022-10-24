package com.korede.dronefordeliveryservice.responses;

import com.korede.dronefordeliveryservice.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDroneResponse {
    private String message;
    private LocalDateTime timestamp;
    public List<Drone> availableDrones;
}
