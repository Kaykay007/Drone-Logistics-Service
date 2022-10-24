package com.korede.dronefordeliveryservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class FoodPack {
    @Id
    private String code;
    private String menuName;
    private int weight;
    private  String image;
}
