package by.codexsoft.task.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.codexsoft.task.dao.UserDAO;
import by.codexsoft.task.entity.User;
import by.codexsoft.task.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(User user) throws SQLException {
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public List<User> getDevelopers(String role) throws SQLException {
		List<User> users = userDAO.getDevelopers(role);
		return users;
	}

	@Override
	@Transactional
	public User getUser(String login) throws SQLException {
		User user = userDAO.getUser(login);
		return user;
	}

}
