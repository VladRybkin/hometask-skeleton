<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Simple JSP Application</title>
	</head>
	<body>
		<h1>Welcome!</h1>
		<h2>Current time is <%= LocalDateTime.now() %></h2>
		<br/>

		<br/>
		 <a href="booking">booking</a>
	</body>
</html>
