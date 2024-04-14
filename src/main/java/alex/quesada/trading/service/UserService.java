package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.UserRequest;
import alex.quesada.trading.controller.dto.UserResponse;
import alex.quesada.trading.domain.User;
import alex.quesada.trading.infrastructure.UserRepository;
import alex.quesada.trading.service.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<UserResponse> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::userToUserResponse);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    public UserResponse saveNewUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        return userMapper.userToUserResponse(userRepository.save(user));
    }
}
