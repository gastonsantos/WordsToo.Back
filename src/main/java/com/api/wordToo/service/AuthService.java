package com.api.wordToo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    private final IJwtService _jwtService;

    @Autowired
    public AuthService(IJwtService _jwtService) {
        this._jwtService = _jwtService;
    }

    public String generateToken() {
        // Crear un usuario ficticio o datos genéricos para el token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER"); // Puedes agregar más información al token aquí
        return _jwtService.generateToken(claims); // "genericUser" es un ejemplo de nombre de usuario
    }
}