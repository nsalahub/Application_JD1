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
<c:if test="${not empty error}">
    <div class="alert massage" role="alert">
        <c:out value="${error}"/>
    </div>
</c:if>
<form class="index" action="${pageContext.request.contextPath}/dispatcher?command=additem" method="post">
    <input type="text" name="name-item" placeholder="name item" required>
    <input type="text" name="description-item" placeholder="description this item" required>
    <input type="text" name="price-item" placeholder="price this item" required>
    <input type="text" name="uniqueNumber" placeholder="unicue number" required>
    <input id='a' type="submit" value="ADD">
</form>
<form class="index" id="back"
      action="${pageContext.request.contextPath}/dispatcher?command=showitemsale" method="post">
    <input id='b' type="submit" value="<<<<<GO BACK">
</form>

</body>
</html>
