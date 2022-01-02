package pl.sroks.shippingbins.exceptions.bin;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BinNotFoundException extends RuntimeException {
    public BinNotFoundException(String msg) {
        super(msg);
    }
}
