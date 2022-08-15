package licenta.utcnforum.server.persistence;

import licenta.utcnforum.server.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends MongoRepository<Post, UUID> {

    @Query(value = "{displayName: ?0}")
    List<Post> getAllByUserDisplayName(String displayName);


    @Query(value = "{ 'categories': { $elemMatch: { '_id' : {'$in' : ?0 } } }}")
    List<Post> getAllByCategories(List<UUID> categories);



}
