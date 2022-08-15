package licenta.utcnforum.server.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document("category")
public class Category implements Serializable {

    @Id
    private ObjectId id;

    private UUID uid;

    private String name;


}
