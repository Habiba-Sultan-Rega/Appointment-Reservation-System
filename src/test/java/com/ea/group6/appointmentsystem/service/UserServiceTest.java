package com.ea.group6.appointmentsystem.service;

import com.ea.group6.appointmentsystem.domain.Gender;
import com.ea.group6.appointmentsystem.domain.User;
import com.ea.group6.appointmentsystem.repository.ReservationRepository;
import com.ea.group6.appointmentsystem.repository.UserRepository;
import com.ea.group6.appointmentsystem.service.reservation.ReservationServiceImpl;
import com.ea.group6.appointmentsystem.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testFindUserByName(){

        User user = stubUser();
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        User user1 = userService.findUserByUsername(user.getUsername());
        assertThat(user1.getUsername()).isEqualTo(user.getUsername());
        verify(userRepository, times(1)).findUserByUsername(user.getUsername());

    }

    public User stubUser() {
        User user = new User();
        user.setUsername("prossie");
        user.setPassword("secret");
        user.setFirstName("pna");
        user.setEmailAddress("pnaki@miu");
        user.setLastName("naki");
        user.setPhone("2674");
        user.setGender(Gender.FEMALE);

        return user;
    }
}
