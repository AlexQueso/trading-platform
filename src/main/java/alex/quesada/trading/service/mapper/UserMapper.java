package alex.quesada.trading.service.mapper;

import alex.quesada.trading.controller.dto.UserRequest;
import alex.quesada.trading.controller.dto.UserResponse;
import alex.quesada.trading.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponse(User user);
    User userRequestToUser(UserRequest userRequest);
}
