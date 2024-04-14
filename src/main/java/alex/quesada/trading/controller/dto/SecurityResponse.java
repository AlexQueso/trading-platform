package alex.quesada.trading.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityResponse {
    String id;
    String name;
}
