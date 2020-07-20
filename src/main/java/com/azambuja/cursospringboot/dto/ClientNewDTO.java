package com.azambuja.cursospringboot.dto;

import java.io.Serializable;

public class ClientNewDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private String email;
  private String cpfOrCpnj;
  private Integer clientType;

  private String publicPlace;
  private String localNumber;
  private String complement;
  private String neighborhood;
  private String zipcode;

  private String phone1, phone2, phone3;
  private Integer cityId;

  public ClientNewDTO() {}

  public ClientNewDTO(
    String name,
    String email,
    String cpfOrCpnj,
    Integer clientType,
    String publicPlace,
    String localNumber,
    String complement,
    String neighborhood,
    String zipcode,
    String phone1,
    String phone2,
    String phone3,
    Integer cityId
  ) {
    this.name = name;
    this.email = email;
    this.cpfOrCpnj = cpfOrCpnj;
    this.clientType = clientType;
    this.publicPlace = publicPlace;
    this.localNumber = localNumber;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.zipcode = zipcode;
    this.phone1 = phone1;
    this.phone2 = phone2;
    this.phone3 = phone3;
    this.cityId = cityId;
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

  public String getCpfOrCpnj() {
    return cpfOrCpnj;
  }

  public void setCpfOrCpnj(String cpfOrCpnj) {
    this.cpfOrCpnj = cpfOrCpnj;
  }

  public Integer getClientType() {
    return clientType;
  }

  public void setClientType(Integer clientType) {
    this.clientType = clientType;
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

  public String getPhone1() {
    return phone1;
  }

  public void setPhone1(String phone1) {
    this.phone1 = phone1;
  }

  public String getPhone2() {
    return phone2;
  }

  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }

  public String getPhone3() {
    return phone3;
  }

  public void setPhone3(String phone3) {
    this.phone3 = phone3;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }
}
