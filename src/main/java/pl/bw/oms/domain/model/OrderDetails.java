package pl.bw.oms.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClientOrder clientOrder;

    @ManyToOne
    private Product product;

    private int quantity;

    //this annotation solves problem with mapping HTML [date] to Java [LocalDate]
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfOrderByClient;

    @ManyToOne
    private OrderMethod orderMethod;

    public OrderDetails() {
    }

    public OrderDetails(ClientOrder clientOrder, Product product, int quantity, LocalDate dateOfOrderByClient, OrderMethod orderMethod) {
        this.clientOrder = clientOrder;
        this.product = product;
        this.quantity = quantity;
        this.dateOfOrderByClient = dateOfOrderByClient;
        this.orderMethod = orderMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateOfOrderByClient() {
        return dateOfOrderByClient;
    }

    public void setDateOfOrderByClient(LocalDate dateOfOrderByClient) {
        this.dateOfOrderByClient = dateOfOrderByClient;
    }



    public OrderMethod getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(OrderMethod orderMethod) {
        this.orderMethod = orderMethod;
    }
}

