package com.logistic.transportlogistic.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadCar {

  private Long id;
  private String fabricator;
  private String model;
  private String createDate;
}
