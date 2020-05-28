<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 13.05.2020
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2> Edit Category</h2>
<form:form method="POST" modelAttribute="category">
    Name: <form:input path="name"/>
    <form:errors path="name"/><br>

    Description: <form:input path="description"/>
    <form:errors path="description"/><br>

    <input type="submit"> <br>
    <br>
    <a href="/category/all"> <<--- Back to Category List </a>
</form:form>
</body>
</html>
