package alex.quesada.trading.exception;

public class SecurityNotFoundException extends RuntimeException {
    public SecurityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
