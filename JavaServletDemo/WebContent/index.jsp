<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.PrintWriter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h1>Lets learn JSP!</h1>

	<%
		System.out.println("Hello World" + new Date());
	%>

<!-- how to set the value in java code, for jsp to use the value? -->
	<%
		String name = request.getParameter("name");
	
	    PrintWriter printWriter = response.getWriter();
		printWriter.println(name);

		out.println(name);
	%>

	<%=name%>

	<%=new Date()%>

	<%!void callMe() {
		System.out.println("define method in jsp.");
	}%>

	<%
		callMe();
	%>

</body>
</html>