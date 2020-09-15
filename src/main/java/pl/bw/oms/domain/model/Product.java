package pl.bw.oms.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal supplierPrice;
    private BigDecimal myPrice;
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

    public Product(String name, BigDecimal supplierPrice, BigDecimal myPrice, String description, Supplier supplier, ProductCategory productCategory) {
        this.name = name;
        this.supplierPrice = supplierPrice;
        this.myPrice = myPrice;
        this.description = description;
        this.supplier = supplier;
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

