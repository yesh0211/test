<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LCI - AddCust</title>
<link href="css/allcss.css" rel="stylesheet" type="text/css">
<script src="js/jQuery1.10.2.js"></script>
<script src="js/allextend-jquery.js" language="JavaScript"></script>
<script src="js/addcustjs.js" language="JavaScript"></script>

</head>
<body>
<div id="addcust" align="center"><form name="mainform" id="mainform" action="Invoice" method="post">
  <!-- <table width="310" height="302" class="string"> -->
  <table width=auto height=auto class="string">
    <tr>
    <td colspan="2" class="labelH3">New Trader
    <input type="hidden" name="action" id="action" value="addtrader">
    <input type="hidden" name="page" id="page" value="Addcust">
    </td>
    <td width="26" ><div class="right_align">
      <!-- <input name="close" type="button" class="closebutton" id="close" value="X"> -->
    </div></td>
    </tr>
  <tr>
  
    <td width="102" class="label"> Name</td>
    <td colspan="2"><input name="tradername" type="text" id="tradername"></td>
    </tr>
  <tr>
    <td class="label">Type</td>
    <td colspan="2"><p>
      <label> <span class="label">
        <input type="radio" name="custtype" value="B" id="custtype_0">
        Credit Buyer</span></label>
      <span class="label">
        <label>
          <input type="radio" name="custtype" value="C" id="custtype_1">
          Cash Buyer</label>
          <label>
          <input type="radio" name="custtype" value="S" id="custtype_2">
          Supplier</label>
      </span> </p></td>
    </tr>
  <tr>
    <td class="label">Address</td>
    <td colspan="2"><textarea name="address" cols="20" rows="3" id="address"></textarea></td>
    </tr>
  <tr>
    <td class="label">Telephone 1</td>
    <td colspan="2"><input name="tel1" type="text" class="dynnumint" id="tel1" maxlength="11"></td>
    </tr>
  <tr>
    <td class="label">Telephone 2</td>
    <td colspan="2"><input name="tel2" type="text" class="dynnumint" id="tel2" maxlength="11"></td>
    </tr>
  <tr>
    <td class="label">Telephone 3</td>
    <td colspan="2"><input name="tel3" type="text" class="dynnumint" id="tel3" maxlength="11"></td>
    </tr>
  <tr>
    <td class="label">TIN No</td>
    <td colspan="2"><input name="tin" type="text" class="dynnumint" id="tin" maxlength="11"></td>
    </tr>
  <tr>
    <td class="label">e-Mail Id</td>
    <td colspan="2"><input name="emailid" type="text" class="string" id="emailid"></td>
    </tr>
  <tr>
    <td><%-- <input name="back" type="button" class="button" id="back" value="<= back"> --%></td>
    <td width="173"><input name="submit" type="submit" class="button" id="submit" value="Add"></td>
    <td></td>
    </tr>
</table></form></div>

</body>
</html>