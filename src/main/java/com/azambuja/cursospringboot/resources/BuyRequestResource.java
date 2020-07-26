package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.BuyRequest;
import com.azambuja.cursospringboot.services.BuyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/buyRequests")
public class BuyRequestResource {
  @Autowired
  BuyRequestService buyRequestService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<BuyRequest> getById(@PathVariable Integer id) {
    BuyRequest buyRequest = buyRequestService.getById(id);
    return ResponseEntity.ok().body(buyRequest);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody BuyRequest buyRequest) {
    buyRequest = buyRequestService.insert(buyRequest);
    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(buyRequest.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }
}
