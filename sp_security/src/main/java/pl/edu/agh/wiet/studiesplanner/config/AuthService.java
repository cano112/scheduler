package pl.edu.agh.wiet.studiesplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.agh.wiet.studiesplanner.model.user.User;

public class AuthService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AuthService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new User(encoder.encode("haslo"), "admin");
    }

}
