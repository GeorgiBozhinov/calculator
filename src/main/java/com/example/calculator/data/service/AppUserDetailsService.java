package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.base_entities.UserRoleEntity;
import com.example.calculator.data.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));

    }



    private UserDetails map(UserEntity userEntity){
        UserDetails result = User.builder().
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).
                authorities(userEntity.getUserRoles().stream().map(this::map).toList()).build();

        return result;
    }


    private GrantedAuthority map(UserRoleEntity userRole){
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}
