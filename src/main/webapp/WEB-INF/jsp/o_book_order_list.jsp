<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> 도서 대여목록/반납</h2>
<h3>[도서 목록보기]</h3>
<h5>반납 할 도서를 검색해주세요</h5>
<ul>
<c:forEach var="order" items="${orders}" varStatus="status">
<li> ${status.index+1} : 도서 이름:${order.odbook}, 대여일:${order.registerDateTime},
  책번호:${order.odbnumber} </li>
  <Form Action = "o_book_return" Method = "post">
<input  type="radio" name="check" id ="check" value="${order.odbnumber}">선택
<Input Type = "Submit" Value = "[선택 도서 반납]">
</Form> 
</c:forEach>
</ul>
<br><br>
<a href='/final'>[메인으로]</a>

</body>
</html>