package com.api.wordToo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
	  //Va a retornar la peticion http siempre y cuando pase por los filtros que configuramos
        return http
            .csrf(csrf ->  
                csrf
                .disable()) // se Deshabilita la proteccion CSRF, que viene por defecto porque vamos a usar JWT, creado por nosotros.
            .authorizeHttpRequests(authRequest ->
              authRequest
                .requestMatchers("/api/cohere/**").permitAll()
                .anyRequest().authenticated() // Toda el resto de Request se va a pedir que esten autenticadas.
                )
            .sessionManagement(sessionManager->
            sessionManager 
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Lo que hace es deshabilitar las sesiones
        .build();
            
    }

}
