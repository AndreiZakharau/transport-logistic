package com.logistic.transportlogistic.model;

import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.domain.Transport;
import jakarta.validation.constraints.NotNull;
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
public class ReadDetail {

  @NotNull
  private Long id;
  @NotNull
  private String number;
  @NotNull
  private Component typeDetail;
  @NotNull
  private ReadTransport transportId;

}
