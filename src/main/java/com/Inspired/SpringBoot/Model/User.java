package com.Inspired.SpringBoot.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class User {

   public enum Gender{
        MALE, FEMALE
    }
    // User
    private final UUID userUid;
    private final String firstname;
    private final String lastname;
    private final Gender gender;
    private final Integer age;
    private final String email;

//    public User() {
//    }

    public User(@JsonProperty("userUid") UUID userUid,
                @JsonProperty("firstname") String firstname,
                @JsonProperty("lastname") String lastname,
                @JsonProperty("gender") Gender gender,
                @JsonProperty("age") Integer age,
                @JsonProperty("email") String email) {
        this.userUid = userUid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }


    @JsonProperty("id")
    public UUID getUserUid() {
        return userUid;
    }

    @JsonIgnore
    public String getFirstname() {
        return firstname;
    }

    @JsonIgnore
    public String getLastname() {
        return lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public int getDateOfBirth(){
        return LocalDate.now().minusYears(age).getYear();
    }

//    public void setUserUid(UUID userUid) {
//        this.userUid = userUid;
//    }

    public static User newUser(UUID userUid, User user){
        return new User(userUid,user.getFirstname(), user.getLastname(), user.getGender(), user.getAge(),user.getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "userUid=" + userUid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
