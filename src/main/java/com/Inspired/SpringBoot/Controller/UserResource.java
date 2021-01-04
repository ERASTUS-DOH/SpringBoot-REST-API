package com.Inspired.SpringBoot.Controller;

import com.Inspired.SpringBoot.Model.User;
import com.Inspired.SpringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path="/api/v1/users")
public class UserResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> fetchUsers(@QueryParam("gender") String gender){
        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{userUid}"
    )
    public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid){
        Optional<User> optionalUser = userService.getUser(userUid);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user " + userUid + " was not found "));
//        return userService.getUser(userUid).get();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user){
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> updateUser(@RequestBody User user){
        int result = userService.updateUser(user);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userUid}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> deleteUser(@PathVariable(name = "userUid") UUID userUid){
        int result = userService.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }

    private ResponseEntity<Integer> getIntegerResponseEntity(int result){
        if(result == 1)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


    class ErrorMessage{
        private String errorMessage;

        public ErrorMessage(String errorMessage){
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public String toString() {
            return "ErrorMessage{" +
                    "message='" + errorMessage + '\'' +
                    '}';
        }
    }
}
