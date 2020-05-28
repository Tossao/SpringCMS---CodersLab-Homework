<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 20.05.2020
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Article</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2>Edit Article</h2>
<form:form method="POST" modelAttribute="article">
    Title: <form:input path="title"/>
    <form:errors path="title"/><br>

    Article Content: <br><form:textarea path="content" rows="5" cols="20"/>
    <form:errors path="content"/><br>

    <%--        Article Created: <form:input path="created"/><br>--%>

    Author: <form:select path="author" itemLabel="lastName" itemValue="id" items="${author}"/> <br>

    Category: <i><small>(multiple choice available)</small></i><br>
    <form:select multiple="true" path="category" itemLabel="name" itemValue="id" items="${category}"/>
    <form:errors path="category"/><br>

    <input type="submit"> <br>
    <br>

    <a href="/article/all"> <<--- Back to Article List </a>
</form:form>
</body>

</html>
