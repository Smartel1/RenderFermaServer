package ru.smartel.renderferma.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.smartel.renderferma.server.entity.UserEntity;
import ru.smartel.renderferma.server.repository.UserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (null == user) {
            throw new UsernameNotFoundException("no users with email " + email);
        }
        return new UserPrincipal(user);
    }
}
