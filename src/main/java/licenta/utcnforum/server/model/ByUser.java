package licenta.utcnforum.server.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ByUser implements Serializable {

    @Field("id")
    private UUID id;

    private String displayName;


}
