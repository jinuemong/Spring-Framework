<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 회원 수정하기 </h2>
<h4> 회원 수정을 진행합니다</h4>
<form action = "m_update_result" method="post">
이메일 :  ${sessionScope.email}<br><br>
비밀번호 : <input type = "password" name = "password" id="password" value="${registerRequest.password}"
placeholder="${sessionScope.pwd}"><br><br>
비밀번호 확인: <input type = "password" name = "confirmPassword" id="confirmPassword"
placeholder="${sessionScope.pwd}"><br><br>
이 름 : <input type = "text" name = "name" id="name" value = "${registerRequest.name}"
placeholder="${sessionScope.name}"><br><br>
<input type="submit" value="수정하기">
<input type="reset" value="다시쓰기">
</form>
<p><a href='/final'>[메인으로 돌아가기]</a></p>
</body>
</html>