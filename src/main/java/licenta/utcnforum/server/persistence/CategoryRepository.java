package licenta.utcnforum.server.persistence;

import licenta.utcnforum.server.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
}
