<%--
  Created by IntelliJ IDEA.
  User: dungn
  Date: 2/6/2025
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
<h1>Danh sach customer ${customerList.size()}</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cus" items="${customerList}">
        <tr>
            <td>${cus.getId()}</td>
            <td>${cus.getName()}</td>
            <td>${cus.getAge()}</td>
            <td>
                <a href="customers?action=edit&id=${cus.id}" class="action-btn edit">Edit</a>
                <a href="customers?action=detail&id=${cus.id}" class="action-btn detail">Detail</a>
                <a href="customers?action=delete&id=${cus.id}" class="action-btn delete"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
