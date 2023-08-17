package com.kerem.userman.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;
import com.kerem.userman.service.RoleService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Value("${role.api.url}")
    private String roleApiUrl;
	
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;

    public RoleServiceImpl() {
        this.restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

	@Override
	public List<Role> getRoles() {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Role[]> response = restTemplate.exchange(roleApiUrl, HttpMethod.GET, requestEntity, Role[].class);
		Role[] roles = response.getBody();
        return Arrays.asList(roles);
	}

	@Override
	public boolean addRole(Role role) {
		setJwtTokenToHeader();
		HttpEntity<Role> requestEntity = new HttpEntity<>(role, headers);
		ResponseEntity<String> response = restTemplate.exchange(roleApiUrl, HttpMethod.POST, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return true;
        }
        System.err.println("Failed to add role. Response: " + response.getBody());
        return false;
	}

	@Override
	public boolean deleteRole(int id) {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(roleApiUrl + "/deleteRole/" + id, HttpMethod.GET, requestEntity, String.class);
		// ResponseEntity<String> responseEntity = restTemplate.getForEntity(userApiUrl + "/deleteUser/" + id, String.class);
        
        if(response.getStatusCode() == HttpStatus.valueOf(200)) {
        	return true;
        }
        return false;
	}

	@Override
	public Role getRoleById(int id) {
		setJwtTokenToHeader();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Role> response = restTemplate.exchange(roleApiUrl + "/" + id, HttpMethod.GET, requestEntity, Role.class);
		return response.getBody();
	}

	@Override
	public boolean updateRole(Role role) {
		setJwtTokenToHeader();
		HttpEntity<Role> requestEntity = new HttpEntity<>(role, headers);
		ResponseEntity<String> response = restTemplate.exchange(roleApiUrl + "/updateRole", HttpMethod.POST, requestEntity, String.class);
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
