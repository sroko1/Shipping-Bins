package pl.sroks.shippingbins.models.bin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BinDto {

    private int id;
    private String name;
    private double width;
    private double length;
    private double height;
    private double price;
    private int amount;
    private double volume;
    private double leasingPrice;

}
