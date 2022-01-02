package pl.sroks.shippingbins.exceptions;

public class FileDoesNotExistException extends RuntimeException {
    public FileDoesNotExistException(String msg) {
        super(msg);
    }
}