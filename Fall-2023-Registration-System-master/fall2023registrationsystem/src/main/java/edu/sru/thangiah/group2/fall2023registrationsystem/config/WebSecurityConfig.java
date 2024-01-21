package edu.sru.thangiah.group2.fall2023registrationsystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.*;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		// sets properties of the Dao Authentication Provider
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
    
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	// define role-based access control for specific URLs
            	.requestMatchers(HttpMethod.DELETE, "/admin/delete/*").hasRole("ADMIN")
                .requestMatchers("/upload", "/uploadFile", "/saveFile").hasRole("ADMIN")
                .requestMatchers("/users").authenticated()
                .requestMatchers("/manager/**").hasAnyRole("MANAGER")
                .requestMatchers("/manager/createManager").hasRole("MANAGER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/parent").hasAnyRole("PARENT")
                .requestMatchers("/instructor").hasAnyRole("INSTRUCTOR")
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/uploadAdminFile").hasRole("ADMIN")
                .requestMatchers("/saveData").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/changePassword").authenticated()
                .anyRequest().permitAll()  // all other URLs
            .and()
            .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/loginSuccess")
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .exceptionHandling()
                .accessDeniedPage("/403");

        http.headers().frameOptions().sameOrigin(); // allow embedding in iframes with the same origin

        return http.build();
    }
}