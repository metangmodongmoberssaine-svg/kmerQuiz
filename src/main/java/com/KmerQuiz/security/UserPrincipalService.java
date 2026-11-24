package com.KmerQuiz.security;

import com.KmerQuiz.entities.User;
import com.KmerQuiz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPseudoUser(username);
        if (user == null ) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
