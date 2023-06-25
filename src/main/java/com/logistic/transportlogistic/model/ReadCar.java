package com.logistic.transportlogistic.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadCar {

  @NotNull(message = "Value 'id' cannot be null")
  private Long id;

  @NotNull(message = "Value 'fabricator' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'fabricator' size must be between 1 and 50")
  private String fabricator;

  @NotNull(message = "Value 'model' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'model' size must be between 1 and 50")
  private String model;

  @NotNull(message = "Value 'createDate' cannot be null")
  private String createDate;

}
