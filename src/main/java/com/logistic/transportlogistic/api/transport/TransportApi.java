package com.logistic.transportlogistic.api.transport;

import com.logistic.transportlogistic.api.model.CreateTransport;
import com.logistic.transportlogistic.api.model.ReadTransport;
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

@RequestMapping("/transport")
@Validated
public interface TransportApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreateTransport addCar(@Valid @RequestBody CreateTransport transport);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteCar(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  CreateTransport updateCar(@RequestBody CreateTransport transport,@PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadTransport getCarById (@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  List<ReadTransport> findAllCar ();


}
