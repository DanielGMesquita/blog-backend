package com.danielmesquita.blogapi.config;

import com.danielmesquita.blogapi.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuthentication {
  @Autowired private SecurityFilter securityFilter;

  public static final String[] SWAGGER_AUTH_PERMIT_LIST =
      new String[] {"/api-docs/**", "/swagger-ui/**"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(SWAGGER_AUTH_PERMIT_LIST)
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/login")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/save")
                    .hasAuthority(UserRole.ADMIN.getRole())
                    .requestMatchers(HttpMethod.GET, "/users/getAll")
                    .hasAuthority(UserRole.USER.getRole())
                    .requestMatchers(HttpMethod.GET, "/users/get")
                    .hasAuthority(UserRole.USER.getRole())
                    .requestMatchers(HttpMethod.PUT, "/users/update")
                    .hasAuthority(UserRole.ADMIN.getRole())
                    .requestMatchers(HttpMethod.DELETE, "/users/delete")
                    .hasAuthority(UserRole.ADMIN.getRole())
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
