package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.CustomUserDetails;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ManagerRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

        // check if the user has role (admin, manager, instructor, parent)
		Admin admin = adminRepository.findByEmail(email);
		Manager manager = managerRepository.findByEmail(email);
		Instructor instructor = instructorRepository.findByEmail(email);
		Parent parent = parentRepository.findByEmail(email);
		
		if (admin != null) {
			user.setRole("ADMIN");
		} else if (manager != null) {
			user.setRole("MANAGER");
		} else if (instructor != null) {
			user.setRole("INSTRUCTOR");
		} else if (parent != null) {
			user.setRole("PARENT");
		} else {
			user.setRole("USER");
		}

		return new CustomUserDetails(user, getAuthorities(user));
	}

	// get authorities based on user's role
	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String userRole = "ROLE_" + user.getRole();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
		return authorities;
	}
}