package com.moonwalk.abuser.config;


import com.moonwalk.abuser.repository.UserReporitory;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@Configuration
public class WebfluxSecurityConfig {

    private UserReporitory userReporitory;



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
       return http.csrf().disable().httpBasic().securityContextRepository(new WebSessionServerSecurityContextRepository())
               .and()
                .authorizeExchange()
               .pathMatchers(HttpMethod.GET, "/idm/user/").hasRole("user")
               .pathMatchers(HttpMethod.GET, "/idm/users/").hasRole("ADMIN")
               .pathMatchers(HttpMethod.DELETE, "/idm/user/").hasRole("ADMIN")
               .pathMatchers(HttpMethod.PUT, "/idm/user/").hasRole("ADMIN")
               .pathMatchers(HttpMethod.POST, "/idm/user/").hasRole("ADMIN")
               .pathMatchers("/auth/**").authenticated()
               .pathMatchers("/idm/**").authenticated()
               .pathMatchers("/idm/**").access(this::currentUserMatchesPath)
               .anyExchange().permitAll()
               .and()
               .build();
    }


    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
                .map(a -> context.getVariables().get("user").equals(a.getName()))
                .map(AuthorizationDecision::new);
    }


    @Bean
    public ReactiveUserDetailsService userDetailsService(UserReporitory userReporitory) {
        return (username) -> userReporitory.findUserByUsername(username)
                .map(u -> User.withUsername(u.getUsername())
                        .password(u.getPassword())
                        .authorities(u.getRoles().toArray(new String[0]))
                        .accountExpired(!u.isActive())
                        .credentialsExpired(!u.isActive())
                        .disabled(!u.isActive())
                        .accountLocked(!u.isActive())
                        .build()
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
