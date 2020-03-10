<%@ page import="project.PocketDtao"%>
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
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
String id=null;
Cookie cookies[]=request.getCookies();
for(int i=0;i<cookies.length;i++){
	if(cookies[i].getName().equals("id")){
		id=cookies[i].getValue();
	}
}
String paramProductId=request.getParameter("productId");
String content="";
boolean hasCon=false;
int idx=0;
PocketDtao pd=new PocketDtao(id);
pd.connect();
pd.setPocket();
String []productId=pd.getProductId();
String []productCount=pd.getProductCount();

for(int i=0;i<productId.length;i++){
	if(productId[i].equals(paramProductId)){
		idx=i;
		hasCon=true;
	}
}

if(hasCon==false){
content=pd.getContents()+"#"+paramProductId+"@"+"1";

}else if(hasCon==true){
productCount[idx]=(Integer.parseInt(productCount[idx])+1)+"";	
for(int i=0;i<productId.length;i++){
	if(i!=0)
		content+="#";
	content+=productId[i]+"@"+productCount[i];
	}
}

pd.updatePocketIm(content);
pd.close();


%>
<script type="text/javascript">
goReturn();
</script>

</body>
</html>