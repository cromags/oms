package pl.bw.oms.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
//Order is SQL keyword, so I named it ClientOrder
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfOrderToTransport;
    private LocalDateTime dateOfSendToClient;
    private String comments;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Transport transport;

    @OneToMany//(mappedBy = "clientOrder")
    @JoinColumn(name = "client_order_id")
    Set<OrderDetails> orderDetails = new HashSet<>();

    public ClientOrder(LocalDateTime dateOfOrderToTransport, LocalDateTime dateOfSendToClient, String comments) {
        this.dateOfOrderToTransport = dateOfOrderToTransport;
        this.dateOfSendToClient = dateOfSendToClient;
        this.comments = comments;
    }

    public ClientOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfOrderToTransport() {
        return dateOfOrderToTransport;
    }

    public void setDateOfOrderToTransport(LocalDateTime dateOfOrderToTransport) {
        this.dateOfOrderToTransport = dateOfOrderToTransport;
    }

    public LocalDateTime getDateOfSendToClient() {
        return dateOfSendToClient;
    }

    public void setDateOfSendToClient(LocalDateTime dateOfSendToClient) {
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

