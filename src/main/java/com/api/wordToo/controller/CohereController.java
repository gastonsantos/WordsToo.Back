package com.api.wordToo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api.wordToo.model.RequestCohere;
import com.api.wordToo.service.IAuthService;
import com.api.wordToo.service.ICohereService;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cohere")
public class CohereController {

	@Autowired
	private ICohereService _cohereService;
	private IAuthService _authService;

	public CohereController(ICohereService _cohereService, IAuthService _authService) {
		
		this._cohereService = _cohereService;
		this._authService = _authService;
	}
	
   

    @GetMapping("/generar-token")
    public ResponseEntity<String> generateToken() {
        String token = _authService.generateToken();
        return ResponseEntity.ok(token);
    }
    
    @PostMapping(value="/continuarHistoria")
	public String continuarHistoria(@RequestBody RequestCohere request) {
		return _cohereService.continuarHistoria(request);
	}
    
    @PostMapping(value="/mejorarHistoria")
	public String continuarHistoriaTrolo(@RequestBody RequestCohere request) {
    	return _cohereService.mejorarHistoria(request);
	}
}
