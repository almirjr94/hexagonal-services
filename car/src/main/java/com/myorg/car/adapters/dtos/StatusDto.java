package com.myorg.car.adapters.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StatusDto {

    @NotBlank
    private String status;
}
