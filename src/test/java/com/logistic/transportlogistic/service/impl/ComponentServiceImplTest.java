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

import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.ComponentMapper;
import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.repositorie.ComponentRepository;
import com.logistic.transportlogistic.testobject.TestModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ComponentServiceImplTest {

  @InjectMocks
  ComponentServiceImpl service;

  @Mock
  ComponentRepository repository;

  @Mock
  ComponentMapper mapper;


  private final Component component = TestModel.getComponent();
  private final ReadComponent readComponent = TestModel.getReadComponent();
  private final CreateComponent createComponent = TestModel.getCreateComponent();


  @Test
  void saveComponent() {

    when(mapper.componentFromCreateComponent(createComponent)).thenReturn(component);
    when(repository.save(component)).thenReturn(component);
    when(mapper.readComponentFromComponent(component)).thenReturn(readComponent);

    ReadComponent actual = service.add(createComponent);

    assertNotNull(actual);
    assertEquals(actual.getId(), readComponent.getId());
    assertEquals(actual.getType(), readComponent.getType());
    assertEquals(actual.getDetails(), readComponent.getDetails());
  }

  @Test
  void deleteComponentById() {

    long id = 12L;

    when(repository.findById(id)).thenReturn(Optional.of(component));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void deleteComponentByIdIfNoSuchIdThenThrowResourceNotFoundException() {

    long id = 5L;
    String exceptionMessage = String.format("Component with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);

  }

  @Test
  void updateComponentById() {

    long id = 12L;
    String type = "body";
    createComponent.setType(type);

    when(repository.findById(id)).thenReturn(Optional.of(component));

    assertNotEquals(component.getType(), type);

    component.setType(type);
    readComponent.setType(type);

    when(mapper.componentFromCreateComponent(createComponent)).thenReturn(component);
    when(repository.save(component)).thenReturn(component);
    when(mapper.readComponentFromComponent(component)).thenReturn(readComponent);

    ReadComponent actual = service.update(createComponent, id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readComponent.getId());
    assertEquals(actual.getType(), readComponent.getType());
    assertEquals(actual.getDetails(), readComponent.getDetails());

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(component);
  }

  @Test
  void getComponentById() {

    long id = 12L;

    when(repository.findById(id)).thenReturn(Optional.of(component));
    when(mapper.readComponentFromComponent(component)).thenReturn(readComponent);

    ReadComponent actual = service.get(id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readComponent.getId());
    assertEquals(actual.getType(), readComponent.getType());
    assertEquals(actual.getDetails(), readComponent.getDetails());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getComponentByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 5L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class, () -> service.get(id));
    assertNull(exception.getMessage());

    verify(repository, times(1)).findById(id);

  }


  @Test
  void componentIsPresent() {

    long id = 12L;

    when(repository.findById(id)).thenReturn(Optional.of(component));

    boolean actual = service.isPresent(id);

    assertTrue(actual);
    verify(repository, times(1)).findById(id);

  }

  @Test
  void componentIsNotPresentThrowResourceNotFoundException() {

    long id = 5L;

    String exceptionMessage = String.format("Component with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.isPresent(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
  }
}