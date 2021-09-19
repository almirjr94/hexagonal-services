package com.myorg.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class CarApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarApplication.class, args);
  }
}
