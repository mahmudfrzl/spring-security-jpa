package io.javabrains.springsecurityjpa.service;

import io.javabrains.springsecurityjpa.model.MyUserDetails;
import io.javabrains.springsecurityjpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not found" + username));
        return user.map(MyUserDetails::new).get();
    }
}
