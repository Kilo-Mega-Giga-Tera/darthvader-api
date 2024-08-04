package kr.app.darthvader;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class DarthvaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarthvaderApplication.class, args);
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            String name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!StringUtils.hasText(name)) {
                return Optional.empty();
            }
            return Optional.of(name);
        };
    }

}
