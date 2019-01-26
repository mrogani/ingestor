package exceptions;

public class IngestorException extends RuntimeException {

    public IngestorException(String msg, Throwable e) {
        super(msg, e);
    }
}
