<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Customer" %>

<html>
<head>
    <title>Customer List</title>

    <style>
        body {
            font-family: "Times New Roman", Times, serif;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 15px;
        }

        h2 {
            font-size: 36px;
            margin-top: 35px;
            margin-bottom: 15px;
        }

        table {
            border-collapse: collapse;
            font-size: 20px;
        }

        th, td {
            border: 2px solid black;
            padding: 8px 14px;
        }

        hr {
            margin: 25px 0;
        }

        .form-row {
            font-size: 20px;
            margin-bottom: 15px;
        }

        input[type="text"] {
            width: 300px;
            font-size: 18px;
            padding: 4px;
            margin-left: 10px;
        }

        input[type="submit"] {
            font-size: 18px;
            padding: 6px 12px;
        }
    </style>
</head>

<body>

<h1>Customer List</h1>

<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Favorite Meal</th>
    </tr>

    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null) {
            for (Customer c : customers) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getFirstName() %></td>
        <td><%= c.getLastName() %></td>
        <td><%= c.getFavoriteMeal() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<hr/>

<h2>Add New Customer</h2>

<form action="${pageContext.request.contextPath}/customers" method="post">
    <div class="form-row">
        First Name:
        <input type="text" name="firstName">
    </div>

    <div class="form-row">
        Last Name:
        <input type="text" name="lastName">
    </div>

    <div class="form-row">
        Favorite Meal:
        <input type="text" name="favoriteMeal">
    </div>

    <input type="submit" value="Save Customer">
</form>

<p>
    <a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a>
</p>

</body>
</html>
