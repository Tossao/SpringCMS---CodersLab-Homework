<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 13.05.2020
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>HOME</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2>Article Catalog App</h2><br>


Choose section you want to go: <br>
<a href="/article/all"> Article List </a><br>
<a href="/author/all"> Author List </a> <br>
<a href="/category/all"> Category List </a><br>
<a href="/draft/all"> Draft List </a><br>
<br>

<h3> Recent articles:</h3>
<table>
    <tbody>
    <tr>
        <th>Title</th>
        <th>Content</th>
        <th>Created</th>
    </tr>
    <c:forEach items="${article}" var="article">
        <tr>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${fn:substring(article.content,0,200)}"/></td>
            <td><c:out value="${article.created}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<h2>Article List by Categories:</h2>
<table>
    <tbody>
    <tr>
        <th>Category</th>
    </tr>
    <c:forEach items="${category}" var="category">
        <tr>
            <td><a href="<c:url value="article/categoryTable/${category.id}"/>">${category.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

</body>
</html>
