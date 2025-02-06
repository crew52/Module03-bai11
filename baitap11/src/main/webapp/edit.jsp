<%--
  Created by IntelliJ IDEA.
  User: dungn
  Date: 2/6/2025
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Customer</title>
    <style>
        form {
            width: 300px;
            margin: auto;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }
        button {
            margin-top: 15px;
            padding: 10px;
            width: 100%;
            background-color: blue;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: darkblue;
        }
    </style>
</head>
<body>

<h1 style="text-align: center;">Edit Customer </h1>

<form action="customers?action=edit" method="post">
    <label>ID khách hàng: ${customer.id}</label>
    <input type="hidden" name="id" value="${customer.id}">

    <label for="name">Tên khách hàng:</label>
    <input type="text" id="name" name="name" value="${customer.name}">

    <label for="age">Tuổi:</label>
    <input type="number" id="age" name="age" value="${customer.age}">

    <button type="submit">Update khách hàng</button>
</form>

</body>
</html>
