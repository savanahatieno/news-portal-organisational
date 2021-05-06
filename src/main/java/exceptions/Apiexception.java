package exceptions;

public class Apiexception extends RuntimeException {
    private final int statusCode;

    public Apiexception(int statusCode, String msg) {
        super(msg); //see explanation below
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
