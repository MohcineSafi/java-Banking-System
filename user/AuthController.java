package user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return authService.authenticate(user.getUsername(), user.getPassword());
    }
}
