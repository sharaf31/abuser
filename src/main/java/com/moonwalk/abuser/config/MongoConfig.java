package com.moonwalk.abuser.config;

import com.moonwalk.abuser.model.Username;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

@Configuration
public class MongoConfig {

    @Bean
    public AuditorAware<Username> auditor() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .log()
                .filter(a -> a != null && a.isAuthenticated())
                .map(auth -> new Username(auth.getName()))
                .switchIfEmpty(Mono.empty())
                .blockOptional();
    }
}
