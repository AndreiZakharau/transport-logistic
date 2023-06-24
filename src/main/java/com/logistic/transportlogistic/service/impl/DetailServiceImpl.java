package com.logistic.transportlogistic.service.impl;

import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.exception.DataException;
import com.logistic.transportlogistic.mapper.DetailMapper;
import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
import com.logistic.transportlogistic.repositorie.DetailRepository;
import com.logistic.transportlogistic.service.DetailService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

  private final DetailMapper mapper;

  private final DetailRepository repository;

  @Transactional
  @Override
  public ReadDetail add(CreateDetail createDetail) {

    Detail detail = mapper.detailFromCreateDetail(createDetail);
    return mapper.readDetailFromDetail(detail);
  }

  @Transactional
  @Override
  public String delete(long id) {

    String message = String.format("Detail with id = '%s' has been deleted", id);
    if (isPresent(id)) {
      repository.deleteById(id);
      return message;
    } else {
      throw new DataException(String.format("Detail with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadDetail update(CreateDetail createDetail, long id) {

    if (isPresent(id)) {
      Detail detail = mapper.detailFromCreateDetail(createDetail);
      detail.setId(id);
      return mapper.readDetailFromDetail(repository.save(detail));
    } else {
      throw new DataException(String.format("Detail with id = '%s' could be found", id));
    }
  }

  @Transactional
  @Override
  public ReadDetail get(long id) {

    Optional<Detail> detail = repository.findById(id);
    return mapper.readDetailFromDetail(detail.get());
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

    return repository.findById(id).isPresent();
  }
}
