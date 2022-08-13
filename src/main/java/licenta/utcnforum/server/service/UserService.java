package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.persistence.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User upsert(User saveObject) {
        return userRepository.save(saveObject);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(ObjectId id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(User object) {

    }
}
