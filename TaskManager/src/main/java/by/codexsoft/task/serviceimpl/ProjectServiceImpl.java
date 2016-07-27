package by.codexsoft.task.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.codexsoft.task.dao.ProjectDAO;
import by.codexsoft.task.entity.Project;
import by.codexsoft.task.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	@Transactional
	public void addProject(Project project) throws SQLException {
		projectDAO.addProject(project);
	}

	@Override
	@Transactional
	public void editProject(Project project) throws SQLException {
		projectDAO.editProject(project);
	}

	@Override
	@Transactional
	public void deleteProject(Project project) throws SQLException {
		projectDAO.deleteProject(project);
	}

	@Override
	@Transactional
	public Project getProject(int projectId) throws SQLException {
		Project project = projectDAO.getProject(projectId);
		return project;
	}

	@Override
	@Transactional
	public List<Project> getProjects() throws SQLException {
		List<Project> projects = projectDAO.getProjects();
		return projects;
	}

}
