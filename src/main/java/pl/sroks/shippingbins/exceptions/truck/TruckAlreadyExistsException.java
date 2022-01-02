package pl.sroks.shippingbins.exceptions.truck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TruckAlreadyExistsException extends RuntimeException {
    public TruckAlreadyExistsException(String msg) {
        super(msg);
    }
}
