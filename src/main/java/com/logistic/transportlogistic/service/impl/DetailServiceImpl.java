package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.api.pagination.SortParamsContext;
import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.DetailMapper;
import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
import com.logistic.transportlogistic.repositorie.DetailRepository;
import com.logistic.transportlogistic.service.DetailService;
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
public class DetailServiceImpl implements DetailService {

  private final DetailMapper mapper;

  private final DetailRepository repository;

  private final ColumnValidator validator;

  @Transactional
  @Override
  public ReadDetail add(CreateDetail createDetail) {

    Detail detail = mapper.detailFromCreateDetail(createDetail);
    return mapper.readDetailFromDetail(repository.save(detail));
  }

  @Transactional
  @Override
  public void delete(long id) {

    isPresent(id);
    repository.deleteById(id);
  }

  @Transactional
  @Override
  public ReadDetail update(CreateDetail createDetail, long id) {

    isPresent(id);
    Detail detail = mapper.detailFromCreateDetail(createDetail);
    detail.setId(id);
    return mapper.readDetailFromDetail(repository.save(detail));
  }

  @Transactional
  @Override
  public ReadDetail get(long id) {

    Detail detail = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return mapper.readDetailFromDetail(detail);
  }

  @Transactional
  @Override
  public Page<ReadDetail> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Detail> details = repository.findAll(pageable);
    return details.map(mapper::readDetailFromDetail);
  }

  @Override
  public boolean isPresent(long id) {

    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new ResourceNotFoundException(
          String.format("Detail with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public Page<ReadDetail> getAllBySort(List<String> sortColumns, List<String> orderTypes, int page,
      int size) {
    Sort sort = getSort(sortColumns, orderTypes);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Detail> details = repository.findAll(pageable);
    return details.map(mapper::readDetailFromDetail);
  }

  private Sort getSort(List<String> sortColumns, List<String> orderTypes) {

    List<String> typesList = Arrays.asList("ASC", "DESC");
    Sort sort = null;
    SortParamsContext sortParameters;
    if (sortColumns != null || !sortColumns.isEmpty()) {
      sortParameters = new SortParamsContext(sortColumns, orderTypes);

      if (!validator.detailColumnsValid(sortParameters)) {
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
