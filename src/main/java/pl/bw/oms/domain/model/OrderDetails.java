package pl.bw.oms.domain.model;

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

    private LocalDate dateOfOrderByClient;

    @ManyToOne
    private OrderMethod orderMethod;


}

