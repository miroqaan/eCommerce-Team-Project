<!-- 결제 화면 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="mMgr" class="ch14.MemberMgr"/>
<%@ page import="java.util.*" %>    
    
<%
	String id = (String)session.getAttribute("idKey");
	String name = request.getParameter("product");
	String quantity = request.getParameter("quantity");
	int cookiesNum = 0;
	String non = "";
    Cookie[] cookies = request.getCookies();
	String cValue = name + "&" + quantity + "&";

	
	if(id != null){ // 회원 접속일 경우
		
	
	
		mMgr.productOrder(id, name, quantity); // basket 테이블에 name과 quantity 삽입
	}

	else if(id == null){ // 비회원 로그인일 경우
	    
	for(int i = 0; i < cookies.length; ++i){
		if(cookies[i].getName().equals("nonId")){
			cookiesNum = i;
		}
	}
	
	
	cookies[cookiesNum].setValue(cValue);
	response.addCookie(cookies[cookiesNum]);

	
	}
		


%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% %>	
<%=name %>이 <%=quantity %>개 주문되었습니다.




<br/>

<a href="login.jsp">메인으로 돌아가기</a>
</body>
</html>