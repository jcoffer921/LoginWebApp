<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shoe Result</title>
</head>
<body>

<h1>Your Shoe Selection</h1>

<p>
    <strong>Name:</strong>
    <%= request.getParameter("shoeName") %>
</p>
<p>
    <strong>Color:</strong>
    <%= request.getParameter("shoeColor") %>
</p>
<p>
    <strong>Size:</strong>
    <%= request.getParameter("shoeSize") %>
</p>
<p>
    <strong>Price:</strong>
    $<%= request.getParameter("shoePrice") %>
</p>

<p><a href="shoes.jsp">Choose Another Shoe</a></p>
<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>
