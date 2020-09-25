package pl.bw.oms.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
//Order is SQL keyword, so I named it ClientOrder
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfOrderToTransport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfSendToClient;
    private String comments;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Transport transport;

    @OneToMany//(mappedBy = "clientOrder")
    @JoinColumn(name = "client_order_id")
    Set<OrderDetails> orderDetails = new HashSet<>();


    public ClientOrder(LocalDate dateOfOrderToTransport, LocalDate dateOfSendToClient, String comments, Client client, Transport transport) {
        this.dateOfOrderToTransport = dateOfOrderToTransport;
        this.dateOfSendToClient = dateOfSendToClient;
        this.comments = comments;
        this.client = client;
        this.transport = transport;
    }

    public ClientOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfOrderToTransport() {
        return dateOfOrderToTransport;
    }

    public void setDateOfOrderToTransport(LocalDate dateOfOrderToTransport) {
        this.dateOfOrderToTransport = dateOfOrderToTransport;
    }

    public LocalDate getDateOfSendToClient() {
        return dateOfSendToClient;
    }

    public void setDateOfSendToClient(LocalDate dateOfSendToClient) {
        this.dateOfSendToClient = dateOfSendToClient;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientOrder clientOrder = (ClientOrder) o;

        return id != null ? id.equals(clientOrder.id) : clientOrder.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

