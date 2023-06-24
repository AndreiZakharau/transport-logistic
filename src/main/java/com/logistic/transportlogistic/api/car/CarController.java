package com.logistic.transportlogistic.api.car;

import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
import com.logistic.transportlogistic.service.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController implements CarApi {

  private final CarServiceImpl carService;

  @Override
  public ReadCar addCar(CreateCar car) {

    return carService.add(car);
  }

  @Override
  public String deleteCar(long id) {

    return carService.delete(id);
  }

  @Override
  public ReadCar updateCar(CreateCar car, long id) {

    return carService.update(car, id);
  }

  @Override
  public ReadCar getCarById(long id) {

    return carService.get(id);
  }

  @Override
  public Page<ReadCar> findAllCar(Integer page, Integer size) {
    return carService.getAll(page,size);
  }

}
