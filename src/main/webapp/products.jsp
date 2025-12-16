<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>

<h1>Product List</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
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
    </tr>
    <%
            }
        }
    %>
</table>

<h2>Add New Product</h2>

<form action="${pageContext.request.contextPath}/products" method="post">
    Name:        <input type="text" name="productName"><br/>
    Description: <input type="text" name="productDescription"><br/>
    Color:       <input type="text" name="productColor"><br/>
    Size:        <input type="text" name="productSize"><br/>
    Price:       <input type="number" step="0.01" name="productPrice"><br/>
    <input type="submit" value="Save Product">
</form>

<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>
