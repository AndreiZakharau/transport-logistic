package com.logistic.transportlogistic.api.car;

import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
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


@RequestMapping("/car")
@Validated
@Tag(name = "Car API", description = "Car api interface")
public interface CarApi {

  @Operation(summary = "Create car", description = "create new car")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadCar addCar(@Valid @RequestBody CreateCar car);

  @Operation(summary = "Delete car", description = "Delete car by ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCar(@PathVariable long id);

  @Operation(summary = "Update car", description = "Update car by ID")
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar updateCar(@Valid @RequestBody CreateCar car, @PathVariable long id);

  @Operation(summary = "Read car", description = "Get car by ID")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar getCarById(@PathVariable long id);

  @Operation(summary = "Read all car", description = "Get all cars")
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadCar> findAllCar(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @Operation(summary = "Read car",
      description = "get all cars by sort parameters")
  @GetMapping("/sort")
  @ResponseStatus(HttpStatus.OK)
  Page<ReadCar> sortTransport(
      @RequestParam(value = "sort", defaultValue = "id", required = false) List<String> sortColumns,
      @RequestParam(value = "order", defaultValue = "asc", required = false) List<String> orderTypes,
      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
      @RequestParam(value = "size", defaultValue = "10", required = false) int size);
}
