package pl.sroks.shippingbins.models.inbound;


import lombok.*;
import pl.sroks.shippingbins.models.bin.Bin;
import pl.sroks.shippingbins.models.supplier.Supplier;
import pl.sroks.shippingbins.models.truck.Truck;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

///
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "inbound")
public class Inbound implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "INCOMING_TYPE")
    private String incomingType;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "ARRIVAL")
    private LocalDate arrived;


    @ManyToOne
    private Bin bin;
    @ManyToOne
    private Truck truck;
    @ManyToOne
    private Supplier supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Inbound inbound = (Inbound) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, inbound.id).append(quantity, inbound.quantity).append(incomingType, inbound.incomingType).append(location, inbound.location).append(arrived, inbound.arrived).append(bin, inbound.bin).append(truck, inbound.truck).append(supplier, inbound.supplier).isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(id).append(incomingType).append(quantity).append(location).append(arrived).append(bin).append(truck).append(supplier).toHashCode();
    }
}
