<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> </head>
<body>
<h1> 도서 관리 서비스 </h1>
<h2> Member List </h2>
<p><a href='/final'>[메인으로 돌아가기]</a></p>
<p><a href='/final/b_admin'>[뒤로가기]</a></p>
<ul>
<c:forEach var="member" items="${members}" varStatus="status">
<li> ${status.index+1} : 이름:${member.name}, 이메일:${member.email},
 등록시간:${member.registerDateTime} </li>
</c:forEach>
</ul>
</body>
</html>