package br.edu.fateccotia.tasklist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.tasklist.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping 
	public ResponseEntity<String> authenticate (
			@RequestBody Map<String, String> auth) {
		String email = auth.get("email");
		String pwd = auth.get("password");
		
		String token = authService.authenticate(email, pwd);
		return ResponseEntity.ok(token);
	}
}
