<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Product" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>

<h2>Update Product</h2>

<%
    Product product = (Product) request.getAttribute("product");
%>

<form action="${pageContext.request.contextPath}/products/update" method="post">
    <input type="hidden" name="product_id" value="<%= product.getProductId() %>" />

    Name: <input type="text" name="product_name" value="<%= product.getProductName() %>" required /><br/><br/>
    Description:<br/>
    <textarea name="product_description" rows="4" cols="40"><%= product.getProductDescription() %></textarea><br/><br/>
    Color: <input type="text" name="product_color" value="<%= product.getProductColor() %>" /><br/><br/>
    Size: <input type="text" name="product_size" value="<%= product.getProductSize() %>" /><br/><br/>
    Price: <input type="number" step="0.01" name="product_price" value="<%= product.getProductPrice() %>" required /><br/><br/>

    <button type="submit">Update</button>
</form>

<p><a href="${pageContext.request.contextPath}/products/read">Back to product list</a></p>

</body>
</html>
