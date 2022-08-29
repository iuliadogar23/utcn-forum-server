package licenta.utcnforum.server.security;

import licenta.utcnforum.server.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private User user;

    private String token;

}
