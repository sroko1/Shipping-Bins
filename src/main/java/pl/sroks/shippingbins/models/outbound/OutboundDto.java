package pl.sroks.shippingbins.models.outbound;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sroks.shippingbins.models.bin.Bin;
import pl.sroks.shippingbins.models.supplier.Supplier;
import pl.sroks.shippingbins.models.truck.Truck;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OutboundDto {

    private int id;
    private String incomingType;
    private int quantity;
    private String location;
    private LocalDate departure;
    private Bin bin;
    private Truck truck;
    private Supplier supplier;
}
