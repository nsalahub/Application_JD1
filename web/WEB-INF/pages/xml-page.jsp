<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${not empty error}">
    <div class="alert massage" role="alert">
        <c:out value="${error}"/>
    </div>
</c:if>

<form class="index" id="back"
      action="${pageContext.request.contextPath}/dispatcher?command=showitemsale" method="post">
    <input id='b' type="submit" value="<<<<<GO BACK">
</form>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description"/>
    <input type="file" name="file"/>
    <input type="submit"/>
</form>

</body>
</html>
