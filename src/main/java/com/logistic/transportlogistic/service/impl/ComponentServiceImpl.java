package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.exception.DataException;
import com.logistic.transportlogistic.mapper.ComponentMapper;
import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.repositorie.ComponentRepository;
import com.logistic.transportlogistic.service.ComponentService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

  private final ComponentMapper mapper;

  private final ComponentRepository repository;


  @Transactional
  @Override
  public ReadComponent add(CreateComponent createComponent) {

    Component component = mapper.componentFromCreateComponent(createComponent);
    return mapper.readComponentFromComponent(repository.save(component));
  }

  @Transactional
  @Override
  public String delete(long id) {

    String message = String.format("Component with id = '%s' has been deleted", id);
    if (isPresent(id)) {
      repository.deleteById(id);
      return  message;
    } else {
      throw new DataException(String.format("Component with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadComponent update(CreateComponent createComponent, long id) {

    if (isPresent(id)) {
      Component component = mapper.componentFromCreateComponent(createComponent);
      component.setId(id);
      return  mapper.readComponentFromComponent(repository.save(component));
    } else {
      throw new DataException(String.format("Component with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadComponent get(long id) {

    Optional<Component> component = repository.findById(id);
    return mapper.readComponentFromComponent(component.get());
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

    return repository.findById(id).isPresent();
  }
}
