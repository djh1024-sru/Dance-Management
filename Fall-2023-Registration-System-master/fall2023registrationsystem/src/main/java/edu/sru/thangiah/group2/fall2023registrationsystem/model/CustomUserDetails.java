package edu.sru.thangiah.group2.fall2023registrationsystem.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

// implements the UserDetails interface for custom user details in Spring Security
public class CustomUserDetails implements UserDetails {

	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	// get the authorities associated with the user
    // if authorities are not provided, default role of "ROLE_USER" is assigned
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities != null) {
			return authorities;
		}
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}

}