package com.logistic.transportlogistic.api.detail;

import com.logistic.transportlogistic.api.model.CreateDetail;
import com.logistic.transportlogistic.api.model.ReadDetail;
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

@RequestMapping("/detail")
@Validated
public interface DetailApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreateDetail addDetail(@Valid @RequestBody CreateDetail detail);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteDetail(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  CreateDetail updateDetail(@RequestBody CreateDetail detail,@PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDetail getDetailById (@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  List<ReadDetail> findAllDetail ();


}
