<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>ТОВАР КУПЛЕН</p>
<form class="index" id="output"
      action="${pageContext.request.contextPath}/dispatcher?command=showitem" method="post">
    <input id='b' type="submit" value="<<<< OUT">
</form>


</body>
</html>
