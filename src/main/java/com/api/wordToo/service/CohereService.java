package com.api.wordToo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.wordToo.model.Contenido;
import com.api.wordToo.model.RequestCohere;

@Service
public class CohereService implements ICohereService {
	@Autowired
	private RestTemplate restTemplate;
	
	 @Value("${cohere.api.url}")
	    private  String apiUrl;

	 @Value("${cohere.api.key}")
	    private  String apiKey;
    
	
	private String mejorar= "Mejora esta historia y devuelve solo el contenido en un lenguaje claro y fluido, sin agregar títulos o comentarios adicionales respetando la historia.";
	private String continuar= "Continúa la historia con un máximo de 30 palabras. Asegúrate de mantener coherencia con lo escrito, sin repetir frases anteriores, y deja un desenlace abierto para seguir desarrollándola.";
	@Override
	 public String continuarHistoria(RequestCohere request) {
	        // Configura los encabezados de la solicitud
		 	String text = null;
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("Authorization", "Bearer " + apiKey);

	        // Configura el cuerpo de la solicitud
	        Map<String, Object> body = new HashMap<>();
	        body.put("prompt",  continuar + " el Titulo es: "+request.getTitulo()+"el Género es: "+request.getGenero()+"el Contenido es: "+request.getContenido());
	        body.put("model", "command-r-plus");  // Modelo de ejemplo
	        body.put("max_tokens", 50); // Controla el número de tokens generados
	        body.put("temperature", 0.5); // Controla la creatividad de la respuesta

	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

	        // Realiza la solicitud a Cohere
	        ResponseEntity<String> response = restTemplate.exchange(
	        		apiUrl,
	                HttpMethod.POST,
	                entity,
	                String.class
	        );
	        
	        // Crear un objeto ObjectMapper
	        ObjectMapper objectMapper = new ObjectMapper();

	    
	        	 try {
					
				        JsonNode root = objectMapper.readTree(response.getBody());
				        text = root.get("text").asText();
				} catch (JsonProcessingException e) {
					
					
					e.printStackTrace();
				}
	        	
	        	 String completo = request.getContenido()+" "+text;
	        
	        return completo;
	    }
	@Override
	 public String mejorarHistoria(RequestCohere request) {
	        // Configura los encabezados de la solicitud
		 	String text = null;
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("Authorization", "Bearer " + apiKey);

	        // Configura el cuerpo de la solicitud
	        Map<String, Object> body = new HashMap<>();
	        body.put("prompt",  mejorar + "el Contenido es: "+request.getContenido());
	        body.put("model", "command-r-plus");  // Modelo de ejemplo
	        body.put("max_tokens", 300); // Controla el número de tokens generados
	        body.put("temperature", 0.5); // Controla la creatividad de la respuesta

	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

	        // Realiza la solicitud a Cohere
	        ResponseEntity<String> response = restTemplate.exchange(
	        		apiUrl,
	                HttpMethod.POST,
	                entity,
	                String.class
	        );
	        
	        // Crear un objeto ObjectMapper
	        ObjectMapper objectMapper = new ObjectMapper();

	    
	        	 try {
					
				        JsonNode root = objectMapper.readTree(response.getBody());
				        text = root.get("text").asText();
				} catch (JsonProcessingException e) {
					
					
					e.printStackTrace();
				}
	        	
	        	 String completo = text;
	        
	        return completo;
	    }
}
