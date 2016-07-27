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
		<div id="flag"><a href="project">Main</a> --- <a href="view_project${projectId}">Project</a></div>
		<div id="view-container">
			<p id="view-title">${task.title}</p>
			<p id="view-date">${task.dateStart}-${task.dateEnd}</p>
			<p id="view-status">${task.status}</p>
			<p id="view-author">${task.author}</p>
			<p id="view-developer">${task.developer}</p>
			<p id="view-edit">Edit project</p>
			<p id="view-description">${task.description}</p>
		</div>

		<div id="view-edit-container">
			EDIT TASK: <br />
			<form:form action="edit_task${task.id}" method="post"
				commandName="task">
				<form:input type="text" path="title" placeholder="title"
					value="${task.title}" id="view-ed-title" />
				<input type="hidden" name="username"
					value='<sec:authentication property="name" />' />
				<form:input type="date" path="dateStart" value="${task.dateStart}" />
				<form:input type="date" path="dateEnd" value="${task.dateEnd}" />
				<sec:authorize access="hasAuthority('developer')">
					<form:select path="status" value="${task.status}"
						id="view-ed-status">
						<option value="${task.status}" selected="${task.status}">${task.status}</option>
						<option value="InWork">In work</option>
						<option value="Verified">Verified</option>
						<option value="Done">Done</option>
					</form:select>
				</sec:authorize>
				<form:select path="developer">
					<option value="${task.developer}" selected="${task.developer}">${task.developer}</option>
					<c:forEach items="${developers}" var="developers">
						<option value="${developers.login}">${developers.login}</option>
					</c:forEach>
				</form:select>
				<form:textarea placeholder="Description" path="description"
					id="view-ed-description" cols="30" rows="10"
					value="${task.description}" />
				<input type="submit" id="submit" value="EDIT" />
			</form:form>
		</div>
		<div id="view-add-container">
			ADD COMMENT:<br /> <br />
			<form:form action="add_comment" method="post" commandName="comment">
				<p>
					<sec:authentication property="name" />
				</p>
				<input type="hidden" name="username"
					value='<sec:authentication property="name" />' />
				<form:textarea path="comment" />
				<input type="submit" value="ADD" />
			</form:form>
		</div>
		<div id="comments-main-container">
			COMMENTS: <br /> <br /> <br />
			<c:forEach items="${comments}" var="comments">
				<div id="comment-container">

					<p>${comments.login}</p>
					<p>${comments.date}</p>
					<p>${comments.comment}</p>
					<p>
						<a href="delete_comment${comments.id}">Delete</a>
					</p>
					<p id="view-edit" onclick="showEdit(${comments.id})">Edit
						comment</p>
					<div id="edit-comment-container${comments.id}">
						<form:form action="edit_comment${comments.id}" method="post" commandName="comment">
							<input type="hidden" name="username"
								value='<sec:authentication property="name" />' /> 
							<input
								type="hidden" name="ed-login" value="${comments.login}" /> 
							<input
								type="hidden" name="ed-date" value="${comments.date}" /> 
							<input
								type="hidden" name="ed-taskId" value="${comments.taskId}" />
							<textarea name="ed-text-comment">${comments.comment}</textarea>
							<input type="submit" value="Edit"/>
						</form:form>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</body>
</html>