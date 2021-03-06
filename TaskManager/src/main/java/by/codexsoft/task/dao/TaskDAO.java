package by.codexsoft.task.dao;

import java.sql.SQLException;
import java.util.List;

import by.codexsoft.task.entity.Task;

public interface TaskDAO {

	public void addTask(Task task) throws SQLException;

	public void editTask(Task task) throws SQLException;

	public void deleteTask(Task task) throws SQLException;

	public Task getTask(int taskId) throws SQLException;

	public List<Task> getTasks(int projectId) throws SQLException;
}
