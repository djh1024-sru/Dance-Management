package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ManagerRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private ParentRepository parentContactRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void populateUserRepository() {
		populateFromAdminTable();
		populateFromManagerTable();
		populateFromInstructorTable();
		populateFromParentContactTable();
	}

	private void populateFromAdminTable() {
		for (Admin admin : adminRepository.findAll()) {
			if (!userRepository.existsByEmail(admin.getEmail())) {
				populateUser(admin.getFirstName(), admin.getLastName(), admin.getEmail(), "ADMIN");
			}
		}
	}

	private void populateFromManagerTable() {
		for (Manager manager : managerRepository.findAll()) {
			if (!userRepository.existsByEmail(manager.getEmail())) {
				populateUser(manager.getFirstName(), manager.getLastName(), manager.getEmail(), "MANAGER");
			}
		}
	}

	private void populateFromInstructorTable() {
		for (Instructor instructor : instructorRepository.findAll()) {
			if (!userRepository.existsByEmail(instructor.getEmail())) {
				populateUser(instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), "INSTRUCTOR");
			}
		}
	}

	private void populateFromParentContactTable() {
		for (Parent parentContact : parentContactRepository.findAll()) {
			if (!userRepository.existsByEmail(parentContact.getPrimaryEmail())) {
				String fullParent1Name = parentContact.getParent1Name();
				String[] nameParts = fullParent1Name.split(" "); // Name must be split with a space
				String firstName = nameParts[0];
				String lastName = nameParts.length > 1 ? nameParts[1] : ""; // Handles case where only the first name is
																			// provided
				populateUser(firstName, lastName, parentContact.getPrimaryEmail(), "PARENT");
			}
		}
	}

	private void populateUser(String firstName, String lastName, String email, String role) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		String hashedPassword = passwordEncoder.encode("1234");
		user.setPassword(hashedPassword);
		user.setRole(role);
		userRepository.save(user);
	}

	public User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		return user;
	}

	public void updateAdmin(Admin updatedAdmin) {
		Admin admin = adminRepository.findByEmail(updatedAdmin.getEmail());
		admin.setLastName(updatedAdmin.getLastName());
		admin.setFirstName(updatedAdmin.getFirstName());
		admin.setEmail(updatedAdmin.getEmail());
		admin.setAdminPhoneNum(updatedAdmin.getAdminPhoneNum());
		adminRepository.save(admin);
	}

	public void updateInstructor(Instructor updatedInstructor) {
		Instructor instructor = instructorRepository.findByEmail(updatedInstructor.getEmail());
		instructor.setLastName(updatedInstructor.getLastName());
		instructor.setFirstName(updatedInstructor.getFirstName());
		instructor.setEmail(updatedInstructor.getEmail());
		instructor.setInstructorPhoneNum(updatedInstructor.getInstructorPhoneNum());
		instructorRepository.save(instructor);
	}

	public void updateManager(Manager updatedManager) {
		Manager manager = managerRepository.findByEmail(updatedManager.getEmail());
		manager.setLastName(updatedManager.getLastName());
		manager.setFirstName(updatedManager.getFirstName());
		manager.setEmail(updatedManager.getEmail());
		manager.setManagerPhoneNum(updatedManager.getManagerPhoneNum());
		managerRepository.save(manager);
	}

	public void updateParent(Parent updatedParent) {
		Parent parent = parentContactRepository.findByEmail(updatedParent.getPrimaryEmail());
		parent.setParent1Name(updatedParent.getParent1Name());
		parent.setParent1PhoneNum(updatedParent.getParent1PhoneNum());
		parent.setParent2Name(updatedParent.getParent2Name());
		parent.setParent2PhoneNum(updatedParent.getParent2PhoneNum());
		parent.setPrimaryEmail(updatedParent.getPrimaryEmail());
		parent.setPrimaryAddress(updatedParent.getPrimaryAddress());
		parent.seteContName(updatedParent.geteContName());
		parent.seteContNum(updatedParent.geteContNum());
		parentContactRepository.save(parent);
	}

	public void updateUser(User updateUser) {
		User user = userRepository.findByEmail(updateUser.getEmail());
		user.setFirstName(updateUser.getFirstName());
		user.setLastName(updateUser.getLastName());
		user.setFirstLogin(updateUser.isFirstLogin());
		user.setEmail(updateUser.getEmail());

		userRepository.save(user);
	}

	@Transactional
	public void updatePasswordForAllRoles(String email, String oldPassword, String newPassword) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new RuntimeException("Existing password does not match");
		}

		// Update the password in the 'users' table
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		// Fetch and update the password in the 'admins' table if the user is an admin
		if ("ADMIN".equals(user.getRole())) {
			Admin admin = adminRepository.findByEmail(email);
			if (admin == null) {
				throw new RuntimeException("Admin not found");
			}
			admin.setPassword(passwordEncoder.encode(newPassword));
			adminRepository.save(admin);
		}
		// Fetch and update the password in the 'managers' table if the user is a
		// manager
		if ("MANAGER".equals(user.getRole())) {
			Manager manager = managerRepository.findByEmail(email);
			if (manager == null) {
				throw new RuntimeException("Manager not found");
			}
			manager.setPassword(passwordEncoder.encode(newPassword));
			managerRepository.save(manager);
		}
		// Fetch and update the password in the 'instructors' table if the user is an
		// instructor
		if ("INSTRUCTOR".equals(user.getRole())) {
			Instructor instructor = instructorRepository.findByEmail(email);
			if (instructor == null) {
				throw new RuntimeException("Instructor not found");
			}
			instructor.setPassword(passwordEncoder.encode(newPassword));
			instructorRepository.save(instructor);
		}
		// Fetch and update the password in the 'parentContacts' table if the user is a
		// parent contact
		if ("PARENT".equals(user.getRole())) {
			Parent parent = parentContactRepository.findByEmail(email);
			if (parent == null) {
				throw new RuntimeException("Parent contact not found");
			}
			parent.setPassword(passwordEncoder.encode(newPassword));
			parentContactRepository.save(parent);
		}
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}