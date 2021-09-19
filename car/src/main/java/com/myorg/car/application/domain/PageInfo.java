package com.myorg.car.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo {

  private int page;
  private int size;
  private String sort;
  private String orderBy;
}
