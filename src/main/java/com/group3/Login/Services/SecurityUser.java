package com.group3.Login.Services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

	// Required to remove warning
	private static final long serialVersionUID = 1L;

	public static final String ROLE_PREFIX = "ROLE_";

	private String userName;
	private String password;
	private String role;

	public SecurityUser(String username, String password, String role) {
		this.userName = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

		return list;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	public String getRole() {
		return role;
	}
	
	@SuppressWarnings("deprecation")
	public UserDetails createUserDetails() {
		return User.withDefaultPasswordEncoder()
		.username(userName).password(password)
		.roles(role).build();		
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

}
