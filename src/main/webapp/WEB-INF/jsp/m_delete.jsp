<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<script> alert("비밀번호를 정확하게 입력하세요")</script>
<h1> 도서 관리 서비스 </h1>
<h2> 회원 삭제하기 </h2>
<h4> 회원 삭제를 진행합니다</h4>
<form action = m_delete_result method="post">
비밀번호 : <input type = "password" name = "strPwd" id="strPwd" value="strPwd"><br>
<Input Type = "Submit" Value = "탈퇴">
</form>
<p><a href='/final'>[탈퇴 취소하기]</a></p>
</body>
</html>