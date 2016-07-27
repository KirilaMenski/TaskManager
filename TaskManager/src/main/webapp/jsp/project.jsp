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

		<sec:authorize access="hasAuthority('manager')">
			<p id="add">
				<a href="">Добавить</a>
			</p>
			<div id="add-project">
				<p>
				<h2>Add project:</h2>
				</p>
				<form:form action="add_project" method="post" commandName="project">
					<form:input type="text" path="title" placeholder="title" />
					<input type="hidden" name="username"
						value='<sec:authentication property="name" />' />
					<form:input type="date" path="dateStart" />
					<form:input type="date" path="dateEnd" />
					<form:textarea type="text" path="description"
						placeholder="description" />
					<input type="submit" id="submit" value="ADD" />
				</form:form>
			</div>
		</sec:authorize>
		<div id="proj-main-container">
		<c:forEach items="${projects}" var="projects">
			<div id="project-container">
					<a href="delete_project${projects.id}"><div id="delete-proj-img"></div></a>
					<div id="proj-title"><a href="view_project${projects.id}">${projects.title}</a></div>
					<div id="proj-date">${projects.dateStart} - ${projects.dateEnd}</div>
					<div id="proj-author">${projects.author}</div>
					<div id="proj-description">${projects.description}</div>
				</div>
		</c:forEach>
		</div>
	</div>
</body>
</html>