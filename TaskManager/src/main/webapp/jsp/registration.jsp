<%@ include file="taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="links.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<div class="registration-container">
		<form:form action="add_user" method="post" commandName="user">
		<p><h2>Please register</h2></p>
			<input name="login" />
			<input type="password" name="password" />
			<span>Select a role:</span>
			<form:select path="role">
				<option value="developer">developer</option>
				<option value="manager">manager</option>
			</form:select>
			<input id="reg-btn" type="submit" value="Registration" />
		</form:form>
		<p id="login-error" style="color: red;">${error}</p>
	</div>
</body>
</html>