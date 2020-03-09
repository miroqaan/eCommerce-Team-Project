<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%
	boolean flag = false;
    Cookie[] cookies = request.getCookies();
    
    
	for(int i = 0; i < cookies.length; ++i){
		if(cookies[i].getName().equals("nonId")){
			flag = true;
		}
	}
	
	
	if(!(flag)){
		
		Cookie cookie = new Cookie("nonId", "non");
		cookie.setMaxAge(120*60);
		cookie.setValue("non");
		response.addCookie(cookie);

	}

	response.sendRedirect("login.jsp");


%>