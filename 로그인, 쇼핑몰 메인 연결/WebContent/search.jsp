<%@ page import="java.net.URLDecoder"%>
<%@ page import="project.ProductsDtao"%>
<%@ page import="project.Top"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#main2{margin:0px auto;  height:100%; width:1100px;padding-top:10px; }

.container{width: 350px;z-index: 8;text-align: center;height: 300px;border:black 0.3px solid;
         margin:7px;float: left;}
.image{opacity: 1;position: relative;
      transition: .5s ease;backface-visibility: hidden;width:340px;height: 250px;margin-top: 5px;}
.middle{position: relative;transition: .5s ease;opacity: 0;
      vertical-align:top; left:100px; top: -150px;}
.container:hover .image{opacity: 0.3;}
.container:hover .middle{opacity: 1;}
.text{
background-color: #4CAF50;color: white;
      font-size: 16px;padding: 16px 32px; width: 90px; height: 20px;}
</style>
</head>
<body>
<%Top top=new Top(request );%>
<%=top.open()%>
<%
ProductsDtao pd =new ProductsDtao();
pd.connect();
String categoryParam=request.getParameter("category");
String keywordParam=request.getParameter("keyword");

if(categoryParam!=null&&keywordParam!=null){	
	pd.searchCandK(categoryParam,keywordParam);	
}
%><div id="main2" style="">
<%
for(int i=0;i<pd.getLength();i++){%>

<div  class="container" onclick="location.href='text.jsp?productId=<%=pd.getId()[i]%>';" ><!-- 상품 -->
<img src='img/<%=pd.getImgFile()[i]%>' alt="상품 1" class="image" style="">
<div class="middle">
<div class="text"><%=pd.getPrice()[i]%>\</div>
</div>
<span style="float: right ; margin-right: 10px; font-style: italic; position: relative;left:0px;top:-54px; ">
<%=pd.getName()[i]%><br><%=pd.getCategory()[i]%></span>
</div>

<%} %>
</div>
<%=top.close()%>
</body>
</html>