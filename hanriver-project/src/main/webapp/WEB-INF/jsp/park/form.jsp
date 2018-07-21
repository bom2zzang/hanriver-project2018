<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공 등록</title>
</head>
<body>

<h1>공원 등록</h1>
<form action="add" method="post">
<table border='1'>
<tr>
    <th>이름</th><td><input type="text" name="name"></td>
</tr>
<tr>
    <th>위치</th><td><input type="text" name="location"></td>
</tr>
</table>
<button>등록</button>

</form>

</body>
</html>