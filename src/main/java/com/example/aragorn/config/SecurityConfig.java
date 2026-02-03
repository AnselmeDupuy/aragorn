package com.example.aragorn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.aragorn.model.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired private CustomUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                                            .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/error").permitAll()
                                            .requestMatchers("/home").authenticated()
                                            .requestMatchers("/champ-de-bataille").hasRole("CHEVALIER")
                                            .requestMatchers("/armurerie").hasRole("CHEVALIER")
                                            .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable())
        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll() )
        .logout(logout -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .permitAll() )
        .sessionManagement(session -> session.maximumSessions(1))   // 1 session par utilisateur
        .rememberMe(rememberMe -> rememberMe.key("cleUniqueEtSecrete")
                                            .tokenValiditySeconds(86400 * 365) // Durée en secondes) 
                                            .rememberMeParameter("remember-me")) // nom du champ dans le formulaire
        .exceptionHandling(ex -> ex.accessDeniedPage("/error403"))
        ;
        return http.build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }   
}
