package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.UserRequest;
import alex.quesada.trading.controller.dto.UserResponse;
import alex.quesada.trading.domain.User;
import alex.quesada.trading.exception.UserNotFoundException;
import alex.quesada.trading.infrastructure.UserRepository;
import alex.quesada.trading.service.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserResponseById(String userId) {
        return userMapper.userToUserResponse(getUserById(userId));
    }

    public User getUserById(String userId) {
        log.info("Retrieving user with id {} from database... ", userId);
        try {
            return userRepository.findById(userId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("User with id " + userId + " was not found.");
        }
    }

    public List<UserResponse> getAll() {
        log.info("Retrieving all users from database... ");
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    public UserResponse saveNewUser(UserRequest userRequest) {
        log.info("Saving new user {}... ", userRequest.toString());
        User user = userMapper.userRequestToUser(userRequest);
        return userMapper.userToUserResponse(userRepository.save(user));
    }
}
