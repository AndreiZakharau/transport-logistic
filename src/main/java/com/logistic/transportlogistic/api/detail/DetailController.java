package com.logistic.transportlogistic.api.detail;

import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
import com.logistic.transportlogistic.service.DetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DetailController implements DetailApi {

  private final DetailService service;

  @Override
  public ReadDetail addDetail(CreateDetail detail) {
    return service.add(detail);
  }

  @Override
  public void deleteDetail(long id) {
    service.delete(id);
  }

  @Override
  public ReadDetail updateDetail(CreateDetail detail, long id) {
    return service.update(detail, id);
  }

  @Override
  public ReadDetail getDetailById(long id) {
    return service.get(id);
  }

  @Override
  public Page<ReadDetail> findAllDetail(Integer page, Integer size) {
    return service.getAll(page,size);
  }

  @Override
  public Page<ReadDetail> sortTransport(List<String> sortColumns, List<String> orderTypes, int page,
      int size) {
    return service.getAllBySort(sortColumns,orderTypes,page,size);
  }
}
