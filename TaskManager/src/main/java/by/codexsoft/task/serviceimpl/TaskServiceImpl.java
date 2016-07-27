package by.codexsoft.task.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.codexsoft.task.dao.TaskDAO;
import by.codexsoft.task.entity.Task;
import by.codexsoft.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;

	@Override
	@Transactional
	public void addTask(Task task) throws SQLException {
		taskDAO.addTask(task);
	}

	@Override
	@Transactional
	public void editTask(Task task) throws SQLException {
		taskDAO.editTask(task);
	}

	@Override
	@Transactional
	public void deleteTask(Task task) throws SQLException {
		taskDAO.deleteTask(task);
	}

	@Override
	@Transactional
	public Task getTask(int taskId) throws SQLException {
		Task task = taskDAO.getTask(taskId);
		return task;
	}

	@Override
	@Transactional
	public List<Task> getTasks(int projectId) throws SQLException {
		List<Task> tasks = taskDAO.getTasks(projectId);
		return tasks;
	}

}
