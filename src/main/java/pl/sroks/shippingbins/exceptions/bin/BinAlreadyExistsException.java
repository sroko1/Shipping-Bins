package pl.sroks.shippingbins.exceptions.bin;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BinAlreadyExistsException extends RuntimeException {
    public BinAlreadyExistsException(String msg) {
        super(msg);
    }
}
