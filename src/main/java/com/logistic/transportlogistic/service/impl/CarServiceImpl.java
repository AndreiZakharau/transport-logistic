package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.api.pagination.SortParamsContext;
import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.CarMapper;
import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
import com.logistic.transportlogistic.repositorie.CarRepository;
import com.logistic.transportlogistic.service.CarService;
import com.logistic.transportlogistic.util.ColumnValidator;
import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarMapper carMapper;

  private final CarRepository repository;

  private final ColumnValidator validator;

  @Transactional
  @Override
  public ReadCar add(CreateCar createCar) {

    Car car = carMapper.carFromCreateCar(createCar);
    return carMapper.readCarFromCar(repository.save(car));
  }

  @Override
  public void delete(long id) {

    isPresent(id);
    repository.deleteById(id);
  }

  @Transactional
  @Override
  public ReadCar update(CreateCar createCar, long id) {

    isPresent(id);
    Car car = carMapper.carFromCreateCar(createCar);
    car.setId(id);
    car = repository.save(car);
    return carMapper.readCarFromCar(car);
  }

  @Transactional
  @Override
  public ReadCar get(long id) {

    Car car = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return carMapper.readCarFromCar(car);
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

    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new ResourceNotFoundException(String.format("Car with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public Page<ReadCar> getAllBySort(List<String> sortColumns, List<String> orderTypes, int page,
      int size) {

    Sort sort = getSort(sortColumns, orderTypes);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Car> cars = repository.findAll(pageable);
    return cars.map(carMapper::readCarFromCar);
  }

  private Sort getSort(List<String> sortColumns, List<String> orderTypes) {

    List<String> typesList = Arrays.asList("ASC", "DESC");
    Sort sort = null;
    SortParamsContext sortParameters;
    if (sortColumns != null || !sortColumns.isEmpty()) {
      sortParameters = new SortParamsContext(sortColumns, orderTypes);

      if (!validator.carColumnsValid(sortParameters)) {
        throw new ResourceNotFoundException("Column name isn't correct");
      } else {
        sort = getOrders(sortColumns, orderTypes, typesList, sort, sortParameters);
      }
    }
    return sort;
  }

  static Sort getOrders(List<String> sortColumns, List<String> orderTypes, List<String> typesList,
      Sort sort, SortParamsContext sortParameters) {
    List<String> orderTypesList = sortParameters.orderTypes();
    for (String column : sortColumns) {
      sort = Sort.by(column);
      if (orderTypes.size() > 0 && orderTypes.stream()
          .anyMatch(order -> typesList.contains(order.toUpperCase(
              Locale.ROOT)))) {
        if (orderTypes.get(0).toUpperCase(Locale.ROOT).equals("DESC")) {
          sort = Sort.by(column).descending();
        } else {
          sort = Sort.by(column).ascending();
        }
      }
    }
    return sort;
  }
}
