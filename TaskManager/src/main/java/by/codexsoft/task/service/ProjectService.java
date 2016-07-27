package by.codexsoft.task.service;

import java.sql.SQLException;
import java.util.List;

import by.codexsoft.task.entity.Project;

public interface ProjectService {

	public void addProject(Project project) throws SQLException;

	public void editProject(Project project) throws SQLException;

	public void deleteProject(Project project) throws SQLException;

	public Project getProject(int projectId) throws SQLException;

	public List<Project> getProjects() throws SQLException;

}
