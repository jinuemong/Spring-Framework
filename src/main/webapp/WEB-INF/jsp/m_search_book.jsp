<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 검색/대여</h2>
<h3>[도서 목록보기]</h3>
<h5>대여할 도서를 검색해주세요</h5>
<ul>
<c:forEach var="book" items="${books}" varStatus="status">
<li> ${status.index+1} : 이름:${book.bname}, 작가:${book.bwriter},
 장르:${book.bgenre}, 대여:${book.borrow}, 등록시간:${book.registerDateTime},
  책번호:${book.bnumber}  </li>
</c:forEach>
</ul>
<form Action = "m_search_book_result" Method = "post">
<select name ="op" id="op">
<option value="bname">이름으로 검색</option>
<option value="bwriter">작가로 검색</option>
<option value= "bgenre">장르로 검색</option>
<option value="borrow">대여가능 도서 검색</option>
</select>
<input type = "text" name = "search" id="search">
<Input Type = "Submit" Value = "검색하기">
</form>

<br><br>
<a href='/final'>[메인으로]</a>

</body>
</html>