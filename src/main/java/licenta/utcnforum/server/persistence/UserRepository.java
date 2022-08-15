package licenta.utcnforum.server.persistence;

import licenta.utcnforum.server.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query(value = "{isUtcn: true}")
    List<User> getAllByAdmin();
}
