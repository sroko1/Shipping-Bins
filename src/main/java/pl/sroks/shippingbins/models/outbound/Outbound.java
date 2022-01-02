package pl.sroks.shippingbins.models.outbound;


import lombok.*;
import pl.sroks.shippingbins.models.bin.Bin;
import pl.sroks.shippingbins.models.supplier.Supplier;
import pl.sroks.shippingbins.models.truck.Truck;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "outbound")
public class Outbound implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "OUTCOMING_TYPE")
    private String incomingType;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "DEPARTURE")
    private LocalDate departure;

    @ManyToOne
    private Bin bin;
    @ManyToOne
    private Truck truck;
    @ManyToOne
    private Supplier supplier;
}