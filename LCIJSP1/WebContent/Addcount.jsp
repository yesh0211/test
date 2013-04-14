<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LCI - AddCount</title>
<link href="css/allcss.css" rel="stylesheet" type="text/css">
<script src="js/jQuery1.8.3.js"></script>
<script src="js/allextend-jquery.js" language="JavaScript"></script>
<script src="js/addcountjs.js" language="JavaScript"></script>

</head>
<body>
<div id="addcust" align="center"><form name="mainform" id="mainform" action="Invoice" method="post">
  <table width="310" height="auto" class="string">
    <tr>
    <td colspan="2" class="labelH3">New Count
    <input type="hidden" name="action" id="action" value="addcount">
    <input type="hidden" name="page" id="page" value="Addcount">
    </td>
    <td width="26" ><div class="right_align">
      <!-- <input name="close" type="button" class="closebutton" id="close" value="X"> -->
    </div></td>
    </tr>
  <tr>
  
    <td width="102" class="label"> Count Name</td>
    <td colspan="2"><input name="countname" type="text" id="countname"></td>
    </tr>
  <tr>
    <td><%-- <input name="back" type="button" class="button" id="back" value="<= back"> --%></td>
    <td width="173"><input name="submit" id="submit" type="submit" class="button" value="Add"></td>
    <td></td>
  </tr>
</table></form></div>

</body>
</html>