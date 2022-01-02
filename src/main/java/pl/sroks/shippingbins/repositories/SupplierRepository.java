package pl.sroks.shippingbins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sroks.shippingbins.models.supplier.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Supplier findSupplierByName(String name);
}
