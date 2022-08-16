package licenta.utcnforum.server.persistence;

import licenta.utcnforum.server.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Optional<Category> findByUuid(UUID uuid);

}
