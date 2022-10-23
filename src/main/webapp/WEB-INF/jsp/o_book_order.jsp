<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 대여하기 </h2>
<br>
${sessionScope.bname}
<c:choose>
<c:when test="${sessionScope.borrow_book ne 1}">
도서가 이미 대여 중입니다.
<a href='/final/o_book_book'>[선택 도서 예약하기]</a>
</c:when>

<c:when test="${sessionScope.borrow_book eq 1}">
도서 대여 완료
</c:when>
</c:choose>

<br><p>
<a href='/final'>[메인으로]</a>
<a href='/final/m_search_book'>[뒤로가기]</a>
</p><br>
</body>
</html>