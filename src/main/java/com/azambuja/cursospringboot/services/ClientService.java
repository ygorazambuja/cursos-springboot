package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.dto.ClientDTO;
import com.azambuja.cursospringboot.repository.ClientRepository;
import com.azambuja.cursospringboot.services.exceptions.DataIntegrityException;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Client insert(Client client) {
    client.setId(null);
    return clientRepository.save(client);
  }

  public Client update(Client client) {
    Client newClient = getById(client.getId());
    updateData(newClient, client);
    return clientRepository.save(newClient);
  }

  private void updateData(Client newClient, Client client) {
    newClient.setName(client.getName());
    newClient.setEmail(client.getEmail());
  }

  public void delete(Integer id) {
    getById(id);
    try {
      clientRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Client have Dependents", e.getCause());
    }
  }

  public List<Client> getAll() {
    return clientRepository.findAll();
  }

  public Page<Client> findPage(
          Integer page,
          Integer linesPerPage,
          String orderBy,
          String direction
  ) {
    PageRequest pageRequest = PageRequest.of(
            page,
            linesPerPage,
            Sort.Direction.valueOf(direction),
            orderBy
    );
    return clientRepository.findAll(pageRequest);
  }

  public Client fromDTO(ClientDTO clientDTO) {

    return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);

  }
}
