<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<h1 class="text-center">Items</h1>
<table class="table">
    <thead class="thead-dark">
    <tr class="text-center">
        <th scope="col">#</th>
        <th scope="col">Quantity</th>
        <th scope="col">Name</th>
        <th scope="col">Unique_number</th>
        <th scope="col">Description</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody class="text-center">
    <form class="index" id="output"
          action="${pageContext.request.contextPath}/dispatcher?command=showlogin" method="post">
        <input id='b' type="submit" value="<<<< OUT">
    </form>

    <c:forEach items="${items}" var="item">
        <tr>
            <form class="item-description"
                  action="${pageContext.request.contextPath}/dispatcher?command=buyIt&name=${item.name}"
                  method="post">
                <td>${item.id}</td>
                <td>
                    <input id="a" type="text" name="quantit<input id='j' type="submit" value="Buy it">y" placeholder="quantity">

                </td>
                <td>${item.name}</td>
                <td>${item.uniqueNumber}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <ul class="pagination">
        <c:forEach begin="1" var="page" end="${pages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/dispatcher?command=showitem&page=${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
