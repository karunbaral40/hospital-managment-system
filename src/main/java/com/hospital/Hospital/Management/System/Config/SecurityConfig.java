package com.hospital.Hospital.Management.System.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
                .username("Admin")
                .password(encoder.encode("Admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("User")
                .password(encoder.encode("User@123#"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // AuthenticationProvider — uses UserDetailsService + PasswordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // AuthenticationManager — needed by AuthController to authenticate
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider,
            JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                // STATELESS — no session, every request must have token
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // Public — no token needed
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/actuator/info").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // ADMIN only actuator
                        .requestMatchers("/actuator/**").hasRole("ADMIN")

                        // GET — both roles
                        .requestMatchers(HttpMethod.GET, "/doctors/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/patients/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/hospital/**")
                        .hasAnyRole("USER", "ADMIN")

                        // POST PUT DELETE — ADMIN only
                        .requestMatchers(HttpMethod.POST, "/doctors/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/doctors/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/doctors/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/patients/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/patients/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/patients/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                // Add JWT filter BEFORE the default username/password filter
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);

        http.headers(headers ->
                headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}