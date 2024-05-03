package br.edu.fateccotia.tasklist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.tasklist.model.User;
import br.edu.fateccotia.tasklist.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}
}
