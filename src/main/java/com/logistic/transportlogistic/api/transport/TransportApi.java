package com.logistic.transportlogistic.api.transport;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
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

@RequestMapping("/transport")
@Validated
public interface TransportApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadTransport addTransport(@Valid @RequestBody CreateTransport transport);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteTransport(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport updateTransport(@RequestBody CreateTransport transport, @PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport getTransportById(@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadTransport> findAllTransport(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @PutMapping("/{transportId}/driver/{driverId}")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport setDriver(@PathVariable long transportId, @PathVariable long driverId);

  @GetMapping("/sort")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadTransport> sortTransport(
      @RequestParam(value = "sort", defaultValue = "id", required = false) List<String> sortColumns,
      @RequestParam(value = "order", defaultValue = "asc", required = false) List<String> orderTypes,
      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
      @RequestParam(value = "size", defaultValue = "10", required = false) int size);

}
