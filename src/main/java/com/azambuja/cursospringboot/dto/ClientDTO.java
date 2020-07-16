package com.azambuja.cursospringboot.dto;

import com.azambuja.cursospringboot.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClientDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  @NotEmpty(message = "Cannot be Empty")
  @Length(min = 5, max = 120, message = "Size must be between 5 and 120")
  private String name;
  @NotEmpty(message = "Cannot be Empty")
  @Email(message = "Invalid Email")
  private String email;

  public ClientDTO() {
  }

  public ClientDTO(Client client) {
    this.id = client.getId();
    this.name = client.getName();
    this.email = client.getEmail();
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
}
