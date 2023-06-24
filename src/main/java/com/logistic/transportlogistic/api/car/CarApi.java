package com.logistic.transportlogistic.api.car;

import com.logistic.transportlogistic.model.CreateCar;
import com.logistic.transportlogistic.model.ReadCar;
import jakarta.validation.Valid;
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
public interface CarApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadCar addCar(@Valid @RequestBody CreateCar car);

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteCar(@PathVariable long id);

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar updateCar(@Valid @RequestBody CreateCar car, @PathVariable long id);

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar getCarById(@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadCar> findAllCar(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);
}
