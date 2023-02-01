package com.example.calculator.data.service;

//import com.example.calculator.data.base_entities.UserEntity;
//import com.example.calculator.data.base_entities.UserRoleEntity;
//import com.example.calculator.data.enums.UserRoleEnum;
//import com.example.calculator.data.repository.UserRepository;
//import com.example.calculator.data.repository.UserRoleRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void init(){
//        if(userRepository.count() == 0 && userRoleRepository.count() == 0){
//            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
//
//            adminRole = userRoleRepository.save(adminRole);
//
//
//            initAdmin(List.of(adminRole));
//            initUser(List.of());
//        }
//    }
//
//
//    private void initAdmin(List<UserRoleEntity> roles){
//
//        UserEntity admin = new UserEntity().
//                setUserRoles(roles).
//                setPassword(passwordEncoder.encode("topsecret")).
//                setUsername("joro");
//
//        userRepository.save(admin);
//    }
//
//    private void initUser(List<UserRoleEntity> roles){
//        UserEntity user = new UserEntity().
//                setUserRoles(roles).
//                setPassword(passwordEncoder.encode("topsecret")).
//                setUsername("joro_user");
//
//        userRepository.save(user);
//    }
//
//}

import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.base_entities.UserRoleEntity;
import com.example.calculator.data.enums.UserRoleEnum;
import com.example.calculator.data.interfaces.UserInterface;
import com.example.calculator.data.model.dto.UserRegisterDTO;
import com.example.calculator.data.repository.UserRepository;
import com.example.calculator.data.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService appUserDetailsService;

    //private final UserDetailsService appUserDetailsService;

    //private String adminPass;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
    }

    public void init() {

        if ( userRepository.count() == 0 && userRoleRepository.count() == 0 ) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of());
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {

        UserEntity admin = new UserEntity().
                setUserRoles(roles).
                setUsername("admin_joro").
                setPassword(passwordEncoder.encode(""));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {

        UserEntity moderator = new UserEntity().
                setUserRoles(roles).
                setUsername("moderator_joro").
                setPassword(passwordEncoder.encode(""));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {

        UserEntity user = new UserEntity().
                setUserRoles(roles).
                setUsername("user_joro").
                setPassword(passwordEncoder.encode(""));

        userRepository.save(user);
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserEntity newUser =
                new UserEntity().
                        setUsername(userRegisterDTO.getUsername()).
                        setFirstName(userRegisterDTO.getFirstName()).
                        setLastName(userRegisterDTO.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    /**
     * @return all users and specific columns
     */
    public List<UserInterface> getAllUsers(){
        //List<UserInterface> usersDTOList = userRepository.findAllUsers();

        return  userRepository.findAllUsers();
    }
}
