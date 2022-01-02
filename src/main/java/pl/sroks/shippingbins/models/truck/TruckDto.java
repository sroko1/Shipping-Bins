package pl.sroks.shippingbins.models.truck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TruckDto {

    private int id;
    private String trailerType;
    private String regNumber;
    private double trailerMaxVolume;
}
