package com.logistic.transportlogistic.model;

import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.domain.Detail;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
public class ReadTransport {

  @NotNull
  private Long id;
  private String vin;
  private String registryNumber;
  private Car car;
  private List<Detail> details;
  private Long driverId;

}
