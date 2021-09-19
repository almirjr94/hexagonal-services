package com.myorg.car.application.services;

import com.myorg.car.application.domain.Car;
import com.myorg.car.application.domain.PageInfo;
import com.myorg.car.application.domain.enums.Status;
import com.myorg.car.application.exceptions.ObjectNotFoundException;
import com.myorg.car.application.ports.CarRepositoryPort;
import com.myorg.car.application.ports.CarServicePort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.lang.String.format;

@Slf4j
public record CarServiceImpl(
        CarRepositoryPort carRepositoryPort) implements CarServicePort {

  @Override
  public List<Car> findAll(PageInfo pageInfo) {
    return carRepositoryPort.findAll(pageInfo);
  }

  @Override
  public Car findById(String carId) {
    return carRepositoryPort.findById(carId)
            .orElseThrow(() -> new ObjectNotFoundException(format("Car with ID %s not found.",carId)));
  }

  @Override
  public Car save(Car car) {
    return carRepositoryPort.save(car);
  }


  @Override
  public long count() {
    return carRepositoryPort.count();
  }

  @Override
  public Car updateStatus(String carId, Status status) {
    Car car = findById(carId);
    car.setStatus(status);
    return save(car);
  }
}
