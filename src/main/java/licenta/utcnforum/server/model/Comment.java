package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class Comment implements Serializable {

    @Field("id")
    private ObjectId id;

    private String text;

    private Long date;

    private ByUser user;

}
