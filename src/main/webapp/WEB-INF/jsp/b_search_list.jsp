<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 관리자 서비스 이용하기 - 도서 관리/검색 </h2>
<h3>[검색 목록보기]</h3>
<ul>
<c:forEach var="book" items="${books}" varStatus="status">
<li> ${status.index+1} : 이름:${book.bname}, 작가:${book.bwriter}, 
 장르:${book.bgenre}, 대여:${book.borrow}, 등록시간:${book.registerDateTime},
  책번호:${book.bnumber} 
 </li>
 <Form Action = "b_update" Method = "post">
 <input  type="radio" name="check" id ="check" value="${book.bnumber}">선택
<Input Type = "Submit"  Value = "[선택 도서 수정]">
</Form>
<Form Action = "b_delete" Method = "post">
<input  type="radio" name="check" id ="check" value="${book.bnumber}">선택
<Input Type = "Submit" Value = "[선택 도서 삭제]">
</Form>
</c:forEach>
</ul>
<br>

<a href='/final/b_insert'>[도서 새로 등록]</a>
<br><p>
<a href='/final/b_search'>[뒤로가기]</a>
<a href='/final'>[메인으로]</a>
</p><br>
</body>
</html>