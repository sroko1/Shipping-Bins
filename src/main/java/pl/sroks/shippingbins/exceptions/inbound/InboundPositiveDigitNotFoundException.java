package pl.sroks.shippingbins.exceptions.inbound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InboundPositiveDigitNotFoundException extends RuntimeException {
    public InboundPositiveDigitNotFoundException(String msg) {
        super(msg);
    }
}
