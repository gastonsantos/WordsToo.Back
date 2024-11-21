package com.api.wordToo.service;

import java.util.Map;

public interface IJwtService {
	String generateToken(Map<String, Object> claims);
}
