package licenta.utcnforum.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Document("post")
public class Post implements Serializable {

    @Id
    private UUID id;

    private String text;

    private ByUser user;

    private Long date;

    private int likes;

    private int dislikes;

    private List<Comment> comments;

    private List<Category> categories;

}
