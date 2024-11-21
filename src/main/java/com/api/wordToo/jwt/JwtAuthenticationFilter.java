package com.api.wordToo.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.wordToo.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	   @Autowired
	    private JwtService jwtService;


	    /*
	     doFilterInternal: Este metodo se ejecuta en cada solicitur HTTP. Es el responsable de extraer el token de la Cabecera, 
	     validarlo y autenticar al usuario si es todo correcto.
	     */
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        // Obtiene el valor de la cabecera Authorization
	        final String authHeader = request.getHeader("Authorization");
	        final String jwt;

	        // Si no tiene cabecera o no comienza con "Bearer", continúa la ejecución sin procesar más.
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            System.out.println("Entra aquí y continúa sin procesar el token.");
	            filterChain.doFilter(request, response);
	            return;
	        }

	        // Si la cabecera es válida, extrae el token eliminando los primeros 7 caracteres
	        jwt = authHeader.substring(7);

	        // Valida el token con el servicio JwtService
	        if (jwtService.isTokenValid(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
	            // Crea un token de autenticación genérico
	            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    null, // Principal (puede ser null si no se identifica un usuario específico)
	                    null, // Credenciales (no las necesitas)
	                    null  // Autoridades (puedes configurar roles si lo necesitas)
	            );

	            // Configura los detalles de la solicitud actual
	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            // Establece la autenticación en el contexto de seguridad
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }

	        // Continúa el procesamiento de la solicitud
	        filterChain.doFilter(request, response);
	    }


}
