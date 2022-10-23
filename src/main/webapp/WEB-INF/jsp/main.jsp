<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<br>
<c:choose>
<c:when test="${sessionScope.login ne 1}">
<h4>로그인 시 도서 검색 이외의 서비스를 이용 가능 합니다.</h4>
<Form Action = "login" Method = "post">
이메일 : <Input Type = "Text" Name = "strEmail"> <BR>
비밀번호 : <Input Type = "PassWord" Name = "strPwd"> <BR><BR>
<Input Type = "Submit" Value = "로그인">

</Form>
<p>
<a href='/final/m_insert'>[회원 가입하기]</a>
<a href='/final/search_id'>[아이디 찾기]</a>
<a href='/final/search_pwd'>[비밀번호 찾기]</a>
</p><br>

</c:when>
<c:when test="${sessionScope.login eq 1}">
<p>${sessionScope.name}님 환영합니다!</p>
<br><a href='/final/m_search_book'>[도서 검색/대여 하기]</a><br>
<br><a href='/final/o_book_order_list'>[대여 도서보기/반납 하기]</a><br>
<br><a href='/final/m_insert_usedbook'>[중고도서 기증하기]</a><br>
<p>
<a href='/final/logout'>[로그아웃]</a>
<a href='/final/m_update'>[회원 정보수정]</a>
<a href='/final/m_delete'>[회원 탈퇴]</a>
</p><br>
</c:when>
</c:choose>
<div>
<button onclick="location.href='/final/b_admin_O'">관리자 서비스 이용하기</button>
<button onclick="location.href='/final/b_list'">등록 서적 보기</button>
</div>
</body>
</html>