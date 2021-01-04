package com.Inspired.SpringBoot.UserDao;

import com.Inspired.SpringBoot.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//import static org.junit.jupiter.api.Assertions.*;

class FakeDataDaoTest {
    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void ShouldselectAllUsers() {
       List<User> users = fakeDataDao.selectAllUsers();
//       assertThat(users).hasSize(1);
       User user = users.get(0);
       assertThat(user.getAge()).isEqualTo(22);

    }

    @Test
    void ShouldselectUserByUserUid() {
        UUID annaUserUuid = UUID.randomUUID();
        User user = new User(annaUserUuid, "anna","montana", User.Gender.FEMALE,30,"anna@gmail.com");
        fakeDataDao.insertUser(annaUserUuid,user);
//        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
       Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserUuid);
       assertThat(annaOptional.isPresent()).isTrue();
       assertThat(annaOptional.get()).isEqualToComparingFieldByField(user);
    }

    @Test
    public void ShouldNotSelectUserByRandomUserUid(){
        Optional<User> user = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
    }
    @Test
    void updateUser() {
        UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        User joe = new User(joeUserUid, "Kwame","monata", User.Gender.MALE,25,"joe@gmail.com");
        fakeDataDao.updateUser(joe);
        Optional<User> user = fakeDataDao.selectUserByUserUid(joeUserUid);
        assertThat(user.isPresent()).isTrue();
//        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(user.get()).isEqualToComparingFieldByField(joe);
    }

    @Test
    void deleteUserByUserUid() {
        UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        fakeDataDao.deleteUserByUserUid(joeUserUid);
        assertThat(fakeDataDao.selectUserByUserUid(joeUserUid).isPresent()).isFalse();
//        assertThat(fakeDataDao.selectAllUsers()).isEmpty;
    }

    @Test
    void insertUser() {
        UUID userUid = UUID.randomUUID();
        User user = new User(userUid,"kwame","doh", User.Gender.MALE,24,"eli@gmail.com");
        fakeDataDao.insertUser(userUid,user);
        assertThat(fakeDataDao.insertUser(userUid,user)).isEqualTo(1);
    }
}