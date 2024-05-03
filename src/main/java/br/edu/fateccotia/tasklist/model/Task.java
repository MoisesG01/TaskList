package br.edu.fateccotia.tasklist.model;
 
import br.edu.fateccotia.tasklist.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "task")
public class Task {
	//Attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description;
	private String notes;
	private TaskStatus status = TaskStatus.PENDING;
	@ManyToOne
	private User user;
	
	//Constructors
	public Task() {
		
	}
	public Task(Integer id, String description, String notes, TaskStatus status, User user) {
		super();
		this.id = id;
		this.description = description;
		this.notes = notes;
		this.status = status;
		this.user = user;
	}
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}