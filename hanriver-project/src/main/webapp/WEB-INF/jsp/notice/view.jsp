
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판 뷰</title>
</head>
<body>
<h1>게시판2</h1>
<table border='1'>
<form action='../update' method='post'>
<tr><th>번호</th><td>
    <input readonly type='text' name='no' value='${notice.no}' readonly></td></tr>
<tr><th>제목</th>
    <td><input type='text' name='title' value='${notice.title}'></td></tr>
<tr><th>내용</th>
    <td><input type='text' name='contents' value='${notice.contents}'></td></tr>
<p><button>변경하기</button><a href='../delete?no=${notice.no}'>삭제하기</a></p>
</form>
</table>
</body>
</html>