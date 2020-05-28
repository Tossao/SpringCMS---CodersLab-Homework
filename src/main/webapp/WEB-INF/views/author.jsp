<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 14.05.2020
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author List</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2> Authors List</h2>
<table>
    <tbody>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <c:forEach items="${author}" var="author">
        <tr>
            <td><c:out value="${author.firstName}"/></td>
            <td><c:out value="${author.lastName}"/></td>
            <td><a href="/author/editAuthorForm/${author.id}">Update</a></td>
            <td><a href="/author/delete/${author.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<a href="/author/addAuthorForm/"> +++ Add new Author +++ </a> <br>
<br>
<a href="/"> <<<--- Back to HomePage </a><br>
</body>
</html>
