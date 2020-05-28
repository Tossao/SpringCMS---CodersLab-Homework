<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 23.05.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drafts</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2>Draft List</h2>
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
        <c:forEach items="${articleDrafts}" var="article">
    <tr>
        <td><c:out value="${article.title}"/></td>
        <td><c:out value="${article.content}"/></td>
        <td><c:out value="${article.created}"/></td>
        <td><c:out value="${article.updated}"/></td>
        <td><c:out value="${article.author.lastName}"/></td>
        <td><c:forEach items="${article.category}" var="category">
            <c:out value="${category.name}"/></c:forEach></td>
        <td><c:out value="${article.draft}"/></td>
        <td><a href="/draft/editDraftForm/${article.id}">Update</a></td>
        <td><a href="/draft/delete/${article.id}">Delete</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<a href="/draft/addDraftForm/"> +++ Add new Draft +++ </a> <br>
<br>
<a href="/"> <<<--- Back to HomePage </a><br>

</body>
</html>
