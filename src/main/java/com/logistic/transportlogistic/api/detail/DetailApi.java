package com.logistic.transportlogistic.api.detail;

import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/detail")
@Validated
public interface DetailApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadDetail addDetail(@Valid @RequestBody CreateDetail detail);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteDetail(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDetail updateDetail(@RequestBody CreateDetail detail, @PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDetail getDetailById(@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadDetail> findAllDetail(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @GetMapping("/sort")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadDetail> sortTransport(
      @RequestParam(value = "sort", defaultValue = "id", required = false) List<String> sortColumns,
      @RequestParam(value = "order", defaultValue = "asc", required = false) List<String> orderTypes,
      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
      @RequestParam(value = "size", defaultValue = "10", required = false) int size);
}
