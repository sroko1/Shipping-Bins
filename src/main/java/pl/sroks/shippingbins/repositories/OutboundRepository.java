package pl.sroks.shippingbins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sroks.shippingbins.models.outbound.Outbound;


@Repository
public interface OutboundRepository extends JpaRepository<Outbound, Integer> {

}
