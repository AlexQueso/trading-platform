package alex.quesada.trading.controller;

import alex.quesada.trading.exception.OrderNotFoundException;
import alex.quesada.trading.exception.SecurityNotFoundException;
import alex.quesada.trading.exception.TradeNotFoundException;
import alex.quesada.trading.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, SecurityNotFoundException.class,
            OrderNotFoundException.class, TradeNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> resourceNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
