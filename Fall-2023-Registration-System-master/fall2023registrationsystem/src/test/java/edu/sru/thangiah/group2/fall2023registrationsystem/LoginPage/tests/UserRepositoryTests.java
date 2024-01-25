//package edu.sru.thangiah.group2.fall2023registrationsystem.LoginPage.tests;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
//import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class UserRepositoryTests {
//
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private UserRepository repo;
//	
//	public void testCreateUser() {
//		User user = new User();
//		user.setEmail("alecbrianstrawn@gmail.com");
//		user.setPassword("abc123");
//		user.setFirstName("Alec");
//		user.setLastName("Strawn");
//		
//		User savedUser = repo.save(user);
//		
//		User existUser = entityManager.find(User.class, savedUser.getId());
//		
//		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//	}
//	
//}
