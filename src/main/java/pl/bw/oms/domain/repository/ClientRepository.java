package pl.bw.oms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.oms.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
