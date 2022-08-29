package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.Category;
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
        User user = userRepository.findByUid(saveObject.getUid()).orElse(null);

        if (user == null)
            saveObject.setUuid(UUID.randomUUID());
        if (saveObject.getId()==null && user!=null )
            return user;

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

        return userRepository.getAllByAdmin().stream() //list<User>
                .map(u -> u.getUserPosts()) //pentru fiecare User din lista -> List<Post> userPosts a userului admin -> rez. final: list<List<Post>>
                .filter(u -> u != null) //elimiam lista de posturi userilor fara nimic postat
                .flatMap(List::stream) //List<List<Post>> -> List<Post> all together in a big list
                .collect(Collectors.toList());
    }

    public List<Category> getFollowCategoriesForUser(String uuid)
    {
        User loggedUser = userRepository.findByUuid(UUID.fromString(uuid)).orElseThrow();
        return loggedUser.getLikedCategory();
    }

    public User getByUid(String uid)
    {
        return userRepository.findByUid(uid).orElse(null);
    }
}
