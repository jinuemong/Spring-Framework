<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 정보 변경 </h2>
<p><strong>도서명 : ${bookRegisterRequest.bname}</strong> </p>
<h3>도서 수정 되었습니다.</h3>
<br><p>
<a href='/final/b_search'>[뒤로가기]</a>
<a href='/final'>[메인으로]</a>
</p><br>
</body>
</html>