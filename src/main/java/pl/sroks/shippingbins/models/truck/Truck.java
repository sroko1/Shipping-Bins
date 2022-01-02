package pl.sroks.shippingbins.models.truck;

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
@Table(name = "truck")
public class Truck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "TRAILER_TYPE")
    private String trailerType;
    @Column(name = "REG_NUMBER")
    private String regNumber;
    @Column(name = "TRAILER_MAX_VOLUME")
    private double trailerMaxVolume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        return new EqualsBuilder().append(id, truck.id).append(trailerMaxVolume, truck.trailerMaxVolume).append(trailerType, truck.trailerType).append(regNumber, truck.regNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(trailerType).append(regNumber).append(trailerMaxVolume).toHashCode();
    }
}
