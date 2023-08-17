package com.kerem.userman.service;

import com.kerem.userman.model.RegisterCredential;
import com.kerem.userman.model.SignInCredential;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
	String login(SignInCredential signInCredential);
	String register(RegisterCredential registerCredential);
}
