package com.logistic.transportlogistic.util;

import com.logistic.transportlogistic.api.pagination.SortParamsContext;
import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.domain.Transport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class ColumnValidator {

  private static final List<String> TRANSPORT_FIELD_NAMES = new ArrayList<>();

  private static final List<String> CAR_FIELD_NAMES = new ArrayList<>();

  private static final List<String> COMPONENT_FIELD_NAMES = new ArrayList<>();

  private static final List<String> DETAIL_FIELD_NAMES = new ArrayList<>();

  private static final List<String> ORDER_TYPES = Arrays.asList("ASC", "DESC");

  static {
    Arrays.stream(Transport.class.getDeclaredFields()).
        forEach(field -> TRANSPORT_FIELD_NAMES.add(field.getName()));
  }

  static {
    Arrays.stream(Car.class.getDeclaredFields()).
        forEach(field -> CAR_FIELD_NAMES.add(field.getName()));
  }

  static {
    Arrays.stream(com.logistic.transportlogistic.domain.Component.class.getDeclaredFields()).
        forEach(field -> COMPONENT_FIELD_NAMES.add(field.getName()));
  }

  static {
    Arrays.stream(Detail.class.getDeclaredFields()).
        forEach(field -> DETAIL_FIELD_NAMES.add(field.getName()));
  }

  public boolean transportColumnsValid(SortParamsContext item) {

    return new HashSet<>(TRANSPORT_FIELD_NAMES).containsAll(item.sortColumns())
        && item.orderTypes().stream().allMatch(order -> ORDER_TYPES.contains(order.toUpperCase(
        Locale.ROOT)));
  }

  public boolean carColumnsValid(SortParamsContext item) {

    return new HashSet<>(CAR_FIELD_NAMES).containsAll(item.sortColumns())
        && item.orderTypes().stream()
        .allMatch(order -> ORDER_TYPES.contains(order.toUpperCase(Locale.ROOT)));
  }

  public boolean componentColumnsValid(SortParamsContext item) {

    return new HashSet<>(COMPONENT_FIELD_NAMES).containsAll(item.sortColumns())
        && item.orderTypes().stream().allMatch(order -> ORDER_TYPES.contains(order.toUpperCase(
        Locale.ROOT)));
  }

  public boolean detailColumnsValid(SortParamsContext item) {

    return new HashSet<>(DETAIL_FIELD_NAMES).containsAll(item.sortColumns())
        && item.orderTypes().stream().allMatch(order -> ORDER_TYPES.contains(order.toUpperCase(
        Locale.ROOT)));
  }
}
