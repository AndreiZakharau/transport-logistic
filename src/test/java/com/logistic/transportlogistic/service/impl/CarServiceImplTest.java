package com.logistic.transportlogistic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.CarMapper;
import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
import com.logistic.transportlogistic.repositorie.CarRepository;
import com.logistic.transportlogistic.testobject.TestModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

  @InjectMocks
  CarServiceImpl carService;

  @Mock
  CarRepository repository;

  @Mock
  CarMapper mapper;


  private final Car car = TestModel.getCar();
  private final ReadCar readCar = TestModel.getReadCar();
  private final CreateCar createCar = TestModel.getCreateCar();


  @Test
  void saveCar() {

    when(mapper.carFromCreateCar(createCar)).thenReturn(car);
    when(repository.save(car)).thenReturn(car);
    when(mapper.readCarFromCar(car)).thenReturn(readCar);

    ReadCar actual = carService.add(createCar);

    assertNotNull(actual);
    assertEquals(actual.getId(), readCar.getId());
    assertEquals(actual.getFabricator(), readCar.getFabricator());
    assertEquals(actual.getModel(), readCar.getModel());
    assertEquals(actual.getCreateDate(), createCar.getCreateDate());

  }

  @Test
  void deleteCarById() {

    long id = 2L;

    when(repository.findById(id)).thenReturn(Optional.of(car));

    carService.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void deleteCarByIdIfNoSuchIdThenThrowResourceNotFoundException() {

    long id = 5L;
    String exceptionMessage = String.format("Car with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> carService.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);

  }

  @Test
  void updateCarById() {

    long id = 2L;
    String carModel = "MAN";
    createCar.setModel(carModel);

    when(repository.findById(id)).thenReturn(Optional.of(car));

    assertNotEquals(car.getModel(), carModel);

    car.setModel(carModel);
    readCar.setModel(carModel);

    when(mapper.carFromCreateCar(createCar)).thenReturn(car);
    when(repository.save(car)).thenReturn(car);
    when(mapper.readCarFromCar(car)).thenReturn(readCar);

    ReadCar actual = carService.update(createCar, id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readCar.getId());
    assertEquals(actual.getFabricator(), readCar.getFabricator());
    assertEquals(actual.getModel(), carModel);
    assertEquals(actual.getCreateDate(), createCar.getCreateDate());

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(car);
  }

  @Test
  void getCarById() {

    long id = 2L;

    when(repository.findById(id)).thenReturn(Optional.of(car));
    when(mapper.readCarFromCar(car)).thenReturn(readCar);

    ReadCar actual = carService.get(id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readCar.getId());
    assertEquals(actual.getFabricator(), readCar.getFabricator());
    assertEquals(actual.getModel(), readCar.getModel());
    assertEquals(actual.getCreateDate(), createCar.getCreateDate());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getCarByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 5L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class, () -> carService.get(id));
    assertNull(exception.getMessage());

    verify(repository, times(1)).findById(id);

  }


  @Test
  void carIsPresent() {

    long id = 2L;

    when(repository.findById(id)).thenReturn(Optional.of(car));

    boolean actual = carService.isPresent(id);

    assertTrue(actual);
    verify(repository, times(1)).findById(id);

  }

  @Test
  void carIsNotPresentThrowResourceNotFoundException() {

    long id = 12L;

    String exceptionMessage = String.format("Car with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> carService.isPresent(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
  }

}