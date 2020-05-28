<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 14.05.2020
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Article</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2> Article List</h2>
<table>
    <tbody>
    <tr>
        <th>Title</th>
        <th>Content</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Author</th>
        <th>Category</th>
        <th>Draft</th>
    </tr>
    <c:forEach items="${article}" var="article">
        <tr>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.content}"/></td>
            <td><c:out value="${article.created}"/></td>
            <td><c:out value="${article.updated}"/></td>
            <td><c:out value="${article.author.lastName}"/></td>
            <td><c:forEach items="${article.category}" var="category">
                <c:out value="${category.name}"/></c:forEach></td>
            <td><c:out value="${article.draft}"/></td>
            <td><a href="/article/editArticleForm/${article.id}">Update</a></td>
            <td><a href="/article/delete/${article.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<a href="/article/addArticleForm/"> +++ Add new Article +++ </a> <br>
<br>
<a href="/"> <<<--- Back to HomePage </a><br>
</body>
</html>
