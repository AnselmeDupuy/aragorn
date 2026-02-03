package com.example.aragorn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configuration complète de la sécurité avec gestion des routes et sessions
     * Authentification simplifiée: connexion par name uniquement
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                // ===== AUTORISATION DES ROUTES =====
                .authorizeHttpRequests(authz -> authz
                // Routes publiques
                .requestMatchers("/", "/home", "/login", "/register").permitAll()
                // API publique des chevaliers
                .requestMatchers("/api/chevaliers/**").permitAll()
                // Ressources statiques
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                // Routes des chevaliers connectés
                .requestMatchers("/chevalier/**").hasRole("CHEVALIER")
                .requestMatchers("/chevalier/profil/**").hasRole("CHEVALIER")
                .requestMatchers("/chevalier/equipement/**").hasRole("CHEVALIER")
                // Toutes les autres routes nécessitent l'authentification
                .anyRequest().authenticated()
                )
                // ===== FORMULAIRE DE LOGIN =====
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .usernameParameter("name") // Le chevalier se connecte avec son "name"
                .passwordParameter("password") // Le formulaire utilise "password"
                .permitAll()
                .defaultSuccessUrl("/chevalier/dashboard", true)
                .failureUrl("/login?error")
                )
                // ===== GESTION DE LA DÉCONNEXION =====
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                )
                // ===== GESTION DES SESSIONS =====
                .sessionManagement(session -> session
                // Maximum 3 sessions simultanées par chevalier
                .maximumSessions(3)
                // URL de redirection si session expirée
                .expiredUrl("/login?expired")
                )
                // ===== PROTECTION CONTRE LES ATTAQUES =====
                .csrf(csrf -> csrf
                // Désactiver CSRF pour les APIs REST
                .ignoringRequestMatchers("/api/**")
                )
                // ===== ENTÊTE DE SÉCURITÉ =====
                .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}
