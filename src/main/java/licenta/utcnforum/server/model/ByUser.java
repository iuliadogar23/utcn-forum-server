package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ByUser implements Serializable {

    @Field("id")
    private ObjectId id;

    private String uid;

    private UUID uuid;

    private boolean utcn;

    private String photoURL;

    private String displayName;


}
