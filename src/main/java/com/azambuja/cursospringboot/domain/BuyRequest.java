package com.azambuja.cursospringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BuyRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date instant;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "buyRequest")
  private Payment payment;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address deliverAddress;

  @OneToMany(mappedBy = "id.buyRequest")
  private Set<ItemRequest> itemRequestSet = new HashSet<>();

  public BuyRequest() {
  }

  public BuyRequest(Integer id, Date instant, Client client, Address deliverAddress) {
    super();
    this.id = id;
    this.instant = instant;
    this.client = client;
    this.deliverAddress = deliverAddress;
  }

  public double getTotalValue() {
    double sum = 0.0;
    for (ItemRequest itemRequest : itemRequestSet) {
      sum = sum + itemRequest.getSubTotal();
    }
    return sum;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getInstant() {
    return instant;
  }

  public void setInstant(Date instant) {
    this.instant = instant;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Address getDeliverAddress() {
    return deliverAddress;
  }

  public void setDeliverAddress(Address deliverAddress) {
    this.deliverAddress = deliverAddress;
  }

  public Set<ItemRequest> getItemRequestSet() {
    return itemRequestSet;
  }

  public void setItemRequestSet(Set<ItemRequest> itemRequestSet) {
    this.itemRequestSet = itemRequestSet;
  }
}
