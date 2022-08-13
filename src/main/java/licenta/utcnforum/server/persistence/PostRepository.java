package licenta.utcnforum.server.persistence;

import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    @Query(value = "{email: ?0}")
    List<Post> getAllByUtcnUser(String utcnEmail);

    @Query(value = "{ 'comments': {'$in' : ?0 }}")
    List<Post> getAllByCategories(List<Category> categories);

}
