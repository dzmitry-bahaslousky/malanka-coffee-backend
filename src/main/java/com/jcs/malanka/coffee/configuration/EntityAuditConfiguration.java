package com.jcs.malanka.coffee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class EntityAuditConfiguration {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(1L);
    }

}
