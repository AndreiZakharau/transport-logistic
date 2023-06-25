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

import com.logistic.transportlogistic.domain.Transport;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.TransportMapper;
import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.repositorie.TransportRepository;
import com.logistic.transportlogistic.testobject.TestModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransportServiceImplTest {

  @InjectMocks
  TransportServiceImpl service;

  @Mock
  TransportRepository repository;

  @Mock
  TransportMapper mapper;


  private final Transport transport = TestModel.getTransport();
  private final ReadTransport readTransport = TestModel.getReadTransport();
  private final CreateTransport createTransport = TestModel.getCreateTransport();


  @Test
  void saveTransport() {

    when(mapper.transportFromCreateTransport(createTransport)).thenReturn(transport);
    when(repository.save(transport)).thenReturn(transport);
    when(mapper.readTransportFromTransport(transport)).thenReturn(readTransport);

    ReadTransport actual = service.add(createTransport);

    assertNotNull(actual);
    assertEquals(actual.getId(), readTransport.getId());
    assertEquals(actual.getVin(), readTransport.getVin());
    assertEquals(actual.getRegistryNumber(), readTransport.getRegistryNumber());
  }

  @Test
  void deleteTransportById() {

    long id = 13L;

    when(repository.findById(id)).thenReturn(Optional.of(transport));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void deleteTransportByIdIfNoSuchIdThenThrowResourceNotFoundException() {

    long id = 5L;
    String exceptionMessage = String.format("Transport with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);

  }

  @Test
  void updateTransportById() {

    long id = 13L;
    String registryNumber = "AP56007";
    createTransport.setRegistryNumber(registryNumber);

    when(repository.findById(id)).thenReturn(Optional.of(transport));

    assertNotEquals(transport.getRegistryNumber(), registryNumber);

    transport.setRegistryNumber(registryNumber);
    readTransport.setRegistryNumber(registryNumber);

    when(mapper.transportFromCreateTransport(createTransport)).thenReturn(transport);
    when(repository.save(transport)).thenReturn(transport);
    when(mapper.readTransportFromTransport(transport)).thenReturn(readTransport);

    ReadTransport actual = service.update(createTransport, id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readTransport.getId());
    assertEquals(actual.getVin(), readTransport.getVin());
    assertEquals(actual.getRegistryNumber(), readTransport.getRegistryNumber());
    assertEquals(actual.getCar(), readTransport.getCar());
    assertEquals(actual.getDetails(), readTransport.getDetails());

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(transport);
  }

  @Test
  void getTransportById() {

    long id = 13L;

    when(repository.findById(id)).thenReturn(Optional.of(transport));
    when(mapper.readTransportFromTransport(transport)).thenReturn(readTransport);

    ReadTransport actual = service.get(id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readTransport.getId());
    assertEquals(actual.getVin(), readTransport.getVin());
    assertEquals(actual.getRegistryNumber(), readTransport.getRegistryNumber());
    assertEquals(actual.getCar(), readTransport.getCar());
    assertEquals(actual.getDetails(), readTransport.getDetails());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getTransportByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 5L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class, () -> service.get(id));
    assertNull(exception.getMessage());

    verify(repository, times(1)).findById(id);

  }


  @Test
  void transportIsPresent() {

    long id = 45L;

    when(repository.findById(id)).thenReturn(Optional.of(transport));

    boolean actual = service.isPresent(id);

    assertTrue(actual);
    verify(repository, times(1)).findById(id);

  }

  @Test
  void transportIsNotPresentThrowResourceNotFoundException() {

    long id = 5L;

    String exceptionMessage = String.format("Transport with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.isPresent(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
  }

  @Test
  void setDriverToTransport() {

    long driverId = 333L;
    long transportId = 13L;

    when(repository.findById(transportId)).thenReturn(Optional.of(transport));

    assertEquals(transport.getId(), transportId);
    assertNotEquals(transport.getDriverId(), driverId);

    transport.setDriverId(driverId);
    readTransport.setDriverId(driverId);

    when(repository.save(transport)).thenReturn(transport);
    when(mapper.readTransportFromTransport(transport)).thenReturn(readTransport);

    ReadTransport actual = service.setDriverToTransport(transportId, driverId);

    assertNotNull(actual);
    assertEquals(actual.getId(), readTransport.getId());
    assertEquals(actual.getVin(), readTransport.getVin());
    assertEquals(actual.getRegistryNumber(), readTransport.getRegistryNumber());
    assertEquals(actual.getCar(), readTransport.getCar());
    assertEquals(actual.getDetails(), readTransport.getDetails());
    assertEquals(actual.getDriverId(), driverId);

    verify(repository, times(1)).findById(transportId);
  }
}