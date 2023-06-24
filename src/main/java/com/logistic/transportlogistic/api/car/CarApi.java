package com.logistic.transportlogistic.api.car;

import com.logistic.transportlogistic.api.model.ReadCar;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/car")
@Validated
public interface CarApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadCar addCar(@Valid @RequestBody ReadCar car);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteCar(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar updateCar(@RequestBody ReadCar car,@PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCar getCarById (@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  List<ReadCar> findAllCar ();



}
