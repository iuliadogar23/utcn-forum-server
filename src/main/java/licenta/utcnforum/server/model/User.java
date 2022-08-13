package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document("user")
public class User implements Serializable {

    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isUtcn;

    private List<Post> likedPosts;

    private List<Category> likedCategory;

    private List<Post> userPosts;

}
