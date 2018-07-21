<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>공원 목록</title>
<body>
<h1>공원목록22</h1>
<p><a href='form'>새회원</a></p>
<table border='1'>
<tr>
	<th>번호</th><th>공원이름</th><th>위치</th>
</tr>
<c:forEach items="${list}" var="park">
<tr>
	<td>${park.no }</td>
	<td><a href='view/${park.name }'>${park.name }</a></td>
	<td>${park.location }</td>
</tr>
</c:forEach>
</table>




</body>
</head>
</html>
