package com.kerem.userman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;

import com.kerem.userman.model.RegisterCredential;
import com.kerem.userman.model.SignInCredential;
import com.kerem.userman.model.User;
import com.kerem.userman.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Value("${auth.api.url}")
    private String authApiUrl;

	private final RestTemplate restTemplate;
	private final HttpHeaders headers;

    public AuthServiceImpl() {
        this.restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

	@Override
	public String login(SignInCredential signInCredential) {
		HttpEntity<SignInCredential> requestEntity = new HttpEntity<>(signInCredential, headers);
		ResponseEntity<String> response = restTemplate.exchange(authApiUrl + "/signIn", HttpMethod.POST, requestEntity, String.class);
		if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
		String authorizationHeader = response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		    String token = authorizationHeader.substring("Bearer ".length());
			return token;
		} else {
			return null;
		}
	}

	@Override
	public String register(RegisterCredential registerCredential) {
		HttpEntity<RegisterCredential> requestEntity = new HttpEntity<>(registerCredential, headers);
		ResponseEntity<String> response = restTemplate.exchange(authApiUrl + "/register", HttpMethod.POST, requestEntity, String.class);
		
		if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
		String authorizationHeader = response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		    String token = authorizationHeader.substring("Bearer ".length());
			return token;
		} else {
			return null;
		}
	}
}
