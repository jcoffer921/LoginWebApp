<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shoe Selector</title>
</head>
<body>

<h1>Choose Your Shoe</h1>

<form action="shoeResult.jsp" method="post">
    <p>
        <label>Shoe Name:
            <input type="text" name="shoeName" />
        </label>
    </p>

    <p>
        <label>Color:
            <select name="shoeColor">
                <option value="Black">Black</option>
                <option value="White">White</option>
                <option value="Red">Red</option>
                <option value="Blue">Blue</option>
            </select>
        </label>
    </p>

    <p>
        <label>Size:
            <select name="shoeSize">
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
        </label>
    </p>

    <p>
        <label>Price:
            <input type="number" step="0.01" name="shoePrice" />
        </label>
    </p>

    <input type="submit" value="Show Shoe Result">
</form>

<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>
