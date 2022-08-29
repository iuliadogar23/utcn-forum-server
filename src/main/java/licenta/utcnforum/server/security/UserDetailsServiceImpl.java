package licenta.utcnforum.server.security;

import licenta.utcnforum.server.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        licenta.utcnforum.server.model.User cont = userRepository.findByEmail(username).orElse(null);

        if (cont!=null)
        {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            return new User(
                    cont.getEmail(),
                    cont.getUid(),
                    grantedAuthorities
            );
        }

        return null;
    }

}
