package com.api.wordToo.model;

public class RequestCohere {

	private String titulo;
	private String genero;
	private String contenido;
	
	
	public RequestCohere(String titulo, String genero, String contenido) {
	
		this.titulo = titulo;
		this.genero = genero;
		this.contenido = contenido;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
