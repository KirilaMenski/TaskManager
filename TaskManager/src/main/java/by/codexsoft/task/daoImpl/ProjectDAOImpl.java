package by.codexsoft.task.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.codexsoft.task.dao.ProjectDAO;
import by.codexsoft.task.entity.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addProject(Project project) throws SQLException {
		currentSession().save(project);
	}

	@Override
	public void editProject(Project project) throws SQLException {
		currentSession().update(project);
	}

	@Override
	public void deleteProject(Project project) throws SQLException {
		currentSession().delete(project);

	}

	@Override
	public Project getProject(int projectId) throws SQLException {
		Project project = (Project) currentSession().get(Project.class, projectId);
		return project;
	}

	@Override
	public List<Project> getProjects() throws SQLException {
		List<Project> projects = currentSession().createQuery("FROM Project").list();
		return projects;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
