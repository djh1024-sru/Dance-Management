package edu.sru.thangiah.group2.fall2023registrationsystem.model;

import java.io.Serializable;

import edu.sru.thangiah.group2.fall2023registrationsystem.security.ValidPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	@ValidPassword
	private String password;

	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;
	
	@Column(name = "first_login", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
	private Boolean firstLogin;
	
	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    private Boolean isActive;
	
	private String role;

	public User(long id, String email, String password,
			String firstName, String lastName, String role, Boolean isActive) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.firstLogin = true;
		this.isActive = isActive;
	}
	// getters + setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isFirstLogin() {
		if (firstLogin == null) {
			return true;
		}
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public User() {
		this.firstLogin = true;
		this.isActive = true;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}