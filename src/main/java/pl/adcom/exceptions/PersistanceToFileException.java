package pl.adcom.exceptions;

public class PersistanceToFileException extends ReservationCustomException{

    private final int code = 103;

    public PersistanceToFileException(String fileName, String operation, String message){
        super(String.format("Unable to perform %s on %s (%s)", operation, fileName, message));
    }

    @Override
    public int getCode() {
        return code;
    }
}
