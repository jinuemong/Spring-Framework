<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> Book List </h2>
<p><a href='/final'>[메인으로 돌아가기]</a></p>
<ul>
<c:forEach var="book" items="${books}" varStatus="status">
<li> ${status.index+1} : 이름:${book.bname}, 작가:${book.bwriter}, 
 장르:${book.bgenre}, 대여:${book.borrow}, 등록시간:${book.registerDateTime},
  책번호:${book.bnumber}  </li>
</c:forEach>
</ul>
</body>
</html>