<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LCI - Login</title>

<style>
.sign-in {
  width: 335px;
  float: right;
  }
.signin-box,
  .accountchooser-box {
  margin: 12px 0 0;
  padding: 20px 25px 15px;
  background: #f1f1f1;
  border: 1px solid #e5e5e5;
  }
  .g-button {
  display: inline-block;
  min-width: 46px;
  text-align: center;
  color: #488;
  font-size: 11px;
  font-weight: bold;
  height: 27px;
  padding: 0 8px;
  line-height: 27px;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  border-radius: 2px;
  -webkit-transition: all 0.218s;
  -moz-transition: all 0.218s;
  -ms-transition: all 0.218s;
  -o-transition: all 0.218s;
  transition: all 0.218s;
  border: 1px solid #dcdcdc;
  background-color: #f5f5f5;
  background-image: -webkit-gradient(linear,left top,left bottom,from(#f5f5f5),to(#f1f1f1));
  background-image: -webkit-linear-gradient(top,#f5f5f5,#f1f1f1);
  background-image: -moz-linear-gradient(top,#f5f5f5,#f1f1f1);
  background-image: -ms-linear-gradient(top,#f5f5f5,#f1f1f1);
  background-image: -o-linear-gradient(top,#f5f5f5,#f1f1f1);
  background-image: linear-gradient(top,#f5f5f5,#f1f1f1);
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  cursor: default;
  }
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
<script type="text/javascript">
$(document).ready(function()
		{
	alert("hi");
//	alert(session.getAttribute("login"));
//	if(session.getAttribute("login")==true)
//		$("#loginform").hide("slow");
		
	//alert("hi");
	$("#username").keyup(function()
			{
		if($("#username").val()!="")
			{
			$("#passworddiv").show("fast");
			$("#password").keyup();
			}
		else
			{
			$("#passworddiv").hide("fast");
			$("#login").hide("fast");
			}
		});
	$("#password").keyup(function()
			{
		if($("#password").val()!="")
			$("#login").show("fast");
		else
			$("#login").hide("fast");
		});
	});
			
</script>
</head>
<body>
	<br>
	<br>
	<div style="sign-in">
	<div style="Signin-box">
		<H1>Welcome to LCI</H1>
		<br> <br>
		<form id="loginform" action="Login" method="post">
		<div id="usernamediv">
			<label for="lusername">Username : </label> <input type=text
				name="tusername" id="username"> </div>
				<div id="passworddiv" style="display: none">
				<br> <label for="lpassword">Password : </label> <input
				type="password" name="tpassword" id="password"> <br> </div> <br> <input
				type="hidden" name="laction" value="login"> <input
				type="submit" class="g-button" id="login" value="Login" style="display: none">
			<br>
			<%
String lmsg;
lmsg=request.getParameter("msg");
if (lmsg==null)lmsg="";
%>
			<label for="lmsg"> <%=lmsg %>
			</label>
		</form>
	</div></div>
</body>
</html>