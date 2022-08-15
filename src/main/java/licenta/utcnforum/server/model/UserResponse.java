package licenta.utcnforum.server.model;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

public class UserResponse {
    private ObjectId id;

    private String uid;

    private UUID uuid;

    private String displayName;

    private String email;

    private boolean isUtcn;

    private List<Post> likedPosts;

    private List<Category> likedCategory;

    private List<Post> userPosts;
}
