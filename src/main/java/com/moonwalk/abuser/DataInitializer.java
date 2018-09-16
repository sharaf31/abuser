package com.moonwalk.abuser;

import com.moonwalk.abuser.model.User;
import com.moonwalk.abuser.repository.UserReporitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {


    private final UserReporitory reporitory;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserReporitory reporitory, PasswordEncoder passwordEncoder) {
        this.reporitory = reporitory;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
//        this.reporitory
//                .deleteAll()
//
//                .thenMany(
//                        Flux
//                                .just("user", "admin")
//                                .flatMap(
//                                        username -> {
//                                            List<String> roles = "user".equals(username)
//                                                    ? Arrays.asList("user")
//                                                    : Arrays.asList("user", "ADMIN");
//                                            User user = User.builder()
//                                                    .roles(roles)
//                                                    .username(username)
//                                                    .password(passwordEncoder.encode("password"))
//                                                    .email(username + "@test.com")
//                                                    .build();
//                                            return this.reporitory.save(user);
//                                        }
//                                )
//                )
//                .log()
//                .subscribe(
//                        null,
//                        null,
//                        () -> log.info("done reporitory initialization...")
//                );
    }
}
