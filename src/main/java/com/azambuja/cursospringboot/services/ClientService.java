package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Address;
import com.azambuja.cursospringboot.domain.City;
import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.azambuja.cursospringboot.dto.ClientDTO;
import com.azambuja.cursospringboot.dto.ClientNewDTO;
import com.azambuja.cursospringboot.repository.AddressRepository;
import com.azambuja.cursospringboot.repository.ClientRepository;
import com.azambuja.cursospringboot.services.exceptions.DataIntegrityException;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private AddressRepository addressRepository;

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

  @Transactional
  public Client insert(Client client) {
    client.setId(null);
    client = clientRepository.save(client);
    addressRepository.saveAll(client.getAddressList());
    return clientRepository.save(client);
  }

  public void update(Client client) {
    Client newClient = getById(client.getId());
    updateData(newClient, client);
    clientRepository.save(newClient);
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
    return new Client(
      clientDTO.getId(),
      clientDTO.getName(),
      clientDTO.getEmail(),
      null,
      null
    );
  }

  public Client fromDTO(ClientNewDTO clientDTO) {
    Client client = new Client(
      null,
      clientDTO.getName(),
      clientDTO.getEmail(),
      clientDTO.getCpfOrCnpj(),
      ClientType.toEnum(clientDTO.getClientType())
    );
    City city = new City(clientDTO.getCityId(), null, null);
    Address address = new Address(
      null,
      clientDTO.getPublicPlace(),
      clientDTO.getLocalNumber(),
      clientDTO.getComplement(),
      clientDTO.getNeighborhood(),
      clientDTO.getZipcode(),
      client,
      city
    );
    client.getAddressList().add(address);
    if (clientDTO.getPhone1() != null) {
      client.getPhones().add(clientDTO.getPhone1());
    }
    if (clientDTO.getPhone2() != null) {
      client.getPhones().add(clientDTO.getPhone2());
    }
    if (clientDTO.getPhone3() != null) {
      client.getPhones().add(clientDTO.getPhone3());
    }

    return client;
  }
}
