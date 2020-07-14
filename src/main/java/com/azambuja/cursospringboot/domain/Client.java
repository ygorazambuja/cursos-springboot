package com.azambuja.cursospringboot.domain;

import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class
Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCpnj;
    private Integer clientType;

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private List<Address> addressList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONES")
    private Set<String> phones = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "client")
    private List<BuyRequest> buyRequests = new ArrayList<>();

    public Client() {
    }

    public Client(Integer id, String name, String email, String cpfOrCpnj, ClientType clientType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCpnj = cpfOrCpnj;
        this.clientType = clientType.getCode();
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

    public String getCpfOrCpnj() {
        return cpfOrCpnj;
    }

    public void setCpfOrCpnj(String cpfOrCpnj) {
        this.cpfOrCpnj = cpfOrCpnj;
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

        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
