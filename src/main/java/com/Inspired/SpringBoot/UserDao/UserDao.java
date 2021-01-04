package com.Inspired.SpringBoot.UserDao;

import com.Inspired.SpringBoot.Model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    List<User> selectAllUsers();

    Optional<User> selectUserByUserUid(UUID userUid);

    int updateUser(User user);

    int deleteUserByUserUid(UUID userUid);

    int insertUser(UUID uuid, User user);
}
