package ru.netology.sql4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Включаем поддержку аннотаций @Secured, @RolesAllowed и @PreAuthorize/@PostAuthorize
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        // Остальные запросы требуют аутентификации
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // Создаем нескольких пользователей с разными ролями.
        UserDetails readUser = User.withDefaultPasswordEncoder()
                .username("readUser")
                .password("password")
                .roles("READ")
                .build();

        UserDetails writeUser = User.withDefaultPasswordEncoder()
                .username("writeUser")
                .password("password")
                .roles("WRITE")
                .build();

        UserDetails writerAndDeleter = User.withDefaultPasswordEncoder()
                .username("adminUser")
                .password("password")
                // Пользователь adminUser получает сразу две роли: WRITE и DELETE
                .roles("WRITE", "DELETE")
                .build();

        return new InMemoryUserDetailsManager(readUser, writeUser, writerAndDeleter);
    }
}
