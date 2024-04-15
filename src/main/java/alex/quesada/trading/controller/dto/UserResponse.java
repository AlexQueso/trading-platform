package alex.quesada.trading.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    String id;
    String name;
}
