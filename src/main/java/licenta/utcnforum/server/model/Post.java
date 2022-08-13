package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document("post")
public class Post implements Serializable {

    @Id
    private ObjectId id;

    private String text;

    private ByUser user;

    private Long date;

    private int likes;

    private int dislike;

    private List<Comment> comments;

}
