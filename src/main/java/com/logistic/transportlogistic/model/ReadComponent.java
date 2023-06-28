package com.logistic.transportlogistic.model;

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
public class ReadComponent {

  @NotNull
  private Long id;
  @NotNull
  private String type;
  @NotNull
  private List<ReadDetail> details;
}
