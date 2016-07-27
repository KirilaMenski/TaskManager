package by.codexsoft.task.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.codexsoft.task.entity.Comment;
import by.codexsoft.task.entity.Project;
import by.codexsoft.task.entity.Task;
import by.codexsoft.task.entity.User;
import by.codexsoft.task.service.CommentService;
import by.codexsoft.task.service.ProjectService;
import by.codexsoft.task.service.TaskService;
import by.codexsoft.task.service.UserService;
import by.codexsoft.task.util.DateCalendar;
import by.codexsoft.task.util.Encrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class ProjectController {

	private static final Logger LOG = Logger.getLogger(ProjectController.class);
	private static int projectId;
	private static int taskId;
	private String role = "developer";

	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/project")
	public ModelAndView openProjectPage() {
		ModelAndView mav = new ModelAndView();
		List<Project> projects = new ArrayList<>();
		List<User> developers = new ArrayList<>();
		try {
			projects = projectService.getProjects();
			developers = userService.getDevelopers(role);
		} catch (SQLException e) {
			LOG.error("Can't display that!", e);
			e.printStackTrace();
		}
		mav.addObject("projects", projects);
		mav.addObject("developers", developers);
		mav.setViewName("project");
		return mav;
	}

	@RequestMapping(value = "/add_project")
	public ModelAndView addProject(@ModelAttribute Project project, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String userName = request.getParameter("username");
		try {
			project.setAuthor(userName);
			projectService.addProject(project);
		} catch (SQLException e) {
			LOG.error("Can't do that!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/project");
		return mav;
	}

	@RequestMapping(value = "/delete_project{id}")
	public ModelAndView deleteProject(@PathVariable int id, @ModelAttribute Project project, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		try {
			project.setId(id);
			projectService.deleteProject(project);
		} catch (SQLException e) {
			LOG.error("Can't delete project!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/project");
		return mav;
	}

	@RequestMapping(value = "/view_project{id}")
	public ModelAndView viewProject(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		projectId = id;
		List<User> developers = new ArrayList<>();
		List<Task> tasks = new ArrayList<>();
		try {
			Project project = projectService.getProject(id);
			developers = userService.getDevelopers(role);
			tasks = taskService.getTasks(id);
			mav.addObject("tasks", tasks);
			mav.addObject("developers", developers);
			mav.addObject("project", project);
		} catch (SQLException e) {
			LOG.error("Can't display project!", e);
			e.printStackTrace();
		}
		mav.setViewName("view_project");
		return mav;
	}

	@RequestMapping(value = "/edit_project{id}")
	public ModelAndView editProject(@PathVariable int id, @ModelAttribute Project project, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		try {
			project.setId(id);
			project.setAuthor(username);
			projectService.editProject(project);
		} catch (SQLException e) {
			LOG.error("Can't edit project!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_project" + id);
		return mav;
	}

	@RequestMapping(value = "/add_task")
	public ModelAndView addTask(@ModelAttribute Task task, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		try {
			task.setProject_id(projectId);
			task.setAuthor(username);
			taskService.addTask(task);
		} catch (SQLException e) {
			LOG.error("Can't add task!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_project" + projectId);
		return mav;
	}

	@RequestMapping(value = "/delete_task{taskId}")
	public ModelAndView deleteTask(@PathVariable int taskId, @ModelAttribute Task task, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		try {
			task.setId(taskId);
			taskService.deleteTask(task);
		} catch (SQLException e) {
			LOG.error("Can't delete task!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_project" + projectId);
		return mav;
	}

	@RequestMapping(value = "view_task{id}")
	public ModelAndView viewTask(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		taskId = id;
		List<User> developers = new ArrayList<>();
		List<Comment> comments = new ArrayList<>();
		User user = null;
		Task task = null;
		try {
			// TODO
			user = userService.getUser("Manager");
			task = taskService.getTask(id);
			developers = userService.getDevelopers(role);
			comments = commentService.getComments(id);
		} catch (SQLException e) {
			LOG.error("Can't display task!", e);
			e.printStackTrace();
		}
		mav.addObject("user_name", user);
		mav.addObject("task", task);
		mav.addObject("projectId", projectId);
		mav.addObject("developers", developers);
		mav.addObject("comments", comments);
		mav.setViewName("view_task");
		return mav;
	}

	@RequestMapping(value = "/edit_task{taskId}")
	public ModelAndView editTask(@PathVariable int taskId, @ModelAttribute Task task, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		try {
			task.setProject_id(projectId);
			task.setId(taskId);
			task.setAuthor(username);
			taskService.editTask(task);
		} catch (SQLException e) {
			LOG.error("Can't edit task!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_task" + taskId);
		return mav;
	}

	@RequestMapping(value = "/add_comment")
	public ModelAndView addComment(@ModelAttribute Comment comment, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		try {
			comment.setTaskId(taskId);
			comment.setLogin(username);
			comment.setDate(DateCalendar.getDate());
			commentService.addComment(comment);
		} catch (SQLException e) {
			LOG.error("Can't add comment", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_task" + taskId);
		return mav;
	}

	@RequestMapping(value = "/delete_comment{id}")
	public ModelAndView deleteComment(@PathVariable int id, @ModelAttribute Comment comment) {
		ModelAndView mav = new ModelAndView();
		try {
			comment.setId(id);
			commentService.deleteComment(comment);
		} catch (SQLException e) {
			LOG.error("Can't delete comment", e);
		}
		mav.setViewName("redirect:/view_task" + taskId);
		return mav;
	}

	@RequestMapping(value = "/edit_comment{commentId}")
	public ModelAndView editComment(@PathVariable int commentId, HttpServletRequest request,
			@ModelAttribute Comment comment) {
		ModelAndView mav = new ModelAndView();
		String login = request.getParameter("ed-login");
		String date = request.getParameter("ed-date");
		String text = request.getParameter("ed-text-comment");
		try {
			comment.setId(commentId);
			comment.setLogin(login);
			comment.setDate(date);
			comment.setTaskId(taskId);
			comment.setComment(text);
			commentService.editComment(comment);
		} catch (SQLException e) {
			LOG.error("Can't edit comment!", e);
			e.printStackTrace();
		}
		mav.setViewName("redirect:/view_task" + taskId);
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/registr_page{error}")
	public ModelAndView openRegistr(@PathVariable String error) {
		ModelAndView mav = new ModelAndView();
		if (error == "error") {
			mav.addObject("error", "Login already exist!");
		}
		mav.setViewName("registration");
		return mav;
	}

	@RequestMapping(value = "/add_user")
	public ModelAndView registration(@ModelAttribute User user, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("login");
		String path = null;
		try {
			User login = userService.getUser(username);
			if (login != null) {
				path = "forward:/registr_page" + "Login already exist!";
			} else {
				String password = request.getParameter("password");
				user.setPassword(Encrypt.encodingPassword(password));
				userService.addUser(user);
				path = "redirect:/hello" + username;
			}
		} catch (SQLException e) {
			LOG.error("Error!", e);
			e.printStackTrace();
		}
		mav.setViewName(path);
		return mav;
	}

	@RequestMapping(value = "/hello{username}")
	public ModelAndView hello(@PathVariable String username) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("username", username);
		mav.setViewName("hello");
		return mav;
	}

	@ModelAttribute
	private User getUser() {
		return new User();
	}

	@ModelAttribute
	private Project getProject() {
		return new Project();
	}

	@ModelAttribute
	private Task getTask() {
		return new Task();
	}

	@ModelAttribute
	private Comment getComment() {
		return new Comment();
	}

}
