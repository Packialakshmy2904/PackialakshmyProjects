<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
* {
  box-sizing: border-box;
}
.menu {
  float:left;
  width:20%;
  text-align:center;
}
.menu a {
  background-color:#e5e5e5;
  padding:8px;
  margin-top:7px;
  display:block;
  width:100%;
  color:black;
}
.main {
  float:left;
  width:60%;
  padding:0 20px;
}
.right {
  background-color:#e5e5e5;
  float:left;
  width:20%;
  padding:15px;
  margin-top:7px;
  text-align:center;
}

@media only screen and (max-width:620px) {
  /* For mobile phones: */
  .menu, .main, .right {
    width:100%;
  }
}

.tower1 {
position:absolute;
top:51px;
left:20px;
width:20px;
height: 30px;
background-color: #333;
}
.tower2 {
position:absolute;
top:31px;
left:45px;
width:20px;
height: 50px;
background-color: #33E;
}
.tower3 {
position:absolute;
top:11px;
left:70px;
width:20px;
height: 70px;
background-color: #3A3;
}
.tower4 {
position:absolute;
top:90px;
left:20px;
width:20px;
height: 10px;
background-color: #AAA;
}
.tower5 {
position:absolute;
top:90px;
left:45px;
width:20px;
height: 17px;
background-color: #AAD;
}
.tower6 {
position:absolute;
top:90px;
left:70px;
width:20px;
height: 23px;
background-color: #ADA;
}

</style>
</head>
<body style="font-family:Verdana;color:#aaaaaa;">
<div class="tower1"></div><div class="tower2"></div><div class="tower3"></div><div class="tower4"></div><div class="tower5"></div><div class="tower6"></div>
<div style="background-color:#e5e5e5;padding:15px;">
  <h1 style="text-align:center;">Learn, Share, Build</h1>
  Doubt
  <br>Rectifier
</div>

<div style="overflow:auto">
  <div class="menu">
    <a href="Welcome.jsp">Logout</a>
    <a href="#">About us</a>
    <a href="#">Contact us</a>
  </div>

  <div class="main">
    <form:form id="quesForm" modelAttribute="ques" action="quesProcess"
		method="post">
		<input type="hidden" name="uname" id="uname" value="${uname}">
		<textarea name="message" id="message" rows="10" cols="30"></textarea>
		<br>
		<input type="submit">
	</form:form>
    

  </div>

  <div class="right">
    <h2>About</h2>
    <p>Will be updated soon</p>
  </div>
</div>

<div style="background-color:#e5e5e5;text-align:center;padding:10px;margin-top:7px;">© copyright packiyaviji001@gmail.com</div>

</body>
</html>
