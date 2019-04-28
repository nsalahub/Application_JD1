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
<form class="index" id="output"
      action="${pageContext.request.contextPath}/dispatcher?command=showlogin" method="post">
    <input id='b' type="submit" value="<<<< OUT">
</form>
<c:if test="${not empty  massge}">
    <div class="info massage">
        <c:out value="${massge}"/>
    </div>
</c:if>
<h1 class="text-center">Items</h1>
<div class="text-center mb-2">
    <a href="${pageContext.request.contextPath}//dispatcher?command=showAddItem">
        <button type="button" class="btn btn-outline-success btn-sm">Add</button>
    </a>
    <a href="${pageContext.request.contextPath}/dispatcher?command=showUpdateItem">
        <button type="button" class="btn btn-outline-warning btn-sm">Update</button>
    </a>
    <a href='${pageContext.request.contextPath}/dispatcher?command=showxml'>
        <button type="button" class="btn btn-outline-danger btn-sm">XML</button>
    </a>
    <a href='${pageContext.request.contextPath}/dispatcher?command=showOrderSale'>
        <button type="button" class="btn btn-outline-danger btn-sm">Orders</button>
    </a>
</div>
<table class="table">
    <thead class="thead-dark">
    <tr class="text-center">
        <th scope="col">#</th>
        <th scope="col">You can delete this item</th>
        <th scope="col">Name</th>
        <th scope="col">Unique_number</th>
        <th scope="col">Description</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach items="${items}" var="item">
        <tr>
            <form class="quantity" action="${pageContext.request.contextPath}/dispatcher?command=deleteItem&name=${item.name}"
                  method="post">
                <td>${item.id}</td>
                <td>
                    <input id='a' type="submit" value="DELETE">
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
                   href="${pageContext.request.contextPath}/dispatcher?command=showitemsale&page=${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
