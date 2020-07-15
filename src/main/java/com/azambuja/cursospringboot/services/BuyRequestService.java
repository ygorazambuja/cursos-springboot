package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.BuyRequest;
import com.azambuja.cursospringboot.repository.BuyRequestRepository;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyRequestService {
  @Autowired
  private BuyRequestRepository buyRequestRepository;

  public BuyRequest getById(Integer id) {
    return buyRequestRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException(
            "Object " + BuyRequest.class.getName() + " id: " + id + " not found"
          )
      );
  }
}
