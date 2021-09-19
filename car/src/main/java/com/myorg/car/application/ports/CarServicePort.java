package com.myorg.car.application.ports;

import com.myorg.car.application.domain.Car;
import com.myorg.car.application.domain.PageInfo;
import com.myorg.car.application.domain.enums.Status;
import com.myorg.car.application.exceptions.ObjectNotFoundException;

import java.util.List;

public interface CarServicePort {

  List<Car> findAll(PageInfo pageInfo);

  Car findById(String carEmail) throws ObjectNotFoundException;

  Car save(Car car);

  long count();

  Car updateStatus(String carId, Status status);
}
