package by.codexsoft.task.service;

import java.sql.SQLException;
import java.util.List;

import by.codexsoft.task.entity.User;

public interface UserService {

	public void addUser(User user) throws SQLException;

	public User getUser(String login) throws SQLException;

	public List<User> getDevelopers(String role) throws SQLException;

}
