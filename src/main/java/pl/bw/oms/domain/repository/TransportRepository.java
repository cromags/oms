package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}
