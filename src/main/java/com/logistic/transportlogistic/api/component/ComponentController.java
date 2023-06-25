package com.logistic.transportlogistic.api.component;

import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.service.ComponentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComponentController implements ComponentApi{

  private final ComponentService service;

  @Override
  public ReadComponent addCar(CreateComponent component) {
    return service.add(component);
  }

  @Override
  public void deleteCar(long id) {
    service.delete(id);
  }

  @Override
  public ReadComponent updateCar(CreateComponent readCar, long id) {
    return service.update(readCar, id);
  }

  @Override
  public ReadComponent getCarById(long id) {
    return service.get(id);
  }

  @Override
  public Page<ReadComponent> findAllCar(Integer page, Integer size) {
    return service.getAll(page,size);
  }

  @Override
  public Page<ReadComponent> sortTransport(List<String> sortColumns, List<String> orderTypes,
      int page, int size) {
    return service.getAllBySort(sortColumns,orderTypes,page,size);
  }

}
