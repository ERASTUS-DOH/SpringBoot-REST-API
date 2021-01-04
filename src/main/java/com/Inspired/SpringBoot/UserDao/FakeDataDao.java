package com.Inspired.SpringBoot.UserDao;

import com.Inspired.SpringBoot.Model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.Inspired.SpringBoot.Model.User.Gender.MALE;

@Repository
public class FakeDataDao implements UserDao{

    private Map<UUID, User> database;

    public FakeDataDao(){
        database = new HashMap<>();
        UUID userUid = UUID.randomUUID();
        database.put(userUid, new User(userUid, "Joe", "Jones", MALE, 22,"joe.jones@gmail.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUserByUserUid(UUID userUid) {
        return Optional.ofNullable(database.get(userUid));
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(),user);
        return 1;
    }

    @Override
    public int deleteUserByUserUid(UUID userUid) {
        database.remove(userUid);
        return 1;
    }

    @Override
    public int insertUser(UUID uuid, User user) {
        database.put(uuid, user);
        return 1 ;
    }
}
