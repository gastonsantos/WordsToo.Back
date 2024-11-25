package com.api.wordToo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.wordToo.service.IAuthService;
import com.api.wordToo.service.ICohereService;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

	@Autowired
	
	private IAuthService _authService;

	public JwtController( IAuthService _authService) {
		
		this._authService = _authService;
	}
	
	  @GetMapping("/generar-token")
	    public ResponseEntity<String> generateToken() {
	        String token = _authService.generateToken();
	        return ResponseEntity.ok()
	                .header("Access-Control-Allow-Origin", "http://localhost:3000") // Cabecera CORS manual
	                .body(token);
	    }
	  
	
}
