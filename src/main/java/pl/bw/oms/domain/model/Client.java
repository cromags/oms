package pl.bw.oms.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    //@NotNull creates database constraint
    //@NotBlank for back-end validation
    @NotNull @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;
    @NotNull @NotBlank
    private String city;
    @NotNull @NotBlank
    private String address;
    @NotNull @NotBlank
    private String tel;

    @Email
    private String email;
    //@Column creates database constraint
    //@Size for back-end validation
    @Column(length = 500) @Size(max = 500)
    private String comments;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    Set<ClientOrder> clientOrders = new HashSet<>();

    public Client(String firstName, String lastName, String zipCode, String city, String address, String tel, String email, String comments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.comments = comments;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

        Client client = (Client) o;

        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
