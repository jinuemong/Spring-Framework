<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 관리자 서비스 이용하기 </h2>
<script> alert("비밀번호를 정확하게 입력하세요")</script>
<p><a href='/final'>[메인으로 돌아가기]</a></p>
<form action = "b_admin_O2" method="post">
비밀 번호:
<input type = "password" name = "admin_pwd" id="admin_pwd" value="admin_pwd"><br><br>
<input type="submit" value="입력완료">
</form>
</body>
</html>