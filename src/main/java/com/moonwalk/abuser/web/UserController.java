package com.moonwalk.abuser.web;

import com.moonwalk.abuser.model.User;
import com.moonwalk.abuser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/idm")
public class UserController {


    private Logger log= LoggerFactory.getLogger(UserController.class);

    private UserService userService;


    @Autowired
    public void UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable (value = "id") String userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User>  createUsers(@Valid @RequestBody User user) {
         return userService.createUsers(user);
    }


    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> updateUser(@RequestBody User user, @PathVariable String id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Boolean> deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }
}