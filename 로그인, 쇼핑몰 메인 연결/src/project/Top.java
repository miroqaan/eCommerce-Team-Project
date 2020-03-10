package project;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*��ٱ��� ���*/


//Top class ��뼳��
//1.Top Ŭ�� ��ü�����Ѵ�
//2.������ ���� �ڵ� ������ open() �޼��带 �ۼ��Ѵ�
//3.������ ���� �ڵ� �Ʒ����� close() �޽��带 �ۼ��Ѵ�.
//ex)<body>
//  Top top=new Top(request);%>        
//                                                                         																  	
//   <%=top.open();%>	
//
//   <div id="" class=""> ������ ������ </div>       						   *����* ������ ��Ÿ���� Top Ŭ���� ��Ÿ�� �� ���ļ� ��µ� ��� 
//	 											  							          ������ ��Ÿ���� �����θ� div�� ������ div�� ���̵�(orŬ����)���� �������̵�(orŬ����)���� ���� ������ ��Ÿ���� �������ٰ�
//	 <%=top.close();%>
//   </body>
public class Top {
	String id;//���̵�
	MembersDtao md;
	PocketDtao pd;
	ProductsDtao prd;
	String name;
	String[] productId;
	String[] productCount;
	String pocketCode="";
	String url;
	/*
	 * <% Cookie [] cookies=request.getCookies(); %>
	 * <h1><%=cookies[1].getName()%><%=cookies[1].getValue()%></h1>
	 */
	Cookie [] cookies;

public Top(HttpServletRequest request) throws SQLException, ClassNotFoundException{	//������
	Cookie[] cookies=request.getCookies();
	for(int i=0;i<cookies.length;i++) {
		System.out.println(i+cookies[i].getName());
		if(cookies[i].getName().equals("id")) {
			this.id=cookies[i].getValue();
			System.out.println(cookies[i].getValue());
		}
	}

	this.url=request.getRequestURI()+"?";
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
	URLEncoder.encode(url);	
	System.out.println("url="+url);
	setMD();
	setPD();
	
}
void setMD() throws SQLException, ClassNotFoundException {//memberDtao ���� ���� ������
	md=new MembersDtao();
	md.connect();
	name=md.getName(id);
	md.close();
}
void setPD() throws SQLException, ClassNotFoundException {//pocketDtao ���� ���� ������
	pd=new PocketDtao(id);
	pd.connect();
	pd.setPocket();
	productId=pd.getProductId();//��ǰ�� �迭�� ������
	productCount=pd.getProductCount();//��ǰ ���� �迭�� ������   /��ǰ��:productName[0] ����:productCount[0]/
	pd.close();
	setPocketCode();
}
void setPocketCode() throws ClassNotFoundException, SQLException {//��ٱ��� �ڵ� ����
	int productPrice;
	int totalPrice=0;
	prd=new ProductsDtao();
	prd.connect();
	if(productId[0].equals("null")) {
		pocketCode="��ϵ� ��ٱ��� ����";
	}else {
		pocketCode+="<div  style=\"overflow:auto; border: 1px black solid ; width: 240px; height:480px; margin: 0px auto;background-color:#dbdbd7; \"> <form id=\"pocketForm\" method=\"get\">"
				+ "<input type=\"hidden\" style=\"position:fixed;\" name=\"returnUrl\"value='"+url+"'>";
	for(int i=0;i<productId.length;i++) {
		prd.searchId(productId[i]);
		productPrice=Integer.parseInt(prd.getPrice()[0]);
	
	pocketCode+="<div style=\"border: black 1px solid; margin:4px 1px;background-color:white; height:65px;\">"
			+ "<img height=\"65\" width=\"68\"  src=\"img/"+prd.getImgFile()[0]+"\" style=\"float:left; margin:0px 0px;\"  onmouseover=\"viPocket('show"+productId[i]+"')\">"
					+ "<div id=\"show"+productId[i]+"\"  onclick=\"location.href='text.jsp?productId="+productId[i]+"';\" onmouseover=\"viPocket('show"+productId[i]+"')\" onmouseout=\"hiPocket('show"+productId[i]+"')\" "
							+ "style=' visibility:hidden; height:65px; width:68px; color:white; font-size:15px; position: relative;background-color:black; text-align:center; opacity: 0.5;'> "
							+"<span style='position:relative; top:-45px;'onmouseover=\"viPocket('show"+productId[i]+"')\">��ǰ����</span></div>"
					+ "<div style=\"position: relative; top:-64.5px;border-left:0.5px black solid; margin:0px 0px 0px 68px; padding-left:5px;\">��ǰ��:"+prd.getName()[0]+"<br> "
							+ "<input type=\"button\"  onclick=\"prevCount('product"+i+"','price"+i+"',"+productPrice+")\" value=\"&#10094;\">\r\n" + 
							"	  <input id=\"product"+i+"\" type=\"text\" name=\""+productId[i]+"\" style=\"text-align:right;width: 20px; border: 0px;\" value=\""+productCount[i]+"\" readonly/>��\r\n" + 
							"	  <input type=\"button\"  onclick=\"nextCount('product"+i+"','price"+i+"',"+productPrice+")\" value=\"&#10095;\">"
							+ "<br>����:"+ "<input id=\"price"+i+"\" type=\"text\" name=\"\"+productName[i]+\"\" style=\"width: 60px; border: 0px; text-align:right;\" value=\""
							+Integer.parseInt(productCount[i])*productPrice+"\" readonly/>�� "
							+"\r\n</div></div>";
			totalPrice+=productPrice*Integer.parseInt(productCount[i]);

		}
	pocketCode+="<input type=\"submit\" onmouseover=\"pocketAction('pocketSave.jsp')\" value=\"��ٱ��� ����\" style=\"position: fixed; top:553px; left: 133px; width: 100px;\">\r\n" + 
			"  	<input type=\"submit\" value=\"�����ϱ�\" style=\"position: fixed; top:580px; left: 133px; width: 100px;\">"
			+ "</form>\r\n<div style=\"position: fixed;top: 552px ; left:17px;\"> �����ݾ�<br>"
			+ "<input id=\"totalPrice\" type=\"text\" style=\" width: 90px;border:0px;text-align:right;color:blue; font-size:20px;\" "
			+ "value=\""+totalPrice+"\"readonly/>��</div>" + 
			"	</div>";
	}
	prd.close();
}
public String open() {//Top �ڵ� ����

	return	"<style type=\"text/css\">\r\n" + 
			"body{margin:0px;}"
			+ "#top #link1:hover{color:#705e15;}"
			+ "#top #link2:hover{color:#705e15;}"
			+ "#top #link3:hover{color:#705e15;}"
			+ "#top #link4:hover{color:#705e15;}"
			+ "#top a:visited { text-decoration:none;color:black;}"
			+ "#top a{ text-decoration:none;color:black;}"+
			"#top #mainTop1{ position: fixed; height: 120px;width:100%; z-index:3;\r\n" + 
			"         border-bottom: black 2px solid; vertical-align: bottom; \r\n" + 
			"        background:#adadad;}\r\n" + 
			"#top #mainTopIms{ margin: 10px 10px 0px 20px; }\r\n" + 
			"#top #mainTopTi{margin: 0px auto; text-align:center; font-size: 54px; height:80px;}\r\n" + 
			"#top #tSpan:hover{font-size:56px;}\r\n" + 
			"#top #ca1{visibility:hidden; opacity: 0.7; z-index:2; border-bottom:0.5px white solid;\r\n" + 
			"      margin-top: 122px; height:190px; width:100%;position: fixed; background-color: white;} \r\n" + 
			"#top #ca2{text-align:center;  height: 100% ;margin: 0px auto; }\r\n" + 
			"#top #ca3{display:inline-block; vertical-align:top; font-size: 18px; width: 80px;height: 100%;margin-top: 15px;}\r\n" + 
			"#top #mainTopCa{margin:0px auto;font-size: 25px; text-align: center;height: 35px; width: 400px; }\r\n"
			+ "#pocket{visibility: hidden;}#member{visibility: hidden;}#search{visibility: hidden;}" + 
			"</style>"
			+ "<script type=\"text/javascript\">\r\n" + 
			"function setManualSession(manual){"
			+ "sessionStorage.setItem('manual',manual);"
			+ "}"+
			"function getManualSession(){"
			+ "var manualNum=sessionStorage.getItem('manual');"+
			"if(manualNum=='0'){}" + 
			"if(manualNum=='1'){" + 
			"	memberClick();}" + 
			"if(manualNum=='2'){" + 
			"	searchClick();}" + 
			"if(manualNum=='3'){" + 
			"	pocketClick();}"
			+ "if(manualNum==null){};"  
			+ "}"
			+ "function sessionClear(){"
			+ "sessionStorage.clear();"
			+ "}"
			+ "function viPocket(id){"+
			"document.getElementById(id).style.visibility=\"visible\";"  
			+ "}"
			+ "function hiPocket(id){"
			+ "document.getElementById(id).style.visibility='hidden';"
			+ "}"+	
			"function mov(){\r\n" + 
			"   document.getElementById(\"ca1\").style.visibility=\"visible\";\r\n" + 
			"}\r\n" + 
			"function mot(){\r\n" + 
			"	document.getElementById(\"ca1\").style.visibility=\"hidden\";	\r\n" + 
			"}\r\n" + 
			"function searchClick(){	\r\n" + 
			"		document.getElementById(\"search\").style.visibility=\"visible\";\r\n" + 
			"		document.getElementById(\"searchImg\").style.borderBottom=\"0px\";		\r\n" + 
			"		document.getElementById(\"searchImg\").setAttribute( 'onclick', 'searchClick1()' );\r\n" + 
			"		memberClick1();\r\n" + 
			"		pocketClick1();\r\n"
			+ "		setManualSession('2');" + 
			
			"}\r\n" + 
			"function searchClick1(){	\r\n" + 
			"		document.getElementById(\"search\").style.visibility=\"hidden\";\r\n" + 
			"		document.getElementById(\"searchImg\").style.border=\"1px black solid\";\r\n" + 
			"		document.getElementById(\"searchImg\").setAttribute( 'onclick', 'searchClick()' );\r\n" + 
			"setManualSession('0');"
			+ "} "+ 
			"function memberClick(){	\r\n" + 
			"	document.getElementById(\"member\").style.visibility=\"visible\";\r\n" + 
			"	document.getElementById(\"memberImg\").style.borderBottom=\"0px\";		\r\n" + 
			"	document.getElementById(\"memberImg\").setAttribute( 'onclick', 'memberClick1()' );\r\n" + 
			"	searchClick1();\r\n" + 
			"	pocketClick1();\r\n"
			+ "	setManualSession('1');" + 
			"}\r\n" + 
			"function memberClick1(){	\r\n" + 
			"	document.getElementById(\"member\").style.visibility=\"hidden\";\r\n" + 
			"	document.getElementById(\"memberImg\").style.border=\"1px black solid\";\r\n" + 
			"	document.getElementById(\"memberImg\").setAttribute( 'onclick', 'memberClick()' );\r\n" + 
			"setManualSession('0');"
			+ "}\r\n" + 
			"function pocketClick(){	\r\n" + 
			"	document.getElementById(\"pocket\").style.visibility=\"visible\";\r\n" + 
			"	document.getElementById(\"pocketImg\").style.borderBottom=\"0px\";		\r\n" + 
			"	document.getElementById(\"pocketImg\").setAttribute( 'onclick', 'pocketClick1()' );\r\n" + 
			"	searchClick1();\r\n" + 
			"	memberClick1();\r\n"
			+ "	setManualSession('3');" + 
			"}\r\n" + 
			"function pocketClick1(){	\r\n" + 
			"	document.getElementById(\"pocket\").style.visibility=\"hidden\";\r\n" + 
			"	document.getElementById(\"pocketImg\").style.border=\"1px black solid\";\r\n" + 
			"	document.getElementById(\"pocketImg\").setAttribute( 'onclick', 'pocketClick()' );\r\n" + 
			"setManualSession('0');"
			+ "}	\r\n"
		
			+ "function nextCount(name,priceId,price) {\r\n" +
			"	var totalPrice=document.getElementById(\"totalPrice\").getAttribute('value')*1;"+
			"	count=document.getElementById(name).getAttribute('value')*1;\r\n"
			+ "count++;" + 
			"	document.getElementById(name).setAttribute( 'value', count);\r\n" + 
			"	document.getElementById(priceId).setAttribute( 'value', count*price);\r\n" +
			"totalPrice+=price;"+
			"	document.getElementById(\"totalPrice\").setAttribute( 'value', totalPrice);\r\n" + 
			"}"  
			+ "function prevCount(name,priceId,price) {\r\n" + 
			"	var totalPrice=document.getElementById(\"totalPrice\").getAttribute('value')*1;"
			+ "count=document.getElementById(name).getAttribute('value')*1;\r\n" + 
			"	if(count!=0){"
			+ "count--;document.getElementById(name).setAttribute( 'value', count);\r\n" + 
			"	document.getElementById(priceId).setAttribute( 'value',count*price);\r\n" +
			"totalPrice-=price;"+
			"	document.getElementById(\"totalPrice\").setAttribute( 'value', totalPrice);\r\n" + 
			"}}"
			+ "function pocketAction(url){"
			+ "document.getElementById(\"pocketForm\").setAttribute( 'action', url);"
			+ "}"
		    +
			"</script>"
			+ "<div id=\"top\">"	
			+ "<div id=\"mainTop1\" style=\"\"><!-- 1 -->\r\n" + 
			"<!-- ========= -->\r\n" + 
			"<div id=\"mainTopIms\" style=\" position: fixed; \">\r\n" + 
			"<img id=\"memberImg\" src=\"im/imMem.png\" height=\"32\" width=\"32\"style=\"border: 1px black solid;margin: 0px;\" onclick=\"memberClick()\"/>\r\n" + 
			"<img id=\"searchImg\" src=\"im/imSearch.png\"height=\"32\" width=\"32\"style=\"border: 1px black solid; margin: 0px;\" onclick=\"searchClick()\"/>\r\n" + 
			"<img id=\"pocketImg\" src=\"im/imList.png\"height=\"32\" width=\"32\"style=\"border: 1px black solid;margin: 0px;\" onclick=\"pocketClick()\"/>\r\n" + 
			"</div>\r\n" + 
			"<div id=\"pocket\" style=\" z-index:8; background-color: white; height: 570px;position: fixed;top: 44px;left: 10px; z-index:5; width: 250px; border-radius: 5px/5px;border: 0.5px black solid;\">\r\n" + 
			"<div style=\"width:32px; height:2px;background-color: white; position:fixed; top:43px; left:100px;\"></div>" + 
			"<span style=\"font-size: 14px;\">&nbsp��ٱ���</span><br>\r\n" + 
			pocketCode+
			"\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div id=\"member\" style=\" z-index:8; background-color: white; height: 200px;position: fixed;top: 44px;left: 10px; z-index:5; width: 150px; border-radius: 5px/5px;border: 0.5px black solid;\">\r\n" + 
			"<div style=\"width:32px; height:2px;background-color: white; position:fixed; top:43px; left:21px;\"></div>"
			+ "<span style=\"font-size: 14px;\">&nbspȸ������</span><br>\r\n" + 
			name+"�� ȯ���մϴ�<br> " + 
			"<div onclick='sessionClear()'><a href=\"lo.html \">�α׾ƿ�</a></div>" + 
			"</div>\r\n" + 
			"\r\n" + 
			/* �˻���ɺ� */"<div id=\"search\" style=\"z-index:8; background-color: white; height: 40px;position: fixed;top: 44px;left: 10px; z-index:5; width: 270px; border-radius: 5px/5px;border: 0.5px black solid;\">\r\n" + 
			"<div style=\"width:32px; height:2px;background-color: white; position:fixed; top:43px; left:61px;\"></div>"+		 
			"<form action=\"search.jsp\" style=\"\">\r\n"
			+ "<select name=\"category\" style=\"float:left;margin:6px 0px 0px 7px; height:23px; \">\r\n" + 
			"      <option value=\"��ü\">��ü</option>\r\n" + 
			"      <option value=\"����\">����</option>\r\n" + 
			"      <option value=\"�ð�\">�ð�</option>\r\n" + 	
			"      <option value=\"�����\">�����</option>\r\n" + 
			"      <option value=\"����\">����</option>\r\n" + 		
			" </select>" + 
			"<input type=\"text\" style=\"border:0px; border-bottom:0.5px black solid;margin:11px 0px 0px 10px;z-index:8;width: 150px; float: left;\"name=\"keyword\">\r\n" + 
			"<input type=\"submit\" value=\"\" style=\"background-image: url(im/imSearch.png); border:0px; padding:0px; width: 32px;height: 32px; background-size:32px; margin:4px 0px 0px 3px;\">\r\n" + 
			"</form>\r\n" + 
			"</div>\r\n" + 
			"<!-- ========= -->\r\n" + 
			"<div id=\"mainTopTi\" style=\"\">\r\n" + 
			"<a id=\"goMain1\"href=\"main1.jsp\" >\r\n" + 
			"<span id=\"tSpan\" style=\" font-family: fantasy; \"><span style=\"color:white;  font-family: fantasy;\">B</span>iz&nbsp&nbsp&nbsp<span style=\"color:white; font-family: fantasy; \">W</span>atch</span>\r\n" + 
			"</a>\r\n" + 
			"</div>\r\n" + 
			"<!-- ========= -->\r\n" + 
			"\r\n" + 
			"<!-- ========= -->\r\n" + 
			" <div id=\"mainTopCa\"style=\"\"onmouseover=\"mov()\" onmouseout=\"mot()\">\r\n" + 
			"<a id=\"link1\" href=\"search.jsp?category=����&keyword=\" >&nbsp ����  &nbsp</a>\r\n" + 
			"<a id=\"link2\" href=\"search.jsp?category=�ð�&keyword=\" >&nbsp �ð�  &nbsp</a>\r\n" + 
			"<a id=\"link3\" href=\"search.jsp?category=�����&keyword=\" >&nbsp �����  &nbsp</a>\r\n" + 
			"<a id=\"link4\" href=\"search.jsp?category=����&keyword=\" >&nbsp ����  &nbsp</a>\r\n" + 
			"</div>\r\n" + 
			"<!-- ========= -->\r\n" + 
			"</div><!-- 1 -->\r\n" + 
			"<!-- ������ ī�װ� -->\r\n"
			+ "<script> "
			+ "getManualSession();"
			+
			 "</script> "+ 
				/*
				 * "<div id=\"ca1\" onmouseover=\"mov()\" onmouseout=\"mot()\" style=\"\"><!-- 2 -->\r\n"
				 * + "<div id=\"ca2\" style=\" \">\r\n" + "<div id=\"ca3\" style=\"\">\r\n" +
				 * "����1<br>����2<br>����3<br>����4<br>����5</div>\r\n" +
				 * "<div id=\"ca3\" style=\"\">\r\n" +
				 * "�ð�1<br>�ð�2<br>�ð�3<br>�ð�4 <br>�ð�5<br>�ð�5</div>\r\n" +
				 * "<div id=\"ca3\" style=\"\">\r\n" + "�����1<br>�����1</div>\r\n" +
				 * "<div id=\"ca3\"  style=\"\">\r\n" +
				 * "����1<br>����1<br>����1<br>����1<br>����</div>\r\n" + "</div>\r\n" +
				 * "</div><!-- 2 -->" + "</div>"
				 */
			 "<div style=\"margin:130px auto;width:100%;float: left;\"> ";//�������� �� div�� ������
}
public String close() {//�������� �� div�� �ݾ���
	return "</div>";
}
}
