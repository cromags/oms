package pl.bw.oms.domain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMethodName;

    @OneToMany//(mappedBy = "orderMethod")
    @JoinColumn(name = "order_method_id")
    Set<OrderDetails> orderDetails = new HashSet<>();

    public OrderMethod(String orderMethodName) {
        this.orderMethodName = orderMethodName;
    }

    public OrderMethod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderMethodName() {
        return orderMethodName;
    }

    public void setOrderMethodName(String orderMethodName) {
        this.orderMethodName = orderMethodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderMethod that = (OrderMethod) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

