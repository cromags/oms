package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.ClientOrder;
import pl.bw.oms.domain.model.OrderDetails;

public interface DetailsRepository extends JpaRepository<OrderDetails, Long> {
}
