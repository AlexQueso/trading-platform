package alex.quesada.trading.controller;

import alex.quesada.trading.controller.dto.UserRequest;
import alex.quesada.trading.controller.dto.UserResponse;
import alex.quesada.trading.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveNewUser(@RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.saveNewUser(user));
    }

    // PUT, DELETE ...
}
