package com.logistic.transportlogistic.api.component;

import com.logistic.transportlogistic.api.model.CreateComponent;
import com.logistic.transportlogistic.api.model.ReadComponent;
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

@RequestMapping("/component")
@Validated
public interface ComponentApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreateComponent addCar(@Valid @RequestBody CreateComponent component);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteCar(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadComponent updateCar(@RequestBody CreateComponent readCar,@PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadComponent getCarById (@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  List<ReadComponent> findAllCar ();

}