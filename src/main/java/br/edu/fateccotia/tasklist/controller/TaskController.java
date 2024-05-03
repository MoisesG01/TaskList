package br.edu.fateccotia.tasklist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.tasklist.enums.TaskStatus;
import br.edu.fateccotia.tasklist.model.Task;
import br.edu.fateccotia.tasklist.model.User;
import br.edu.fateccotia.tasklist.service.AuthService;
import br.edu.fateccotia.tasklist.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public ResponseEntity<String> metodo() {
		return ResponseEntity.ok("Hello, World!");
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task,
			@RequestHeader(name="token") String token) {
		if (!authService.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User user = authService.toUser(token);
		task.setUser(user);
		Task taskCreated = taskService.save(task);
		return ResponseEntity.ok(taskCreated);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Task>> findAll (
			@RequestHeader(name="token") String token) {
		if (!authService.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User user = authService.toUser(token);
		List<Task> list = taskService.findAll(user);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> edit(@PathVariable Integer id, @RequestBody Task task) {
		Optional<Task> actualTask = taskService.edit(id, task);
		if (actualTask.isPresent()) {
			return ResponseEntity.ok(actualTask.get());
		}
		return ResponseEntity.notFound().build();
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		taskService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Task>> search(
			@RequestParam(name = "q") String query
			, @RequestParam(name = "s") String status
			, @RequestParam(name = "token") String token) {
		if (!authService.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		List<Task> tasks = null;
		User user = authService.toUser(token);
		if ("p".equals(status)) {
			tasks = taskService.findByQuery(query, TaskStatus.PENDING, user);
		} else if ("d".equals(status)) {
			tasks = taskService.findByQuery(query, TaskStatus.DONE, user);
		}
		return ResponseEntity.ok(tasks);
	}
}
	