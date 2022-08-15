package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class ByUser implements Serializable {

    @Field("id")
    private ObjectId id;

    private String uid;

    private String displayName;


}
