package com.moonwalk.abuser.service;

import com.moonwalk.abuser.model.User;
import com.moonwalk.abuser.repository.UserReporitory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Arrays;

@Service
public class UserService {

    private UserReporitory userReporitory;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserReporitory userReporitory,PasswordEncoder passwordEncoder) {
        this.userReporitory = userReporitory;
        this.passwordEncoder = passwordEncoder;
    }


    public Mono<User> getUserById(String userId){
        return userReporitory.findById(userId).
                switchIfEmpty(Mono.error(new Exception("No Users found with Id: " + userId)));
    }


    public Mono<User> createUsers(@Valid @RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles()==null||user.getRoles().isEmpty())
            user.setRoles(Arrays.asList("user"));
        return userReporitory.save(user);
    }

    public Flux<User> getAllUsers() {
        return userReporitory.findAll();
    }

    public Mono<User> updateUser(User user, String id) {
        return getUserById(id).doOnSuccess(updateUser -> {
            BeanUtils.copyProperties(user,updateUser);
            userReporitory.save(updateUser).subscribe();
        });
    }

    public Mono<Boolean> deleteUser(String id) {
        return getUserById(id).doOnSuccess(updateUser -> {
            userReporitory.deleteById(id).subscribe();
        }).flatMap(user -> Mono.just(Boolean.TRUE));
    }

}
