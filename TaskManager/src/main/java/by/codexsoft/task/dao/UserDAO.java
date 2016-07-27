package by.codexsoft.task.dao;

import java.sql.SQLException;
import java.util.List;

import by.codexsoft.task.entity.User;

public interface UserDAO {

	public void addUser(User user) throws SQLException;
	
	public User getUser(String login) throws SQLException;

	public List<User> getDevelopers(String role) throws SQLException;

}
