package com.api.wordToo.service;

import com.api.wordToo.model.RequestCohere;

public interface ICohereService {
	public String continuarHistoria(RequestCohere request);
	public String mejorarHistoria(RequestCohere request);
}
