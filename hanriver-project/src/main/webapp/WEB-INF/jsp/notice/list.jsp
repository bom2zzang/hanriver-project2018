<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판 목록</title>
</head>
<body>
<h1>게시판 목록2</h1>
<p><a href='add'>새 게시글</a></p>
<table border='1'>
<tr>
    <th>번호</th><th>제목</th><th>날짜</th>
</tr>
<c:forEach items="${list}" var="notice">
<tr>
    <td>${notice.no}</td><td><a href='view?no=${notice.no}'>${notice.title}</a></td><td>${notice.date}</td>
</tr>
</c:forEach>
</table>
</body>
</html>