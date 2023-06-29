package com.logistic.transportlogistic.api.component;

import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
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


@RequestMapping("/component")
@Tag(name = "Component API", description = "Component api interface")
@Validated
public interface ComponentApi {

  @Operation(summary = "Create component", description = "Create new component")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadComponent addCar(@Valid @RequestBody CreateComponent component);

  @Operation(summary = "Delete component", description = "Delete component by ID")
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCar(@PathVariable long id);

  @Operation(summary = "Update component", description = "Update component by ID")
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadComponent updateCar(@RequestBody CreateComponent readCar, @PathVariable long id);

  @Operation(summary = "Read component", description = "Get component by ID")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadComponent getCarById(@PathVariable long id);

  @Operation(summary = "Read all component", description = "Get all component with pagination")
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadComponent> findAllCar(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @Operation(summary = "Read all component" , description = "Get all component by sort parameters with pagination")
  @GetMapping("/sort")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadComponent> sortTransport(
      @RequestParam(value = "sort", defaultValue = "id", required = false) List<String> sortColumns,
      @RequestParam(value = "order", defaultValue = "asc", required = false) List<String> orderTypes,
      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
      @RequestParam(value = "size", defaultValue = "10", required = false) int size);
}
