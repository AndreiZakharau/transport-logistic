package com.logistic.transportlogistic.api.transport;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportController implements TransportApi{

  private final TransportService service;


  @Override
  public ReadTransport addCar(CreateTransport transport) {

    return service.add(transport);
  }

  @Override
  public String deleteCar(long id) {

    return service.delete(id);
  }

  @Override
  public ReadTransport updateCar(CreateTransport transport, long id) {

    return service.update(transport, id);
  }

  @Override
  public ReadTransport getCarById(long id) {

    return service.get(id);
  }

  @Override
  public Page<ReadTransport> findAllCar(Integer page, Integer size) {

    return service.getAll(page,size);
  }
}
