package com.logistic.transportlogistic.api.transport;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Transport API", description = "Transport api interface")
@Validated
public interface TransportApi {

  @PostMapping
  @Operation(summary = "Create transport", description = "Create new transport")
  @ResponseStatus(HttpStatus.CREATED)
  ReadTransport addTransport(@Valid @RequestBody CreateTransport transport);

  @DeleteMapping("{id}")
  @Operation(summary = "Delete transport", description = "Delete transport by ID")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteTransport(@PathVariable long id);

  @PutMapping("{id}")
  @Operation(summary = "Update transport", description = "Update transport by ID")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport updateTransport(@RequestBody CreateTransport transport, @PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport getTransportById(@PathVariable long id);

  @GetMapping()
  @Operation(summary = "Read transport", description = "Get transport by ID")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadTransport> findAllTransport(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @PutMapping("/{transportId}/driver/{driverId}")
  @Operation(summary = "Adding driver", description = "Adding driver by transport ID and driverID ")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport setDriver(@PathVariable long transportId, @PathVariable long driverId);

  @GetMapping("/sort")
  @Operation(summary = "Read all transport", description = "Get all transport by parameters and with pagination")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadTransport> sortTransport(
      @RequestParam(value = "sort", defaultValue = "id", required = false) List<String> sortColumns,
      @RequestParam(value = "order", defaultValue = "asc", required = false) List<String> orderTypes,
      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
      @RequestParam(value = "size", defaultValue = "10", required = false) int size);

}
