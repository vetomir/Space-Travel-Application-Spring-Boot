package pl.gregorymartin.spacetravelagency;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gregorymartin.spacetravelagency.user.model.UserRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repo;

    public UserDetailsServiceImpl(final UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return repo.findAllByUsername(s);
    }
}
