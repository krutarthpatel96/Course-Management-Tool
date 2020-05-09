package com.group3.Login.Services;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group3.BusinessModels.LoginForm;
import com.group3.Login.DAO.DAOAbstractFactory;
import com.group3.Login.DAO.ILoginDAO;

@Service
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		ILoginDAO ld = DAOAbstractFactory.instance().createLoginDAO();
		LoginForm user = ld.getUserByEmail(email);
		String role = ld.getRoleByEmail(email).toUpperCase();

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}

		else {

			SecurityUser securedUser = new SecurityUser(user.getEmail(), user.getPassword(), role);

			return securedUser.createUserDetails();

		}

	}

}
