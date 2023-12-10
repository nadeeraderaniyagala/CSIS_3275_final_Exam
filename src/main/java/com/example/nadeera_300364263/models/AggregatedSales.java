package com.example.nadeera_300364263.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedSales {

    private String name;
    private double washingMachine;
    private double refrigerator;
    private double musicSystem;

}
