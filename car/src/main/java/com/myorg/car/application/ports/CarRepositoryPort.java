package com.myorg.car.application.ports;

import com.myorg.car.application.domain.Car;
import com.myorg.car.application.domain.PageInfo;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryPort {

  Car save(Car car);

  List<Car> findAll(PageInfo pageInfo);

  Optional<Car> findById(String carId);

  long count();
}
