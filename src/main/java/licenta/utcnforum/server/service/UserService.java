package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.persistence.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User upsert(User saveObject) {
        if (saveObject.getUid() == null)
            saveObject.setUid(UUID.randomUUID());
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
        userRepository.delete(object);
    }

    public List<Post> getAllPostsByAdminUser() {

        return userRepository.getAllByAdmin().stream()
                .map(u -> u.getUserPosts())
                .filter(u -> u != null)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
