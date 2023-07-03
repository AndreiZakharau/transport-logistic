package com.logistic.transportlogistic.testobject;

import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.domain.Transport;
import com.logistic.transportlogistic.model.ComponentModel;
import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.DetailModel;
import com.logistic.transportlogistic.model.ReadCar;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.model.ReadDetail;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.model.TransportModel;
import java.time.LocalDate;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestModel {

  public static Car getCar() {
    return Car.builder()
        .id(2L)
        .fabricator("Volvo")
        .model("500")
        .createDate(LocalDate.parse("2021-12-03"))
        .build();
  }

  public static Detail getDetail() {
    return Detail.builder()
        .id(45L)
        .typeDetail(Component.builder().id(12L).build())
        .number("456UF677HG")
        .transport(Transport.builder().id(13L).build())
        .build();
  }

  public static Component getComponent() {
    return Component.builder()
        .id(12L)
        .type("engine")
        .details(getListDetail()).build();
  }

  public static List<Detail> getListDetail() {
    Detail detail1 = Detail.builder().id(33L).number("467567GHJ56657HGg8").build();
    Detail detail2 = Detail.builder().id(43L).number("ew427GHJ56657HGg8").build();
    Detail detail3 = Detail.builder().id(37L).number("467567GHJ5hHJG676gj$%").build();

    return List.of(detail1, detail2, detail3);
  }

  public static List<ReadDetail> getListReadDetail() {
    ReadDetail detail1 = ReadDetail.builder().id(33L).number("467567GHJ56657HGg8").build();
    ReadDetail detail2 = ReadDetail.builder().id(43L).number("ew427GHJ56657HGg8").build();
    ReadDetail detail3 = ReadDetail.builder().id(37L).number("467567GHJ5hHJG676gj$%").build();

    return List.of(detail1, detail2, detail3);
  }

  public static List<DetailModel> getListModelDetail() {
    DetailModel detail1 = new DetailModel(33, "467567GHJ56657HGg8");
    DetailModel detail2 = new DetailModel(43, "ew427GHJ56657HGg8");
    DetailModel detail3 = new DetailModel(37, "467567GHJ5hHJG676gj$%");

    return List.of(detail1, detail2, detail3);
  }

  public static Transport getTransport() {
    return Transport.builder()
        .id(13L)
        .vin("VYT6785HG787")
        .registryNumber("AT567799")
        .details(getListDetail())
        .car(getCar())
        .driverId(66L).build();
  }

  public static ReadCar getReadCar() {
    return ReadCar.builder()
        .id(2L)
        .fabricator("Volvo")
        .model("500")
        .createDate("2021-12-03")
        .build();
  }

  public static CreateCar getCreateCar() {
    return CreateCar.builder()
        .fabricator("Volvo")
        .model("500")
        .createDate("2021-12-03").build();
  }

  public static CreateComponent getCreateComponent() {
    return CreateComponent.builder()
        .type("engine")
        .build();
  }

  public static ReadComponent getReadComponent() {
    return ReadComponent.builder()
        .id(12L)
        .type("engine")
        .details(getListModelDetail()).build();
  }

  public static CreateDetail getCreateDetail() {
    return CreateDetail.builder()
        .typeDetail(ReadComponent.builder().id(12L).type("engine").details(getListModelDetail()).build())
        .number("456UF677HG")
        .build();
  }

  public static ReadDetail getReadDetail() {
    return ReadDetail.builder()
        .id(45L)
        .typeDetail(new ComponentModel(12, "engine"))
        .number("456UF677HG")
        .transport(new TransportModel(13, "VYT6785HG787", "AT567799", getListReadDetail()))
        .build();
  }

  public static CreateTransport getCreateTransport() {
    return CreateTransport.builder()
        .vin("VYT6785HG787")
        .registryNumber("AT567799")
        .build();
  }

  public static ReadTransport getReadTransport() {
    return ReadTransport.builder()
        .id(13L)
        .vin("VYT6785HG787")
        .registryNumber("AT567799")
        .details(getListModelDetail())
        .car(getReadCar())
        .driverId(66L).build();
  }
}
