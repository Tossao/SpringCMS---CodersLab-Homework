<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 13.05.2020
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2> Category List</h2>
<table>
    <tbody>
    <tr>
        <th>Name</th>
        <th>Desctiption</th>
    </tr>
    <c:forEach items="${category}" var="category">
        <tr>
            <td><c:out value="${category.name}"/></td>
            <td><c:out value="${category.description}"/></td>
            <td><a href="/category/editCategoryForm/${category.id}">Update</a></td>
            <td><a href="/category/delete/${category.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<a href="/category/addCategoryForm/"> +++ Add new Category +++ </a> <br>
<br>
<a href="/"> <<<--- Back to HomePage </a><br>
</body>
</html>
