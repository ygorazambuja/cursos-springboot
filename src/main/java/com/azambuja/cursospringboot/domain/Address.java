package com.azambuja.cursospringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Address implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String publicPlace;
  private String localNumber;
  private String complement;
  private String neighborhood;
  private String zipcode;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  public Address() {}

  public Address(
    Integer id,
    String publicPlace,
    String localNumber,
    String complement,
    String neighborhood,
    String zipcode,
    Client client,
    City city
  ) {
    this.id = id;
    this.publicPlace = publicPlace;
    this.localNumber = localNumber;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.zipcode = zipcode;
    this.client = client;
    this.city = city;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPublicPlace() {
    return publicPlace;
  }

  public void setPublicPlace(String publicPlace) {
    this.publicPlace = publicPlace;
  }

  public String getLocalNumber() {
    return localNumber;
  }

  public void setLocalNumber(String localNumber) {
    this.localNumber = localNumber;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    return id != null ? id.equals(address.id) : address.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
