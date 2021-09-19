package com.myorg.car.adapters.outbound.persistence;

import com.myorg.car.adapters.outbound.persistence.entities.CarEntity;
import com.myorg.car.application.domain.Car;
import com.myorg.car.application.domain.PageInfo;
import com.myorg.car.application.ports.CarRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MongoCarRepository implements CarRepositoryPort {

  private final ModelMapper modelMapper;
  private final SpringDataMongoCarRepository carRepository;

  @Override
  public Car save(Car car) {
    CarEntity save = carRepository.save(modelMapper.map(car, CarEntity.class));
    return modelMapper.map(save, Car.class);
  }

  @Override
  public List<Car> findAll(PageInfo pageInfo) {
    PageRequest pageRequest =
        PageRequest.of(
            pageInfo.getPage(),
            pageInfo.getSize(),
            Sort.Direction.fromString(pageInfo.getSort()),
            pageInfo.getOrderBy());

    return carRepository.findAll(pageRequest).stream()
        .map(entity -> modelMapper.map(entity, Car.class))
        .toList();
  }

  @Override
  public Optional<Car> findById(String carId) {
    Optional<CarEntity> carEntity = carRepository.findById(carId);
    return carEntity.map(entity -> modelMapper.map(entity, Car.class));
  }

  @Override
  public long count() {
    return carRepository.count();
  }
}
