package com.logistic.transportlogistic.api.transport;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.service.TransportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportController implements TransportApi {

  private final TransportService service;


  @Override
  public ReadTransport addTransport(CreateTransport transport) {
    return service.add(transport);
  }

  @Override
  public void deleteTransport(long id) {
    service.delete(id);
  }

  @Override
  public ReadTransport updateTransport(CreateTransport transport, long id) {
    return service.update(transport, id);
  }

  @Override
  public ReadTransport getTransportById(long id) {
    return service.get(id);
  }

  @Override
  public Page<ReadTransport> findAllTransport(Integer page, Integer size) {
    return service.getAll(page, size);
  }

  @Override
  public ReadTransport setDriver(long transportId, long driverId) {
    return service.setDriverToTransport(transportId, driverId);
  }

  @Override
  public Page<ReadTransport> sortTransport(List<String> sortColumns, List<String> orderTypes,
      int page, int size) {
    return service.getAllBySort(sortColumns, orderTypes, page, size);
  }
}
