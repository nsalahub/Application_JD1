<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>

<h1 style='text-align: center'>Login</h1>
<div class="login">
    <c:if test="${not empty error}">
        <div class="alert massage" role="alert">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <form class="index" action="${pageContext.request.contextPath}/dispatcher?command=login" method="post">
        <input type="text" name="email" placeholder="username" required>
        <input type="password-user" name="password" placeholder="password" required>
        <input id='a' type="submit" value="LOGIN">
    </form>
    <form class="index" id="signin"
          action="${pageContext.request.contextPath}/dispatcher?command=showregistration" method="post">
        <input id='b' type="submit" value="SIGN IN">
    </form>
</div>
</div>


</body>
</html>