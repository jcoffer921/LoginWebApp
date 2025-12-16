<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home - Login Web App</title>
</head>
<body>

<h1>Welcome to the Login Web App</h1>

<ul>
    <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
    <li><a href="${pageContext.request.contextPath}/customers">Customer List</a></li>
    <li><a href="${pageContext.request.contextPath}/shoes.jsp">Shoe Selector</a></li>
    <li><a href="${pageContext.request.contextPath}/products">Product Management (DB)</a></li>
    <li><a href="${pageContext.request.contextPath}/product_read.jsp">View Shoe Products</a></li>

</ul>

</body>
</html>
