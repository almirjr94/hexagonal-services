package com.myorg.car.application.domain;

import com.myorg.car.application.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

  private String id;
  private String name;
  private String model;
  private String brand;
  private String chassisNumber;
  private String licensePlate;
  private Integer yearModel;
  private Integer yearCreate;

  private Status status;

  private LocalDateTime createAt;
  private LocalDateTime lastModifiedDate;
}
