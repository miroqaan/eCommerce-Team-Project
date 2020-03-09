<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
	
	boolean fla = false;
	
    Cookie[] cookies = request.getCookies();

	
	for(int i = 0; i < cookies.length; ++i){
		if(cookies[i] != null && cookies[i].getName().equals("nonId")){
			fla = true;
		}
	}
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function loginCheck(){
		if(document.loginFrm.id.value == ""){
			alert("아이디를 입력해주세요.");
			document.loginFrm.id.focus();
			return;
			
		}
		
		if(document.loginFrm.pwd.value == ""){
			alert("비밀번호를 입력해주세요.");
			document.loginFrm.pwd.focus();
			return;
		}
		document.loginFrm.submit();
		
	}


	function loginCheck_non(){
		alert("비회원으로 로그인하였습니다.");
		location.href='loginNonProc.jsp';
	}
	
	
	   
		
	
	
	
	
</script>


</head>
<body bgcolor="#ffffcc">
<div align="center"><br/><br/>
<%if(id != null){ %>
<b><%=id %></b>님 환영합니다.
<p>제한된 기능을 사용할 수 있습니다.<p/>
<p><a href="order.jsp">상품 주문하러 가기</a></p>






<a href="logout.jsp">로그아웃</a>
<%} else if(fla){ %>


<br/>
<a href="logout.jsp">로그아웃</a>
	<p>비회원으로 로그인하였습니다.</p>
	<a href="order.jsp">상품 주문하러 가기</a>
	
<%} else{ %>
<form name="loginFrm" method="post" action="loginProc.jsp">
<table>
	<tr>
		<td align="center" colspan="2"><h4>로그인</h4></td>
	</tr>
	<tr>
		<td>아 이 디</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd"></td>
	</tr>
	<tr>
		<td colspan="2">
		<div align="right">
			<input type="button" value="로그인" onclick="loginCheck()">&nbsp;
				<input type="button" value="회원가입" onclick="javascript:location.href='member.jsp'">
				<input type="button" value="비회원 로그인" onclick="loginCheck_non()">&nbsp;
		</div>
		</td>
	</tr>
</table>
</form>


<%} %>


</div>
</body>
</html>