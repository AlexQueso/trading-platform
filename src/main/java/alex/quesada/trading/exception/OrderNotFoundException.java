package alex.quesada.trading.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}