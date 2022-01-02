package pl.sroks.shippingbins.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sroks.shippingbins.models.inbound.Inbound;

import java.util.Optional;

@Repository
public interface InboundRepository extends JpaRepository<Inbound, Integer> {

    Optional<Inbound> findById(int id);
    Inbound findInboundByQuantity(int quantity);

    void save(Optional<Inbound> inbound);
}
