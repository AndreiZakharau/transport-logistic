package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.api.pagination.SortParamsContext;
import com.logistic.transportlogistic.domain.Car;
import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.ComponentMapper;
import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.repositorie.ComponentRepository;
import com.logistic.transportlogistic.service.ComponentService;
import com.logistic.transportlogistic.service.util.ColumnValidator;
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
public class ComponentServiceImpl implements ComponentService {

  private final ComponentMapper mapper;

  private final ComponentRepository repository;

  private final ColumnValidator validator;


  @Transactional
  @Override
  public ReadComponent add(CreateComponent createComponent) {

    Component component = mapper.componentFromCreateComponent(createComponent);
    return mapper.readComponentFromComponent(repository.save(component));
  }

  @Transactional
  @Override
  public void delete(long id) {

    isPresent(id);
    repository.deleteById(id);
  }

  @Transactional
  @Override
  public ReadComponent update(CreateComponent createComponent, long id) {

    isPresent(id);
    Component component = mapper.componentFromCreateComponent(createComponent);
    component.setId(id);
    return mapper.readComponentFromComponent(repository.save(component));
  }

  @Transactional
  @Override
  public ReadComponent get(long id) {

    Component component = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return mapper.readComponentFromComponent(component);
  }

  @Transactional
  @Override
  public Page<ReadComponent> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Component> components = repository.findAll(pageable);
    return components.map(mapper::readComponentFromComponent);
  }

  @Override
  public boolean isPresent(long id) {

    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new ResourceNotFoundException(
          String.format("Component with id = '%s' could be found", id));
    }
  }

  @Override
  public Page<ReadComponent> getAllBySort(List<String> sortColumns, List<String> orderTypes,
      int page, int size) {
    Sort sort =getSort(sortColumns, orderTypes);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Component> components = repository.findAll(pageable);
    return components.map(mapper::readComponentFromComponent);
  }

  private Sort getSort(List<String> sortColumns, List<String> orderTypes) {

    List<String> typesList = Arrays.asList("ASC", "DESC");
    Sort sort = null;
    SortParamsContext sortParameters;
    if (sortColumns != null || !sortColumns.isEmpty()) {
      sortParameters = new SortParamsContext(sortColumns, orderTypes);

      if (!validator.componentColumnsValid(sortParameters)) {
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
