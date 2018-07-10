<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>멤버 뷰</title>
</head>
<body>
<h1>멤버2</h1>
<table border='1'>
<form action='update' method='post'>
<tr><th>아이디</th>
    <td><input readonly type='text' name='id' value='${member.id}' readonly></td></tr>
<tr><th>암호</th>
    <td><input type='password' name='password'></td></tr>
<tr><th>이메일</th>
    <td><input type='email' name='email' value='${member.email}'></td></tr>
<tr><th>전화번호</th>
    <td><input type='text' name='tel' value='${member.tel}'></td></tr>
<p><button>변경하기</button><a href='delete?id=${member.id}'>삭제하기</a></p>
</form>
</table>
</body>
</html>