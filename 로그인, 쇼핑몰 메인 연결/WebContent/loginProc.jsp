<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="ch14.MemberMgr"/>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	String url = "login.jsp";

	String msg = "로그인에 실패하였습니다.";
	
	boolean result = mMgr.loginMember(id, pwd);
	
	if(result && id.equals("master")){
		session.setAttribute("idKey", id);
		msg = "로그인(관리자)에 성공하였습니다";
		url = "login_admin.jsp";
	}
	else if(result){
		session.setAttribute("idKey", id);
		msg = "로그인(일반)에 성공하였습니다.";
		
	}
	
	
%>


<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>