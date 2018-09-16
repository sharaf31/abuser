package com.moonwalk.abuser.repository;

import com.moonwalk.abuser.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserReporitory extends ReactiveMongoRepository<User,String> {

    Mono<User> findUserByUsername(String userName);
}
