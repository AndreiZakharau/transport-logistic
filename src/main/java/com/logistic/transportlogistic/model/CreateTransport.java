package com.logistic.transportlogistic.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateTransport {

  @NotNull(message = "Value 'vin' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'vin' size must be between 1 and 50")
  private String vin;

  @NotNull(message = "Value 'registryNumber' cannot be null")
  @Size(min = 1, max = 30, message = "Value 'registryNumber' size must be between 1 and 30")
  private String registryNumber;


}
