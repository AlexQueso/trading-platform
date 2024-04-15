package alex.quesada.trading.exception;

public class TradeNotFoundException extends RuntimeException {
    public TradeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}