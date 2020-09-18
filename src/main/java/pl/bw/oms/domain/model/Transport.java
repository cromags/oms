package pl.bw.oms.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    private String name;

    private String zipCode;
    private String city;
    private String address;
    private String tel;

    @Email
    private String email;
    private String www;
    private String comments;

    @OneToMany
    @JoinColumn(name = "transport_id")
    Set<ClientOrder> clientOrders = new HashSet<>();

    public Transport(String name, String zipCode, String city, String address, String tel, String email, String www, String comments) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.www = www;
        this.comments = comments;
    }

    public Transport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<ClientOrder> getClientOrders() {
        return clientOrders;
    }

    public void setClientOrders(Set<ClientOrder> clientOrders) {
        this.clientOrders = clientOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        return id != null ? id.equals(transport.id) : transport.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

