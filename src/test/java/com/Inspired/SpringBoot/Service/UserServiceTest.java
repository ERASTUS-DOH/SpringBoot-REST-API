package com.Inspired.SpringBoot.Service;

import com.Inspired.SpringBoot.Model.User;
import com.Inspired.SpringBoot.UserDao.FakeDataDao;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.swing.text.html.HTML.Tag.I;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;


class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
       userService = new UserService(fakeDataDao);
    }

    @Test
    void ShouldgetAllUsers() {
        UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        User joe = new User(joeUserUid, "Kwame","monata", User.Gender.MALE,25,"joe@gmail.com");
        fakeDataDao.updateUser(joe);

//        ImmutableList<User> users = new ImmutableList.Builder<>User();

//        given(fakeDataDao.selectAllUsers()).willReturn(joe);
        List<User> allUsers =  userService.getAllUsers(Optional.empty());
//        assertThat(allUsers).hasSize(1);
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void insertUser() {
    }
}