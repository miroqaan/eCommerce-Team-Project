<%@ page import="project.Top"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<% String id = (String)session.getAttribute("idKey"); %>
	
	<% 
	boolean idcheck = false;
	if(id != null){
		idcheck = true;
	}
	
		System.out.println(id);
		System.out.println(idcheck);
		
		
		boolean flag = false;
		
		if(id == null){
		
	    Cookie[] cookies = request.getCookies();
	    
	    
		for(int i = 0; i < cookies.length; ++i){ // nonId 쿠키가 있다면 flag를 true로 선언한다.
			if(cookies[i].getName().equals("nonId")){
				flag = true;
			}
		}
		System.out.println(flag);

		
		if(!(flag)){ // nonId 쿠키가 없다면 해당 쿠키를 생성한다.
			
			Cookie cookie = new Cookie("nonId", "non");
			cookie.setMaxAge(120*60);
			cookie.setValue("non");
			response.addCookie(cookie);

		}
	}
		
		
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#main2{margin:20px auto;  height:100%; width:1095px; }

.container{width: 350px;z-index: 1;text-align: center;height: 300px;
         margin:7px;float: left;}
.image{opacity: 1;position: relative;width: 20px;
      height: auto;transition: .5s ease;backface-visibility: hidden;}
.middle{position: relative;transition: .5s ease;opacity: 0;display:inline-block; 
      vertical-align:top; left:50px; top: -170px;}
.container:hover .image{opacity: 0.3;}
.container:hover .middle{opacity: 1;}
.text{background-color: #4CAF50;color: white;
      font-size: 16px;padding: 16px 32px;}
.slideshow-container {
  width: 600px;
  height 400px;
  position: relative;
  margin: auto;
  
  
}
/* Hide the images by default */
#a {
  display: none;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  margin-top: -22px;
  padding: 16px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
  background-color: rgba(0,0,0,0.2);
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}

/* Caption text */

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active, .dot:hover {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}



</style>
<script type="text/javascript">
var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}
</script>
</head>
<body style="">
<%-- <%
Top top=new Top(request);%>
<%=top.open()%> --%>

<style>
header{
font-size: 35px;
text-align: center;

}


nav{
float:right;
}

</style>

<header>
BizWatch


</header>
<nav>
<%if(idcheck == false){ %>
<a href="login.jsp">로그인</a>


<%} %>

<%if(idcheck == true){ 
	
%>
<a href="logout.jsp">로그아웃</a>
<%} %>

	<%

	if(flag == true){
	
	%>
	<br/>
	비회원 로그인 상태입니다.
	<%} %>
	</nav>
	
<div style="border-top: 1px black solid;border-bottom: 1px black solid;width: 600px;margin: 20px auto; height: 450px; padding:30px;">
<div class="slideshow-container">
 
  <div class="mySlides fade">
    <div class="numbertext">1 / 3</div>
    <img src="im/im1.png" style="width:600px;height: 400px;">
  </div>
  <div id="a"class="mySlides fade">
    <div class="numbertext">2 / 3</div>
    <img src="im/im2.png" style="width:600px;height: 400px;">
  </div>
  <div id="a"class="mySlides fade">
    <div class="numbertext">3 / 3</div>
    <img src="im/im3.png" style="width:600px; height: 400px;">
  </div>
  <!-- Next and previous buttons -->
  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>
  </div>
<br>
<!-- The dots/circles -->
<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span>
  <span class="dot" onclick="currentSlide(2)"></span>
  <span class="dot" onclick="currentSlide(3)"></span>

</div>
</div>

<div id="main2" style=""><!-- 3-1 -->

<div class="container" ><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px; font-style: italic; ">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px; font-style: italic; ">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle" style="">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px; font-style: italic;">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px;font-style: italic; ">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px;font-style: italic; ">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px;font-style: italic; ">super watch</span>
</div>

<div class="container"><!-- 상품 -->
<img src="im/product1.png" alt="상품 1" class="image" style="width:100%">
<div class="middle">
<div class="text">price: \30,000</div>
</div>
<span style="float: right ; margin-right: 10px;font-style: italic; ">super watch</span>
</div>

</div><!-- 3-1 -->
<%-- 
<%=top.close()%> --%>
</body>
</html>