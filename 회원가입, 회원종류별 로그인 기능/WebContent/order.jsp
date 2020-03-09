<!-- 주문 페이지 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

	String id;
	id = (String)session.getAttribute("idKey");




 %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.a {

	
	width: 300px;
	heigth: 500px
	
	
	background:#f00;
	margin: 50px 50px 50px 50px;
	
}
</style>
</head>
<body>
<%if(id != null){ %>
회원입니다.
<%} %>
<%if(id == null){ %>
비회원입니다.
<%} %>

<div class="a">
	<form action="order2.jsp" method="post">
		<img src="images/necklace1.png" alt="목걸이">목걸이 사세요~ <br/>
		<input type="hidden" name="product" value="necklace1"/>
		<input type="text" name="quantity" placeholder="구매수량을 입력하세요" />
		<input type="submit" value="눌러"/>
	</form>
</div>
<br/>
<a href="login.jsp">메인으로 돌아가기</a>

</body>
</html>