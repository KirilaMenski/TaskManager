package by.codexsoft.task.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.codexsoft.task.dao.TaskDAO;
import by.codexsoft.task.entity.Task;

@Repository
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addTask(Task task) throws SQLException {
		currentSession().save(task);
	}

	@Override
	public void editTask(Task task) throws SQLException {
		currentSession().update(task);
	}

	@Override
	public void deleteTask(Task task) throws SQLException {
		currentSession().delete(task);
	}

	@Override
	public Task getTask(int taskId) throws SQLException {
		Task task = (Task) currentSession().get(Task.class, taskId);
		return task;
	}

	@Override
	public List<Task> getTasks(int projectId) throws SQLException {
		List<Task> tasks = currentSession().createQuery("FROM Task t WHERE t.project_id = :id")
				.setParameter("id", projectId).list();
		return tasks;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
