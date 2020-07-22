package com.azambuja.cursospringboot.domain;

import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(unique = true)
  private String email;
  private String cpfOrCnpj;
  private Integer clientType;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  private List<Address> addressList = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "PHONES")
  private Set<String> phones = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private List<BuyRequest> buyRequests = new ArrayList<>();

  public Client() {}

  public Client(
          Integer id,
          String name,
          String email,
          String cpfOrCnpj,
          ClientType clientType
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.cpfOrCnpj = cpfOrCnpj;
    this.clientType = (clientType == null) ? null : clientType.getCode();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfOrCnpj() {
    return cpfOrCnpj;
  }

  public void setCpfOrCnpj(String cpfOrCnpj) {
    this.cpfOrCnpj = cpfOrCnpj;
  }

  public ClientType getClientType() {
    return ClientType.toEnum(clientType);
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType.getCode();
  }

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addressList) {
    this.addressList = addressList;
  }

  public Set<String> getPhones() {
    return phones;
  }

  public void setPhones(Set<String> phones) {
    this.phones = phones;
  }

  public List<BuyRequest> getBuyRequests() {
    return buyRequests;
  }

  public void setBuyRequests(List<BuyRequest> buyRequests) {
    this.buyRequests = buyRequests;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Client client = (Client) o;

    return Objects.equals(id, client.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
