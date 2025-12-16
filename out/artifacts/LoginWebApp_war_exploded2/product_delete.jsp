<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>

<h2>Delete Product</h2>

<p>Are you sure you want to delete product with ID:
    <strong><%= request.getAttribute("productId") %></strong>?
</p>

<form action="${pageContext.request.contextPath}/products/delete" method="post">
    <input type="hidden" name="product_id" value="<%= request.getAttribute("productId") %>" />
    <button type="submit">Yes, Delete</button>
</form>

<p><a href="${pageContext.request.contextPath}/products/read">Cancel</a></p>

</body>
</html>

