<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 등록 </h2>
<form action = "b_insert_result" method="post">
책이름 : <input type = "text" name = "bname" id="bname" value="${bookRegisterRequest.bname}"><br><br>
작가 : <input type = "text" name = "bwriter" id="bwriter" value="${bookRegisterRequest.bwriter}"><br><br>
장르 : <input type = "text" name = "bgenre" id="bgenre" value = "${bookRegisterRequest.bgenre}"><br><br>
<br>
<input type="submit" value="등록하기">
<input type="reset" value="다시쓰기">
</form><br>
<a href='/final/b_search'>[등록 취소]</a>
<p><a href='/final'>[메인으로 돌아가기]</a></p>
</body>
</html>