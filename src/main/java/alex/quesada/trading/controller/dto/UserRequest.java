package alex.quesada.trading.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequest {
    String name;
    String password;
}
