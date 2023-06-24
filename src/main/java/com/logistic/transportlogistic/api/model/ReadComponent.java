package com.logistic.transportlogistic.api.model;

import com.logistic.transportlogistic.domain.Detail;
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

  private Long id;
  private String type;
  private List<Detail> details;
}
