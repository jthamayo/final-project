package io.github.jthamayo.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.findByUsernameOrEmail(username, username)
		.orElseThrow(() -> new UsernameNotFoundException("user not found with " + username + " credentials"));
	return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
	User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found: " + id));
	return UserPrincipal.create(user);
    }
}
