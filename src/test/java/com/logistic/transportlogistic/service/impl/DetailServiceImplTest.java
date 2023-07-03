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

import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.DetailMapper;
import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
import com.logistic.transportlogistic.repositorie.DetailRepository;
import com.logistic.transportlogistic.testobject.TestModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DetailServiceImplTest {

  @InjectMocks
  DetailServiceImpl service;

  @Mock
  DetailRepository repository;

  @Mock
  DetailMapper mapper;


  private final Detail detail = TestModel.getDetail();
  private final ReadDetail readDetail = TestModel.getReadDetail();
  private final CreateDetail createDetail = TestModel.getCreateDetail();


  @Test
  void saveDetail() {

    when(mapper.detailFromCreateDetail(createDetail)).thenReturn(detail);
    when(repository.save(detail)).thenReturn(detail);
    when(mapper.readDetailFromDetail(detail)).thenReturn(readDetail);

    ReadDetail actual = service.add(createDetail);

    assertNotNull(actual);
    assertEquals(actual.getId(), readDetail.getId());
    assertEquals(actual.getNumber(), readDetail.getNumber());
    assertEquals(actual.getTypeDetail(), readDetail.getTypeDetail());
  }

  @Test
  void deleteDetailById() {

    long id = 45L;

    when(repository.findById(id)).thenReturn(Optional.of(detail));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void deleteDetailByIdIfNoSuchIdThenThrowResourceNotFoundException() {

    long id = 5L;
    String exceptionMessage = String.format("Detail with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);

  }

  @Test
  void updateDetailById() {

    long id = 45L;
    String numberDetail = "567HGHJHJH6765";
    createDetail.setNumber(numberDetail);

    when(repository.findById(id)).thenReturn(Optional.of(detail));

    assertNotEquals(detail.getNumber(), numberDetail);

    detail.setNumber(numberDetail);
    readDetail.setNumber(numberDetail);

    when(mapper.detailFromCreateDetail(createDetail)).thenReturn(detail);
    when(repository.save(detail)).thenReturn(detail);
    when(mapper.readDetailFromDetail(detail)).thenReturn(readDetail);

    ReadDetail actual = service.update(createDetail, id);

    assertEquals(actual.getId(), readDetail.getId());
    assertEquals(actual.getNumber(), readDetail.getNumber());
    assertEquals(actual.getTypeDetail(), readDetail.getTypeDetail());
    assertEquals(actual.getTransport(), readDetail.getTransport());

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(detail);
  }

  @Test
  void getDetailById() {

    long id = 45L;

    when(repository.findById(id)).thenReturn(Optional.of(detail));
    when(mapper.readDetailFromDetail(detail)).thenReturn(readDetail);

    ReadDetail actual = service.get(id);

    assertEquals(actual.getId(), readDetail.getId());
    assertEquals(actual.getNumber(), readDetail.getNumber());
    assertEquals(actual.getTypeDetail(), readDetail.getTypeDetail());
    assertEquals(actual.getTransport(), readDetail.getTransport());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getDetailByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 5L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class, () -> service.get(id));
    assertNull(exception.getMessage());

    verify(repository, times(1)).findById(id);

  }


  @Test
  void detailIsPresent() {

    long id = 45L;

    when(repository.findById(id)).thenReturn(Optional.of(detail));

    boolean actual = service.isPresent(id);

    assertTrue(actual);
    verify(repository, times(1)).findById(id);

  }

  @Test
  void detailIsNotPresentThrowResourceNotFoundException() {

    long id = 5L;

    String exceptionMessage = String.format("Detail with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(ResourceNotFoundException.class,
        () -> service.isPresent(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
  }
}