package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.dto.ClientDTO;
import com.azambuja.cursospringboot.dto.ClientNewDTO;
import com.azambuja.cursospringboot.services.ClientService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
  @Autowired
  private ClientService clientService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Client> getById(@PathVariable Integer id) {
    Client client = clientService.getById(id);
    return ResponseEntity.ok().body(client);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO newClient) {
    Client client = clientService.fromDTO(newClient);
    client = clientService.insert(client);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(client.getId())
      .toUri();
    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(
    @Valid @RequestBody ClientDTO clientDTO,
    @PathVariable Integer id
  ) {
    Client client = clientService.fromDTO(clientDTO);
    client.setId(id);
    clientService.update(client);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    clientService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<ClientDTO>> getAll() {
    List<Client> categories = clientService.getAll();
    List<ClientDTO> clientDTOS = categories
      .stream()
      .map(ClientDTO::new)
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(clientDTOS);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<ClientDTO>> getByPage(
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<Client> clientServicePage = clientService.findPage(
      page,
      linesPerPage,
      orderBy,
      direction
    );
    Page<ClientDTO> clientDTOS = clientServicePage.map(ClientDTO::new);
    return ResponseEntity.ok().body(clientDTOS);
  }
}
