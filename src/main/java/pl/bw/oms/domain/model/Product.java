package pl.bw.oms.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    private String productName;
    private BigDecimal supplierPrice;
    private BigDecimal myPrice;

    @Column(length = 500) @Size(max = 500)
    private String description;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private ProductCategory productCategory;

    @OneToMany
    @JoinColumn(name = "product_id")
    Set<OrderDetails> orderDetails = new HashSet<>();

    public Product() {
    }

    public Product(String productName, BigDecimal supplierPrice, BigDecimal myPrice, String description, Supplier supplier, ProductCategory productCategory) {
        this.productName = productName;
        this.supplierPrice = supplierPrice;
        this.myPrice = myPrice;
        this.description = description;
        this.supplier = supplier;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public BigDecimal getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(BigDecimal myPrice) {
        this.myPrice = myPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id != null ? id.equals(product.id) : product.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", supplierPrice=" + supplierPrice +
                ", myPrice=" + myPrice +
                ", description='" + description + '\'' +
                ", supplier=" + supplier +
                ", productCategory=" + productCategory +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

