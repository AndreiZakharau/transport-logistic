package com.logistic.transportlogistic.model;

import com.logistic.transportlogistic.domain.Component;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Detail creation model")
public class CreateDetail {

  @NotNull(message = "Value 'number' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'number' size must be between 1 and 50")
  private String number;

  @NotNull(message = "Value 'typeDetail' cannot be null")
  private ReadComponent typeDetail;

  private ReadTransport transport;

}
