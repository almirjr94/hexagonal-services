package com.myorg.car.adapters.configuration;

import com.myorg.car.application.ports.CarRepositoryPort;
import com.myorg.car.application.services.CarServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public CarServiceImpl carServiceImpl(CarRepositoryPort carRepositoryPort) {
    return new CarServiceImpl(carRepositoryPort);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
