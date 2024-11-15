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
import com.api.wordToo.service.ICohereService;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cohere")
public class CohereController {

	@Autowired
	private ICohereService _cohereService;
	 

	public CohereController(ICohereService _cohereService) {
		
		this._cohereService = _cohereService;
		
	}
	
    @Autowired
    private RestTemplate restTemplate;

    private static final String COHERE_API_URL = "https://api.cohere.ai/generate";
    private static final String COHERE_API_KEY = "hlKdCGADTFU6Mc8zEwqdojIK6pet1cN16S3Fo0l0"; // Reemplaza con tu clave de Cohere

    
    
    @GetMapping(value="/preguntar")
    public String preguntar() {
        // Configura los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + COHERE_API_KEY);

        // Configura el cuerpo de la solicitud
        Map<String, Object> body = new HashMap<>();
        body.put("prompt", "Necesito que me continues con la historia con no mas de 20 palabras, dejandolo abierto para que yo pueda continuarla, Titulo: La marca.  Había una vez un perro que tenia la marca de...");
        body.put("model", "command-r-plus");  // Modelo de ejemplo
        body.put("max_tokens", 50); // Controla el número de tokens generados
        body.put("temperature", 0.5); // Controla la creatividad de la respuesta

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // Realiza la solicitud a Cohere
        ResponseEntity<String> response = restTemplate.exchange(
                COHERE_API_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        // Devuelve la respuesta obtenida
        return response.getBody();
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
