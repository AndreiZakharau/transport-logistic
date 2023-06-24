package com.logistic.transportlogistic.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
public class Transport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 1, max = 50)
  @Column(unique = true)
  private String vin;

  @NotNull
  @Size(min = 1, max = 30)
  @Column(unique = true)
  private String registryNumber;


  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Car car;

  @Builder.Default
  @OneToMany(mappedBy = "transportId")
  private List<Detail> details = new ArrayList<>();

  private Long driverId;

}
