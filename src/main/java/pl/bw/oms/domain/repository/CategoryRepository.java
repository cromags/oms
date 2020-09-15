package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
}
