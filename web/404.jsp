<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body{
            text-align: center;
            background-image: url("/sources/dist/image/404.jpg") ;
            background-repeat: no-repeat;
        }
    </style>
    <link rel="stylesheet" href="/sources/dist/css/error.css">
</head>
<body>

<a href="/" >返回首页</a>
</body>
</html>