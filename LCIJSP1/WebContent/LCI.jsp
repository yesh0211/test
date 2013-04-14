<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LCI - MainPage</title>
<link href="css/lcicss.css" rel="stylesheet" type="text/css">
<script src="js/jQuery1.8.3.js"></script>
<script src="js/allextend-jquery.js"></script>
<script src="js/lcijs.js"></script>

</head>
<body>
<div id="banner" class="banner"> </div>
<div class="maindivtab" id="maindivtab">
  <div class="margintab" id="margintab">
    <div class="anothermargintab" id="anothermargintab">
      <ol id="maintab" class="maintab">
        <li id="tab1" class="tabs">
        <a id="link1" class="atag" href="LCI.jsp?page=Invoice" >
	<span class="taborder"></span>
	<span class="tabdata">Invoice</span>
	</a>
        </li>
        <li id="tab2" class="tabs">
        <a id="link2" class="atag" href="LCI.jsp?page=Cashbill" >
	<span class="taborder"></span>
	<span class="tabdata">Cashbill</span>
	</a>
		</li>
        <li id="tab3" class="tabs">
        <a id="link3" class="atag" href="LCI.jsp?page=Voucher" >
	<span class="taborder"></span>
	<span class="tabdata">Voucher</span>
	</a>
		</li>
	
        <li id="tab4" class="tabs">
        <a id="link4" class="atag" href="LCI.jsp?page=Addcust" >
	<span class="taborder"></span>
	<span class="tabdata">Add Trader</span>
	</a>
		</li>
        
        <li id="tab5" class="tabs">
        <a id="link5" class="atag" href="LCI.jsp?page=Addcount" >
	<span class="taborder"></span>
	<span class="tabdata">Add Count</span>
	</a>
		</li>
		
        </ol>
    </div>
  </div>
</div>
<!-- Invoice.jsp?mode=view -->
<input type="hidden" id="mainpage"
value="<%
if(request.getParameter("page")!=null)
{
 out.write(request.getParameter("page"));
}
else
{
 out.write("Default");
}
%>" name="mainpage" > 
<div class="framediv" id="framediv"><center><h1>Lakshmi Cottage Industries</h1></center></div>


</body>
</html>