<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: marci
  Date: 14.05.2020
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
    <style type="text/css">
        table, table * {
            border: 1px solid black
        }
    </style>
</head>
<body>
<h2> Edit Author</h2>
<form:form method="POST" modelAttribute="author">
    First Name: <form:input path="firstName"/>
    <form:errors path="firstName"/><br>

    Last Name: <form:input path="lastName"/>
    <form:errors path="lastName"/><br>

    <input type="submit"> <br>
    <br>
    <a href="/author/all"> <<--- Back to Author List </a>
</form:form>
</body>
</html>
