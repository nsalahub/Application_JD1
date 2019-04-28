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
<form class="index" action="${pageContext.request.contextPath}/dispatcher?command=registration" method="post">
    <input type="text" name="email" placeholder="email" required>
    <input type="text" name="password" placeholder="password" required>
    <input type="text" name="name" placeholder="name" required>
    <input type="text" name="surname" placeholder="surname" required>
    <input type="text" name="address" placeholder="address" required>
    <input type="text" name="telephone" placeholder="telephone" required>
    <input id='a' type="submit" value="REGISTRATION">
</form>
<form class="index" id="signin"
      action="${pageContext.request.contextPath}/dispatcher?command=showlogin" method="post">
    <input id='b' type="submit" value="<<<<<<<GO BACK">
</form>
</body>
</html>
