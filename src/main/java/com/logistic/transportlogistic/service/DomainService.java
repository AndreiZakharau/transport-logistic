package com.logistic.transportlogistic.service;

import java.util.List;
import org.springframework.data.domain.Page;

public interface DomainService<T, K> {

  K add(T t);

  void delete(long id);

  K update(T t, long id);

  K get(long id);

  Page<K> getAll(int page, int size);

  boolean isPresent(long id);

  Page<K> getAllBySort(List<String> sortColumns, List<String> orderTypes, int page, int size);
}
