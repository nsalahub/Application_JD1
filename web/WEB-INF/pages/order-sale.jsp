<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form class="index" id="back"
      action="${pageContext.request.contextPath}/dispatcher?command=showitemsale" method="post">
    <input id='b' type="submit" value="<<<<<<< GO BACK">
</form>
<table class="table">
    <thead class="thead-dark">
    <tr class="text-center">
        <th scope="col">#</th>
        <th scope="col">User</th>
        <th scope="col">Item</th>
        <th scope="col">Created</th>
        <th scope="col">Quantity</th>
        <th scope="col">State</th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.user.id}</td>
            <td>${order.item.id}</td>
            <td>${order.createDate}</td>
            <td>${order.quantity}</td>
            <td>${order.orderStatus}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <ul class="pagination">
        <c:forEach begin="1" var="page" end="${pages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/dispatcher?command=showordersale&page=${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</div>


</body>
</html>
