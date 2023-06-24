package com.logistic.transportlogistic.mapper;

import com.logistic.transportlogistic.api.model.ReadCar;
import com.logistic.transportlogistic.api.model.CreateCar;
import com.logistic.transportlogistic.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

  CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);

  Car carFromReadCar(ReadCar readCar);

  ReadCar readCarFromCar(Car car);

  Car carFromCreateCar(CreateCar createCar);

  CreateCar createCarFromCar(Car car);
}
