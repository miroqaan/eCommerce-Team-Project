<%@page import="java.util.Enumeration"%>
<%@page import="project.PocketDtao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function goReturn() {
	location.href='<%=request.getParameter("returnUrl")%>';
}
function manual(){
	sessionStorage.setItem('manual','3');
}
manual();
</script>
<body>
<%
System.out.println("abc  "+request.getParameter("returnUrl"));
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
String id=null;
Cookie cookies[]=request.getCookies();
for(int i=0;i<cookies.length;i++){
	if(cookies[i].getName().equals("id")){
		id=cookies[i].getValue();
	}
}
String products="";
PocketDtao pd=null;
Enumeration pram = request.getParameterNames();
int i=0;
pram.nextElement();
while(pram.hasMoreElements()){
	
	String name=(String)pram.nextElement();
	if(Integer.parseInt(request.getParameter(name))!=0) {
		if(i!=0){
			products+="#";
		}
	products += name+"@"+request.getParameter(name);
	i++;
	}
	
}
if(i==0) {products="null";}
System.out.println(products);
	pd=new PocketDtao(id);
	pd.connect();
	pd.updatePocketIm(products);
	pd.close();

%>
<script type="text/javascript">
goReturn();
</script>
</body>
</html>