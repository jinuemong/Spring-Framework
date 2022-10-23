<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 정보 변경 </h2>
<h2> 도서 수정을 진행합니다 </h2>
<form action = "b_update_result" method="post">
책번호 :  ${sessionScope.bnumber}<br><br>
책 이름 : <input type = "text" name = "bname" id="bname" value = "${bookRegisterRequest.bname}"
placeholder="${sessionScope.bname}"><br><br>
책 작가 : <input type = "text" name = "bwriter" id="bwriter" value = "${bookRegisterRequest.bwriter}"
placeholder="${sessionScope.bwriter}"><br><br>
책 장르 : <input type = "text" name = "bgenre" id="bgenre" value = "${bookRegisterRequest.bgenre}"
placeholder="${sessionScope.bgenre}"><br><br>
책 대여 : <input type = "text" name = "borrow" id="borrow" value = "${bookRegisterRequest.borrow}"
placeholder="${sessionScope.borrow}"><br><br>
<input type="submit" value="수정하기">
<input type="reset" value="다시쓰기">
</form>
<br><p>
<a href='/final/b_search'>[목록가기]</a>
<a href='/final'>[메인으로]</a>
</p><br>
</body>
</html>