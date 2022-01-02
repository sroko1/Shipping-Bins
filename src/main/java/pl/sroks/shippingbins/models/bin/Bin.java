package pl.sroks.shippingbins.models.bin;


import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "bin")
public class Bin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "WIDTH")
    private double width;
    @Column(name = "LENGTH")
    private double length;
    @Column(name = "HEIGHT")
    private double height;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "VOLUME")
    private double volume;
    @Column(name = "LEASING_PRICE")
    private double leasingPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Bin bin = (Bin) o;

        return new EqualsBuilder().append(id, bin.id).append(width, bin.width).append(length, bin.length).append(height, bin.height).append(price, bin.price).append(amount, bin.amount).append(volume, bin.volume).append(leasingPrice, bin.leasingPrice).append(name, bin.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(width).append(length).append(height).append(price).append(amount).append(volume).append(leasingPrice).toHashCode();
    }
}
