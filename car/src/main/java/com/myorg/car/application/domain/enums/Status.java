package com.myorg.car.application.domain.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Status {
  ACTIVATED,
  DEACTIVATED,
  RESERVED,
  RENTED;

  public static Status toEnum(String name) {
    for (Status value : values()) {
      if (value.name().equalsIgnoreCase(name)) {
        return value;
      }
    }
    String status = Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "));
    throw new IllegalArgumentException(
        String.format("Status %s not valid. Can be used [%s]", name, status));
  }
}
