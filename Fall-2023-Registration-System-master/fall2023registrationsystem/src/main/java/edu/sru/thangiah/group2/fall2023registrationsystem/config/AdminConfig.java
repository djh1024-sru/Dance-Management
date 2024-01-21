package edu.sru.thangiah.group2.fall2023registrationsystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;

@Configuration
public class AdminConfig {

	private final AdminRepository adminRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public AdminConfig(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	// executed on application startup
    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // check if an admin user exists
            Admin admin = adminRepository.findByEmail("admin@studio.com");
            if (admin == null) {
                // create the admin user
                admin = new Admin();
                admin.setAdminID("999");
                admin.setEmail("admin@studio.com");
                admin.setPassword(passwordEncoder.encode("root"));
                admin.setRole("ADMIN");
                admin.setFirstName("admin");
                admin.setLastName("admin");
                adminRepository.save(admin);
                System.out.println("Admin with email admin@studio.com and password root has been created");
            }
            else {
            	System.out.println("Admin with email admin@studio.com already exists");
            }
            
            // check if a user with the same email exists
            User user = userRepository.findByEmail("admin@studio.com");
            if (user == null) {
            	// create the user if not exists
                user = new User();
                user.setEmail("admin@studio.com");
                user.setFirstName("admin");
                user.setLastName("admin");
                user.setId(999);
                user.setPassword(passwordEncoder.encode("root"));
                user.setRole("ADMIN");
                userRepository.save(user);
            }
        };
    }
}