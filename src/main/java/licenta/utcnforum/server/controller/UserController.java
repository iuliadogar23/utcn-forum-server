package licenta.utcnforum.server.controller;

import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/id")
    public ResponseEntity<User> getById(@RequestBody ObjectId id)
    {
        return ResponseEntity.ok(userService.findById(id));
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
