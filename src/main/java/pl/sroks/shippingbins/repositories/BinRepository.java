package pl.sroks.shippingbins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sroks.shippingbins.models.bin.Bin;

@Repository
public interface BinRepository extends JpaRepository<Bin, Integer> {

    Bin findBinByName(String name);
    Bin findBinByAmount(int amount);
}
