package pl.sroks.shippingbins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sroks.shippingbins.models.truck.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

    Truck findTruckByRegNumber(String regNumber);
}
