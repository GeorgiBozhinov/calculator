package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.base_entities.UserRoleEntity;
import com.example.calculator.data.enums.UserRoleEnum;
import com.example.calculator.data.model.dto.UpdateUserDTO;
import com.example.calculator.data.repository.UserRepository;
import com.example.calculator.data.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserRoleRepository mockUserRoleRepository;

    private PasswordEncoder mockPasswordEncoder;

    private UserDetailsService mockAppUserDetailsService;

    @Mock
    private ModelMapper mockModelMapper;

    private UserService toTest;

    @BeforeEach
    void setUp() {

        toTest = new UserService(
                mockUserRepository,
                mockUserRoleRepository,
                mockPasswordEncoder,
                mockAppUserDetailsService,
                mockModelMapper = new ModelMapper()
        );
    }

    @Test
    void testCheckIfUserExist_UserExist() {

        UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test1");
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testov");
        userEntity.setPassword("testovam");
        userEntity.setUserRoles(List.of(userRole));

        when(mockUserRepository.findByUsername(anyString())).thenReturn(Optional.of(userEntity));
//        doReturn(Optional.of(userEntity)).when(mockUserRepository.findByUsername(userEntity.getUsername()));

        //act
        boolean user = toTest.createUserIfNotExist(userEntity.getUsername());

        boolean expectedExist = true;

        //assert
        Assertions.assertEquals(expectedExist, user);
    }

    @Test
    void testGetAllUsers_UserExist() {

        UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test1");
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testov");
        userEntity.setPassword("testovam");
        userEntity.setUserRoles(List.of(userRole));

        when(mockUserRepository.findAll()).thenReturn(List.of(userEntity));

        List<UpdateUserDTO> expected = new ArrayList<>();

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUsername("test1");
        updateUserDTO.setFirstName("Test");
        updateUserDTO.setLastName("Testov");
        updateUserDTO.setUserRoles(List.of(userRole));

        expected.add(updateUserDTO);

        //act
        List<UpdateUserDTO> actual = toTest.getAllUsers();
       // List<UpdateUserDTO> listOfUsers = List.of(this.mockModelMapper.map(userEntity, UpdateUserDTO.class));

        //assert            (expected, actual)
        //Assertions.assertEquals(listOfUsersExpected, listOfUsers);

        for ( int i = 0; i < actual.size(); i++ ) {
            Assertions.assertEquals(expected.get(i).getUsername(), actual.get(i).getUsername());
            Assertions.assertEquals(expected.get(i).getFirstName(), actual.get(i).getFirstName());
            Assertions.assertEquals(expected.get(i).getLastName(), actual.get(i).getLastName());
           // Assertions.assertEquals(listOfUsers.get(i).get(), listOfUsersExpected.get(i).getUsername());
        }


    }

    @Test
    void testGetUser_UserExist() {

        UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1);
        userEntity.setUsername("test1");
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testov");
        userEntity.setPassword("testovam");
        userEntity.setUserRoles(List.of(userRole));

        when(mockUserRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));

        UpdateUserDTO expected = this.mockModelMapper.map(userEntity, UpdateUserDTO.class);
        //act
        UpdateUserDTO actual = toTest.getUser(1L);

        //assert
        Assertions.assertEquals(expected.getUsername(), actual.getUsername());
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getLastName(), actual.getLastName());
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    @Test
    void testUpdateUser_UserExist(){
        UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1);
        userEntity.setUsername("test1");
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testov");
        userEntity.setPassword("testovam");
        userEntity.setUserRoles(List.of(userRole));

        when(mockUserRepository.findById(any())).thenReturn(Optional.of(userEntity));

        //act
        UpdateUserDTO updateUserDTO = mockModelMapper.map(userEntity, UpdateUserDTO.class);
        updateUserDTO.setUsername("test_changed");
        toTest.updateUser(updateUserDTO);

    }

}
