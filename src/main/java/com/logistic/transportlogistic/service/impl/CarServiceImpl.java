package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.exception.DataException;
import com.logistic.transportlogistic.mapper.CarMapper;
import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
import com.logistic.transportlogistic.repositorie.CarRepository;
import com.logistic.transportlogistic.service.CarService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarMapper carMapper;

  private final CarRepository repository;


  @Transactional
  @Override
  public ReadCar add(CreateCar createCar) {

    Car car = carMapper.carFromCreateCar(createCar);
    return carMapper.readCarFromCar(repository.save(car));
  }

  @Override
  public String delete(long id) {

    String message = String.format("Car with id = '%s' has been deleted", id);

    if (isPresent(id)) {
      repository.deleteById(id);
      return message;
    } else {
      throw new DataException(String.format("Car with id = '%s' could be found", id));
    }

  }

  @Transactional
  @Override
  public ReadCar update(CreateCar createCar, long id) {

    if (isPresent(id)) {
      Car car = carMapper.carFromCreateCar(createCar);
      car.setId(id);
      car = repository.save(car);
      return carMapper.readCarFromCar(car);
    } else {
      throw new DataException(String.format("Id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadCar get(long id) {

    Optional<Car> car = Optional.of(repository.findById(id)).get();

    return carMapper.readCarFromCar(car.get());
  }

  @Transactional
  @Override
  public Page<ReadCar> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Car> cars = repository.findAll(pageable);
    return cars.map(carMapper::readCarFromCar);
  }


  @Override

  public boolean isPresent(long id) {

    return repository.findById(id).isPresent();
  }
}
