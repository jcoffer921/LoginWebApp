<%-- Admin login page: handles adminUser authentication. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>

<h2>Admin Login</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<form action="${pageContext.request.contextPath}/admin/login" method="post">
    <label>Username:
        <input type="text" name="username" required />
    </label><br/><br/>

    <label>Password:
        <input type="password" name="password" required />
    </label><br/><br/>

    <button type="submit">Login</button>
</form>

</body>
</html>