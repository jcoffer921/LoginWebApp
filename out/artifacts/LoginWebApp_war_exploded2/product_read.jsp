<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>

<h2>All Shoe Products</h2>

<p>
    <a href="${pageContext.request.contextPath}/products/create">Create New Product</a> |
    <a href="${pageContext.request.contextPath}/admin/logout">Logout</a>
</p>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>

    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product p : products) {
    %>
    <tr>
        <td><%= p.getProductId() %></td>
        <td><%= p.getProductName() %></td>
        <td><%= p.getProductDescription() %></td>
        <td><%= p.getProductColor() %></td>
        <td><%= p.getProductSize() %></td>
        <td><%= p.getProductPrice() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/products/update?id=<%= p.getProductId() %>">Edit</a> |
            <a href="${pageContext.request.contextPath}/products/delete?id=<%= p.getProductId() %>">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
