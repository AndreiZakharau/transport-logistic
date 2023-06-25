package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.api.pagination.SortParamsContext;
import com.logistic.transportlogistic.domain.Transport;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.TransportMapper;
import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.repositorie.TransportRepository;
import com.logistic.transportlogistic.service.TransportService;
import com.logistic.transportlogistic.service.util.ColumnValidator;
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
public class TransportServiceImpl implements TransportService {

  private final TransportMapper mapper;

  private final TransportRepository repository;

  private final ColumnValidator validator;

  @Transactional
  @Override
  public ReadTransport add(CreateTransport createTransport) {

    Transport transport = mapper.transportFromCreateTransport(createTransport);
    return mapper.readTransportFromTransport(repository.save(transport));
  }

  @Transactional
  @Override
  public void delete(long id) {

    isPresent(id);
    repository.deleteById(id);

  }

  @Transactional
  @Override
  public ReadTransport update(CreateTransport createTransport, long id) {

    isPresent(id);
    Transport transport = mapper.transportFromCreateTransport(createTransport);
    transport.setId(id);
    return mapper.readTransportFromTransport(repository.save(transport));

  }

  @Transactional
  @Override
  public ReadTransport get(long id) {

    Transport transport = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return mapper.readTransportFromTransport(transport);
  }

  @Transactional
  @Override
  public Page<ReadTransport> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Transport> transports = repository.findAll(pageable);
    return transports.map(mapper::readTransportFromTransport);
  }

  @Transactional
  @Override
  public ReadTransport setDriverToTransport(long transportId, long driverId) {

    Transport transport = repository.findById(transportId)
        .orElseThrow(ResourceNotFoundException::new);
    transport.setDriverId(driverId);
    return mapper.readTransportFromTransport(repository.save(transport));
  }

  @Override
  public Page<ReadTransport> getAllBySort(List<String> sortColumns, List<String> orderTypes,
      int page, int size) {

    Sort sort = getSort(sortColumns, orderTypes);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Transport> transports = repository.findAll(pageable);
    return transports.map(mapper::readTransportFromTransport);
  }

  @Override
  public boolean isPresent(long id) {

    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new ResourceNotFoundException(
          String.format("Transport with id = '%s' could be found", id));
    }
  }

  private Sort getSort(List<String> sortColumns, List<String> orderTypes) {

    List<String> typesList = Arrays.asList("ASC", "DESC");
    Sort sort = null;
    SortParamsContext sortParameters;
    if (sortColumns != null || !sortColumns.isEmpty()) {
      sortParameters = new SortParamsContext(sortColumns, orderTypes);

      if (!validator.transportColumnsValid(sortParameters)) {
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
