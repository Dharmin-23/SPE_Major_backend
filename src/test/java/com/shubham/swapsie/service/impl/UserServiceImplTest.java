package com.shubham.swapsie.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.LoginRequest;
import com.shubham.swapsie.model.User;
import com.shubham.swapsie.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#createUser(User)}
     */
    @Test
    void testCreateUser() {
        User user = new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou");

        when(userRepository.save((User) any())).thenReturn(user);
        assertSame(user, userServiceImpl.createUser(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(User)}
     */
    @Test
    void testCreateUser2() {
        when(userRepository.save((User) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class,
                () -> userServiceImpl.createUser(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualUsers = userServiceImpl.getUsers();
        assertSame(userList, actualUsers);
        assertTrue(actualUsers.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers2() {
        when(userRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> userServiceImpl.getUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(long)}
     */
    @Test
    void testGetUserById() {
        User user = new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou");

        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        assertSame(user, userServiceImpl.getUserById(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(long)}
     */
    @Test
    void testGetUserById2() {
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(long)}
     */
    @Test
    void testGetUserById3() {
        when(userRepository.findById((Long) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> userServiceImpl.getUserById(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(long, User)}
     */
    @Test
    void testUpdateUser() {
        User user = new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou");

        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((Long) any()))
                .thenReturn(Optional.of(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        assertSame(user,
                userServiceImpl.updateUser(1L, new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(long, User)}
     */
    @Test
    void testUpdateUser2() {
        when(userRepository.save((User) any())).thenThrow(new RuntimeException());
        when(userRepository.findById((Long) any()))
                .thenReturn(Optional.of(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        assertThrows(RuntimeException.class,
                () -> userServiceImpl.updateUser(1L, new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(long, User)}
     */
    @Test
    void testUpdateUser3() {
        User user = mock(User.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setEmail((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setFName((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setLName((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setPassword((String) any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any()))
                .thenReturn(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou"));
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> userServiceImpl.updateUser(1L, new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).findById((Long) any());
        verify(user).setFName((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(long, User)}
     */
    @Test
    void testUpdateUser4() {
        when(userRepository.save((User) any()))
                .thenReturn(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou"));
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        assertThrows(ResourceNotFoundException.class,
                () -> userServiceImpl.updateUser(1L, new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(long, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.User.getFName()" because "updatedUser" is null
        //       at com.shubham.swapsie.service.impl.UserServiceImpl.updateUser(UserServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setEmail((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setFName((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setLName((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(user).setPassword((String) any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any()))
                .thenReturn(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou"));
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        userServiceImpl.updateUser(1L, null);
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any()))
                .thenReturn(Optional.of(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        assertEquals("User F Name L Name with id : 1 deleted successfully", userServiceImpl.deleteUser(1L));
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser2() {
        doThrow(new RuntimeException()).when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any()))
                .thenReturn(Optional.of(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        assertThrows(RuntimeException.class, () -> userServiceImpl.deleteUser(1L));
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser3() {
        User user = mock(User.class);
        when(user.getFName()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(user.getLName()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(user.getId()).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(1L));
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
        verify(user).getFName();
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser4() {
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#login(LoginRequest)}
     */
    @Test
    void testLogin() {
        User user = new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou");

        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(user);
        assertSame(user, userServiceImpl.login(new LoginRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmailAndPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#login(LoginRequest)}
     */
    @Test
    void testLogin2() {
        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> userServiceImpl.login(new LoginRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmailAndPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#login(LoginRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.LoginRequest.getEmail()" because "loginRequest" is null
        //       at com.shubham.swapsie.service.impl.UserServiceImpl.login(UserServiceImpl.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findByEmailAndPassword((String) any(), (String) any()))
                .thenReturn(new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou"));
        userServiceImpl.login(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#login(LoginRequest)}
     */
    @Test
    void testLogin4() {
        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class,
                () -> userServiceImpl.login(new LoginRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmailAndPassword((String) any(), (String) any());
    }
}

