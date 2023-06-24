package com.logistic.transportlogistic.api.model;

import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.domain.Transport;
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

  private Long id;
  private String number;
  private Component typeDetail;
  private Transport transportId;

}
