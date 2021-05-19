package pl.adcom.exceptions;

public abstract class ReservationCustomException extends RuntimeException {

    public abstract int getCode();

    public ReservationCustomException(String message) {
        super(message);
    }
}
