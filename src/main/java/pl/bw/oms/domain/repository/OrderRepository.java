package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.ClientOrder;

public interface OrderRepository extends JpaRepository<ClientOrder, Long> {
}
