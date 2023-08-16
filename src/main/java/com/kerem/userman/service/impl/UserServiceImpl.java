package com.kerem.userman.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kerem.userman.model.User;
import com.kerem.userman.service.UserService;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	
	@Value("${user.api.url}")
    private String userApiUrl;
	
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;

    public UserServiceImpl() {
        this.restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

	@Override
	public List<User> getUsers() {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<User[]> response = restTemplate.exchange(userApiUrl, HttpMethod.GET, requestEntity, User[].class);
		User[] users = response.getBody();
        return Arrays.asList(users);
	}
	
	@Override
	public boolean addUser(User user) {
		setJwtTokenToHeader();
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		ResponseEntity<String> response = restTemplate.exchange(userApiUrl, HttpMethod.POST, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return true;
        }
        System.err.println("Failed to add user. Response: " + response.getBody());
        return false;
	}

	@Override
	public boolean deleteUser(int id) {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(userApiUrl + "/deleteUser/" + id, HttpMethod.GET, requestEntity, String.class);
		// ResponseEntity<String> responseEntity = restTemplate.getForEntity(userApiUrl + "/deleteUser/" + id, String.class);
        
        if(response.getStatusCode() == HttpStatus.valueOf(200)) {
        	return true;
        }
        return false;
    }

	@Override
	public User getUserById(int id) {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<User> response = restTemplate.exchange(userApiUrl + "/" + id, HttpMethod.GET, requestEntity, User.class);
		return response.getBody();
		// return restTemplate.getForObject(userApiUrl + "/" + id, User.class);
	}

	@Override
	public boolean updateUser(User user) {
		setJwtTokenToHeader();
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		ResponseEntity<String> response = restTemplate.exchange(userApiUrl + "/updateUser", HttpMethod.POST, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.valueOf(200)) {
            return true;
        }
        System.err.println("Failed to update user. Response: " + response.getBody());
        return false;
	}
	
	public void setJwtTokenToHeader() {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        String token = (String) session.getAttribute("jwtToken");
        headers.set("Authorization", "Bearer " + token);
	}
}

