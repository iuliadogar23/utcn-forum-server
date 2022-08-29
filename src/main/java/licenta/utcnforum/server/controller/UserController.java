package licenta.utcnforum.server.controller;

import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.security.AuthenticationResponse;
import licenta.utcnforum.server.security.JwtTokenProvider;
import licenta.utcnforum.server.security.UserDetailsServiceImpl;
import licenta.utcnforum.server.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable ObjectId id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) throws Exception {

        User loggedUser = userService.upsert(user);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getUid()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(loggedUser, jwtTokenProvider.generateToken(userDetails));

        return ResponseEntity.ok(authenticationResponse);

    }

    @PostMapping("/upsert")
    public ResponseEntity<User> upsert(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.upsert(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody User user)
    {
        userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
