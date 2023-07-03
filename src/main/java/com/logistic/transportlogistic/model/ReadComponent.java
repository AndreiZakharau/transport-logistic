package com.logistic.transportlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Component display model")
public class ReadComponent {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  private String type;
  private List<DetailModel> details;
}
