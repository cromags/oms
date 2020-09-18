package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.OrderMethod;

public interface OrderMethodsRepository extends JpaRepository<OrderMethod, Long> {
}
