
<%@ page import="project.Top"%>
<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >
<%Top top=new Top(request);%>
<%=top.open()%>
<%
String id=null;
Cookie cookies[]=request.getCookies();
for(int i=0;i<cookies.length;i++){
	if(cookies[i].getName().equals("id")){
		id=cookies[i].getValue();
	}
}
String url=request.getRequestURI()+"?";
int i=0;
Enumeration pram = request.getParameterNames();
String paramName;
while(pram.hasMoreElements()){		
	paramName=(String)pram.nextElement();
		if(i!=0)
			url+="&";
	url+=paramName+"="+request.getParameter(paramName);
	i++;
}	
%>
<div style="width: 100%; text-align: center;">
<h1>상품ID:<%=request.getParameter("productId")%></h1>
<form method="get" action="pocketPlus.jsp" style="margin: 0px auto;">
<input type="hidden" style="position: fixed; " name="id" value="<%=id%>"/>
<input type="hidden" style="position: fixed;" name="productId" value="<%=request.getParameter("productId")%>"/>
<input type="hidden" style="position:fixed;  " name="returnUrl"value='<%=url%>'>
<input type="submit" value="장바구니 추가">
</form>
</div>
<%=top.close()%>


</body>
</html>