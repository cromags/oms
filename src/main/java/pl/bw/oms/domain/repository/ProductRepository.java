package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
