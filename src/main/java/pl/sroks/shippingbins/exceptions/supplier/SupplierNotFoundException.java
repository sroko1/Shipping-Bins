package pl.sroks.shippingbins.exceptions.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String msg) {
        super(msg);
    }
}
