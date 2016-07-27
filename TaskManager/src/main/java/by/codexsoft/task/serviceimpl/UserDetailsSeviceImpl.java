package by.codexsoft.task.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.codexsoft.task.dao.UserDAO;
import by.codexsoft.task.entity.User;

@Service("userDetailsService")
public class UserDetailsSeviceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User user = null;
		try {
			user = userDAO.getUser(login);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user != null) {
			String username = user.getLogin();
			String password = user.getPassword();
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));

			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, password, authorities);
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User Not Found !");
		}
	}

}
