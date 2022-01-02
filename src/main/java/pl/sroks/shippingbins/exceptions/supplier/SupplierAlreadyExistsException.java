package pl.sroks.shippingbins.exceptions.supplier;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SupplierAlreadyExistsException extends RuntimeException{
    public SupplierAlreadyExistsException(String msg) {
        super(msg);
    }
}
