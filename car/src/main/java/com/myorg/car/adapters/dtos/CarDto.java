package com.myorg.car.adapters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
  private String name;
  private String model;
  private String brand;
  private String chassisNumber;
  private String licensePlate;
  private Integer yearModel;
  private Integer yearCreate;
}
