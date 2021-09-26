package com.myorg.car.adapters.outbound.persistence;

import com.myorg.car.application.domain.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class MongoCarRepositoryTest {

  @Autowired private MongoCarRepository mongoCarRepository;

  public static MongoDBContainer mongoDBContainer =
      new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));

  @BeforeAll
  static void initAll() {
    mongoDBContainer.start();
  }

  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void when_find_returns_optional_empty() {
    Optional<Car> carOptional = mongoCarRepository.findById("teste");
    assertTrue(carOptional.isEmpty());
  }

  @Test
  void when_save_return_car() {
    Car expected = Car.builder().brand("teste").chassisNumber("1234").build();

    Car actual = mongoCarRepository.save(expected);

    assertNotNull(actual);
    assertEquals(expected.getBrand(), actual.getBrand());
    assertEquals(expected.getChassisNumber(), actual.getChassisNumber());
  }

  @Test
  void containerStartsAndPublicPortIsAvailable() {
    assertThatPortIsAvailable(mongoDBContainer);
  }

  private void assertThatPortIsAvailable(MongoDBContainer container) {
    try {
      new Socket(container.getContainerIpAddress(), container.getFirstMappedPort());
    } catch (IOException e) {
      throw new AssertionError(
          "The expected port " + container.getFirstMappedPort() + " is not available!");
    }
  }
}
