package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
