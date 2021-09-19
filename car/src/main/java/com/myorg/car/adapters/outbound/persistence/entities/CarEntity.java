package com.myorg.car.adapters.outbound.persistence.entities;

import com.myorg.car.application.domain.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "car")
public class CarEntity {

  @Id private String id;
  private String name;
  private String model;
  private String brand;
  private String chassisNumber;
  private String licensePlate;
  private Integer yearModel;
  private Integer yearCreate;

  private Status status;

  @CreatedDate private LocalDateTime createAt;

  @LastModifiedDate private LocalDateTime lastModifiedDate;
}
