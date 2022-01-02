package pl.sroks.shippingbins.exceptions.truck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TruckNotFoundException  extends RuntimeException {
    public TruckNotFoundException(String msg) {
        super(msg);
    }
}
