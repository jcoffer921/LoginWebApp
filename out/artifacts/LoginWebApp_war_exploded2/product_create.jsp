<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>

<h2>Create New Shoe Product</h2>

<form action="${pageContext.request.contextPath}/products/create" method="post">
    Name: <input type="text" name="product_name" required /><br/><br/>
    Description:<br/>
    <textarea name="product_description" rows="4" cols="40"></textarea><br/><br/>
    Color: <input type="text" name="product_color" /><br/><br/>
    Size: <input type="text" name="product_size" /><br/><br/>
    Price: <input type="number" step="0.01" name="product_price" required /><br/><br/>

    <button type="submit">Create</button>
</form>

<p><a href="${pageContext.request.contextPath}/products/read">Back to product list</a></p>

</body>
</html>
