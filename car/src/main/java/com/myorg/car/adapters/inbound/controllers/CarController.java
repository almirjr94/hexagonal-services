package com.myorg.car.adapters.inbound.controllers;

import com.myorg.car.adapters.dtos.CarDto;
import com.myorg.car.adapters.dtos.StatusDto;
import com.myorg.car.application.domain.Car;
import com.myorg.car.application.domain.PageInfo;
import com.myorg.car.application.domain.enums.Status;
import com.myorg.car.application.ports.CarServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

  private final ModelMapper modelMapper;
  private final CarServicePort carServicePort;

  @PostMapping
  public ResponseEntity<Car> save(@Valid @RequestBody CarDto carDto) {

    Car car = carServicePort.save(modelMapper.map(carDto, Car.class));

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(car.getId())
            .toUri();

    return ResponseEntity.created(uri).body(car);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Car> getById(@PathVariable("id") String id) {
    return ok(carServicePort.findById(id));
  }

  @GetMapping
  public ResponseEntity<Page<Car>> getAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

    PageInfo pageInfo =
        PageInfo.builder().page(page).orderBy(orderBy).size(linesPerPage).sort(direction).build();

    List<Car> cars = carServicePort.findAll(pageInfo);

    return ok(new PageImpl<>(cars, pageRequest, carServicePort.count()));
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<Car> updateStatus(
      @PathVariable String id, @RequestBody @Valid StatusDto status) {

    return ok(carServicePort.updateStatus(id, Status.toEnum(status.getStatus())));
  }
}
