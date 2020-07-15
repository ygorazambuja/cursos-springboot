package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.repository.ClientRepository;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  public Client getById(Integer id) {
    return clientRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException(
            "Object " + Client.class.getName() + " id: " + id + " not found"
          )
      );
  }
}
