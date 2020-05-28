<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 23.05.2020
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Draft</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2>Edit Draft</h2>
<form:form method="POST" modelAttribute="draft">
    Title: <form:input path="title"/>
    <form:errors path="title"/><br>

    Draft Content: <br><form:textarea path="content" rows="5" cols="20"/>
    <form:errors path="content"/><br>

    Author: <form:select path="author" itemLabel="lastName" itemValue="id" items="${author}"/> <br>

    Category: <i><small>(multiple choice available)</small></i><br>
    <form:select multiple="true" path="category" itemLabel="name" itemValue="id" items="${category}"/>
    <form:errors path="category"/><br>

    Draft: <form:select path="draft">
    <option value="true">true</option>
    <option value="true">false</option>
</form:select>
    <form:errors path="draft"/><br>

    <input type="submit"> <br>
    <br>

    <a href="/draft/all"> <<--- Back to Draft List </a>
</form:form>
</body>
</html>
