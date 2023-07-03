package com.logistic.transportlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Detail display model")
public class ReadDetail {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  private String number;
  private ComponentModel typeDetail;
  private TransportModel transport;

}
