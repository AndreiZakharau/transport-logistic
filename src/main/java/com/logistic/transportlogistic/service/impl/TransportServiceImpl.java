package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.domain.Transport;
import com.logistic.transportlogistic.exception.ResourceNotFoundException;
import com.logistic.transportlogistic.mapper.TransportMapper;
import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.repositorie.TransportRepository;
import com.logistic.transportlogistic.service.TransportService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {

  private final TransportMapper mapper;

  private final TransportRepository repository;

  @Transactional
  @Override
  public ReadTransport add(CreateTransport createTransport) {

    Transport transport = mapper.transportFromCreateTransport(createTransport);
    return mapper.readTransportFromTransport(repository.save(transport));
  }

  @Transactional
  @Override
  public String delete(long id) {

    String message = String.format("Transport with id = '%s' has been deleted", id);
    if (isPresent(id)) {
      repository.deleteById(id);
      return message;
    } else {
      throw new ResourceNotFoundException(String.format("Transport with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadTransport update(CreateTransport createTransport, long id) {

    if(isPresent(id)) {
      Transport transport = mapper.transportFromCreateTransport(createTransport);
      transport.setId(id);
      return mapper.readTransportFromTransport(repository.save(transport));
    } else {
      throw new ResourceNotFoundException(String.format("Transport with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadTransport get(long id) {

    Optional<Transport>transport = repository.findById(id);
    return mapper.readTransportFromTransport(transport.get());
  }

  @Transactional
  @Override
  public Page<ReadTransport> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Transport> transports = repository.findAll(pageable);
    return transports.map(mapper::readTransportFromTransport);
  }

  @Override
  public boolean isPresent(long id) {
    return repository.findById(id).isPresent();
  }
}
