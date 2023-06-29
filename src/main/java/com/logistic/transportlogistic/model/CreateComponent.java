package com.logistic.transportlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "Component creation model")
public class CreateComponent {

  @NotNull(message = "Value 'type' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'type' size must be between 1 and 50")
  private String type;
}
