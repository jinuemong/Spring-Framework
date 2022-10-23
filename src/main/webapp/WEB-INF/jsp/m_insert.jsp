<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 회원 가입하기 </h2>
<form action = "m_insert_result" method="post">
이메일 : <input type = "text" name = "email" id="email" value="${registerRequest.email}"><br><br>
비밀번호 : <input type = "password" name = "password" id="password" value="${registerRequest.password}"><br><br>
비밀번호 확인: <input type = "password" name = "confirmPassword" id="confirmPassword"><br><br>
이 름 : <input type = "text" name = "name" id="name" value = "${registerRequest.name}"><br><br>
<input type="submit" value="가입하기">
<input type="reset" value="다시쓰기">
<p><a href='/final'>[메인으로 돌아가기]</a></p>
</form>
</body>
</html>