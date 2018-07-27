<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>한강공원</title>
</head>
<body>
<h1>공원뷰</h1>
<table border='1'>
<form action='../update' method='post'>
<tr><th>공원이름</th>
    <td><input readonly type='text' name='name' value='${park.name}' readonly></td></tr>
<tr><th>위치</th>
    <td><input type='text' name='location' value='${park.location}'></td></tr>
<p><button>변경하기</button><a href='../delete?name=${park.name}'>삭제하기</a></p>
</form>
</table>
</body>
</html>