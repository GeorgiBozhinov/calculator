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
import com.example.calculator.data.model.dto.UpdateUserDTO;
import com.example.calculator.data.model.dto.UserRegisterDTO;
import com.example.calculator.data.repository.UserRepository;
import com.example.calculator.data.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService appUserDetailsService;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService, ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public void init() {

        if ( userRepository.count() == 0 && userRoleRepository.count() == 0 ) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);
            userRole = userRoleRepository.save(userRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of(userRole));
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {

        UserEntity admin = new UserEntity()
                .setUserRoles(roles)
                .setUsername("admin_joro")
                .setFirstName("Test")
                .setLastName("Test")
                .setPassword(passwordEncoder.encode("Topsecret2%"))
                        .setCreationDate(dtf.format(now));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {

        UserEntity moderator = new UserEntity()
                .setUserRoles(roles)
                .setUsername("moderator_joro")
                .setFirstName("Test")
                .setLastName("Test")
                .setPassword(passwordEncoder.encode("Topsecret2%"))
                .setCreationDate(dtf.format(now));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {

        UserEntity user = new UserEntity()
                .setUserRoles(roles)
                .setUsername("user_joro")
                .setFirstName("Test")
                .setLastName("Test")
                .setPassword(passwordEncoder.encode("Topsecret2%"))
                .setCreationDate(dtf.format(now));

        userRepository.save(user);
    }

    public boolean createUserIfNotExist(String username) {

        var userOpt = this.userRepository.findByUsername(username);

        if ( !userOpt.isEmpty() ) {
            return true;
        }

        return false;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserRoleEntity userRoleEntity = userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);

        UserEntity newUser =
                new UserEntity()
                        .setUsername(userRegisterDTO.getUsername())
                        .setFirstName(userRegisterDTO.getFirstName())
                        .setLastName(userRegisterDTO.getLastName())
                        .setUserRoles(List.of(userRoleEntity))
                        .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                        .setCreationDate(dtf.format(now));

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
    public List<UpdateUserDTO> getAllUsers() {

        List<UserEntity> usersEntityList = userRepository.findAll();
        List<UpdateUserDTO> usersDTOList = new ArrayList<>();

        usersEntityList.forEach(userEntity -> usersDTOList.add(modelMapper.map(userEntity, UpdateUserDTO.class)));

        return usersDTOList;
    }

    public UpdateUserDTO getUser(Long userId) {

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        updateUserDTO.setId(userEntity.get().getId());
        updateUserDTO.setUsername(userEntity.get().getUsername());
        updateUserDTO.setFirstName(userEntity.get().getFirstName());
        updateUserDTO.setLastName(userEntity.get().getLastName());
        //updateUserDTO.setUserRoles(userEntity.get().getUserRoles());

        //UpdateUserDTO updateUserDTO = modelMapper.map(userEntity, UpdateUserDTO.class);

        return updateUserDTO;
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {

        UserEntity userEntity = userRepository.findById(updateUserDTO.getId()).orElse(null);

        if ( userEntity != null ) {
            userRepository.updateUser(updateUserDTO.getId(), updateUserDTO.getUsername(), updateUserDTO.getFirstName(), updateUserDTO.getLastName());
        }

    }

    public void deleteUser(long id){
        userRepository.deleteUser(id);
    }


}
