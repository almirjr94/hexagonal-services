package com.myorg.car.adapters.outbound.persistence;

import com.myorg.car.adapters.outbound.persistence.entities.CarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoCarRepository extends MongoRepository<CarEntity, String> {}
