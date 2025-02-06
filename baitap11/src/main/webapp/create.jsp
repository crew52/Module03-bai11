<%--
  Created by IntelliJ IDEA.
  User: dungn
  Date: 2/6/2025
  Time: 9:12 AM
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

<h1 style="text-align: center;">Thêm Mới Khách Hàng</h1>

<form action="customers?action=create" method="post">
<%--    <label for="id">ID khách hàng:</label>--%>
<%--    <input type="number" id="id" name="id" required>--%>

    <label for="name">Tên khách hàng:</label>
    <input type="text" id="name" name="name" required>

    <label for="age">Tuổi:</label>
    <input type="number" id="age" name="age" required>

    <button type="submit">Thêm khách hàng</button>
</form>

</body>
</html>

