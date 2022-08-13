package licenta.utcnforum.server.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Comment implements Serializable {

    @Transient
    private UUID id = UUID.randomUUID();

    private String text;

    private Long date;

    private ByUser user;

}
