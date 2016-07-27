<%@ include file="taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="links.jsp"%>
<title>TaskManager</title>
</head>
<body>
	<div id="main-container">
		<div id="userproperty-bar">
			<sec:authentication property="name" />
			<sec:authentication property="authorities" />
			<a href="<c:url value="/logout" />">Logout</a>
		</div>
		<div id="flag"><a href="project">Main</a></div>

		<div id="view-container">
			<p id="view-title">${project.title}</p>
			<p id="view-date">${project.dateStart}- ${project.dateEnd}</p>
			<p id="view-status">${project.status}</p>
			<p id="view-author">${project.author}</p>
			<p id="view-edit">Edit project</p>
			<p id="view-description">${project.description}</p>
		</div>

		<div id="view-edit-container">
			<form:form action="edit_project${project.id}" method="post"
				commandName="project">
				<form:input type="text" path="title" value="${project.title}" id="view-ed-title"/>
				<form:input type="date" path="dateStart" />
				<input type="hidden" name="username"
					value='<sec:authentication property="name" />' />
				<form:input type="date" path="dateEnd" />
				<sec:authorize access="hasAuthority('developer')">
				<form:select path="status" value="${project.status}" id="view-ed-status">
					<option value="Default"></option>
					<option value="InWork">In Work</option>
					<option value="Verified">Verified</option>
					<option value="Done">Done</option>
				</form:select>
				</sec:authorize>
				<form:textarea type="text" path="description"
					value="${project.description}" id="view-ed-description"/>
				<form:input type="hidden" path="author" value="${project.author}" />
				<input type="submit" id="submit" value="Edit" />
			</form:form>
		</div>
		<div id="view-add-container">
		ADD TASK: <br />
		<form:form action="add_task" method="post" commandName="task">
			<form:input type="text" path="title" placeholder="title" />
			<input type="hidden" name="username"
				value='<sec:authentication property="name" />' />
			<p>Date start:<form:input type="date" path="dateStart" /></p>
			<p>Date end: <form:input type="date" path="dateEnd" /></p>
			<p>Status:</p>
			<form:select path="status" value="${project.status}">
				<option value=" "></option>
				<option value="InWork">In work</option>
				<option value="Verified">Verified</option>
				<option value="Done">Done</option>
			</form:select>
			<p>Select developer:</p>
			<form:select path="developer">
				<option value="Default"></option>
				<c:forEach items="${developers}" var="developers">
					<option value="${developers.login}">${developers.login}</option>
				</c:forEach>
			</form:select>
			<form:textarea type="text" path="description"
				placeholder="description" />
			<input type="submit" id="submit" value="ADD" />
		</form:form>
		</div>
		
		<div id="view-proj-task-main-container">
		<c:forEach items="${tasks}" var="tasks">
			<div id="task-container">
					<a href="delete_task${tasks.id}"><div id="delete-task-img"></div></a>
					<div id="view-proj-task-title"><a href="view_task${tasks.id}">${tasks.title}</a></div>
					<div id="view-proj-task-date">${tasks.dateStart} - ${tasks.dateEnd}</div>
					<div id="view-proj-task-status">${tasks.status}</div>
					<div id="view-proj-task-author">${tasks.author}</div>
					<div id="view-proj-task-developer">${tasks.developer}</div>
					<div id="view-proj-task-description">${tasks.description}</div>
				</div>
		</c:forEach>
		</div>

		
	</div>
</body>
</html>