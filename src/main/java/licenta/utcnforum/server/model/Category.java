package licenta.utcnforum.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document("category")
public class Category implements Serializable {

    @Id
    private UUID id;

    private String name;


}
