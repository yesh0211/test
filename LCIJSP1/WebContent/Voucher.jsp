<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,lcijava.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LCI - Voucher</title>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css" />
<!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />-->
<link rel="stylesheet" href="css/allcss.css" />
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
-->
<script src="js/jQuery1.10.2.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js" ></script>
 
<script src="js/allextend-jquery.js" language="JavaScript"></script>
<script src="js/invoicejs.js" language="JavaScript"></script>

</head>
<body>
<form id="mainform" name="mainform" >
  <table width="100%" cellspacing="1" >
    <tr>
      <td colspan="5"><center>
	  <H1 class="labelH1"> Voucher </H1>
	  </center>	 
	  <input type="hidden" id="mode" value="<%String mode=request.getParameter("mode"); out.write(mode);%>">
	  <input type="hidden" name="action" id="action" value="bought">
	  <input type="hidden" name="page" id="page" value="Voucher"> 
	  </td>
    </tr>
    <tr>
      <td colspan="2" rowspan="2">
        <div align="center">
          <select name="custid" class="data" id="cust">
            <%
            Voucher i = new Voucher();
            Inventory in = new Inventory();
      {
LinkedHashMap <String,String> cl;
out.write("<option value='0'>"+"Select Customer"+"</option> \n");
for (Map.Entry<String,String> e : (cl=i.supplierList()).entrySet())
	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
      }

%>
          </select>
          <input name="Addcust" type="button" class="button" id="Addcust" onclick="addCust()" value="+">
        </div></td>
      <td width="25%" height="30" ><div align="right" class="label">Bill No</div></td>
      <td colspan="2"><div align="center" >
        <input type="text" class="string" name="billno" id="billno" 
        value=""> 
        </div></td>
    </tr>
    <tr id="billdaterow">
      <td height="30"><div align="right"><strong class="label">Bill Date</strong></div></td>
      <td colspan="2" ><div align="center" >
        <input name="billdate" type="text" class="data string" id="billdate" >
      </div></td>
    </tr>
    <tr>
      <td height="39" colspan="5">&nbsp;</td>
    </tr>
    <tr class="grid">
      <td width="25%" height="30"><div align="center" class="label"><strong>Count</strong>
        <input name="Addcount" type="button" class="button" id="Addcount" value="+">
      </div></td>
      <td width="25%"><div align="center" class="label"><strong>Quantity</strong></div></td>
      <td><div align="center" class="label"><strong>Rate</strong></div></td>
      <td colspan="2"><div align="center" class="label"><strong>Amount</strong></div></td>
    </tr>
    <tr id="row1">
      <td height="30">
        <div align="center">
          <select name="countid1" class="data" id="count1">
            <%
      LinkedHashMap <String,String> cl = new LinkedHashMap <String,String>();
      out.write("<option value='0'>"+"Select Count"+"</option> \n");
      for (Map.Entry<String,String> e : ((cl=in.countList())==null?(cl=new LinkedHashMap <String,String> ()):cl).entrySet())
    	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
         	  
      %>
          </select>
      </div></td>
      <td height="30"><div align="center">
        <input name="qty1" type="text" class="dynnumdec data" id="quantity1" >
      </div></td>
      <td height="30"><div align="center">
        <input name="rate1" type="text" class="dynnumdec data" id="rate1">
      </div></td>
      <td height="30" colspan="2" align="center"><input name="amt1" type="text" class="disableddata amount numdec" id="amt1"></td>
    </tr>
    <tr id="row2">
      <td height="30">
		<div align="center">
		  <select name="countid2" class="data" id="count2">
		    <%
      	out.write("<option value='0'>"+"Select Count"+"</option> \n");
        for (Map.Entry<String,String> e : cl.entrySet())
      	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
        %>
          </select>
      </div></td>
      <td height="30"><div align="center">
        <input name="qty2" type="text" class="dynnumdec data" id="quantity2">
      </div></td>
      <td height="30"><div align="center">
        <input name="rate2" type="text" class="dynnumdec data"  id="rate2">
      </div></td>
      <td height="30" colspan="2" align="center"><input name="amt2" type="text" class="disableddata amount numdec" id="amt2"></td>
    </tr>
    <tr id="row3">
      <td height="30">
        <div align="center">
          <select name="countid3" class="data" id="count3">
            <%
      	out.write("<option value='0'>"+"Select Count"+"</option> \n");
        for (Map.Entry<String,String> e : cl.entrySet())
      	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
      	%>
          </select>
      </div></td>
      <td height="30"><div align="center">
        <input name="qty3" type="text" class="dynnumdec data" id="quantity3">
      </div></td>
      <td height="30"><div align="center">
        <input name="rate3" type="text" class="dynnumdec data"  id="rate3">
      </div></td>
      <td height="30" colspan="2" align="center" ><input name="amt3" type="text" class="disableddata amount numdec"  id="amt3"></td>
    </tr>
    <tr id="row4">
      <td height="30"><div align="center">
        <select name="countid4" class="data" id="count4">
          <%
      	out.write("<option value='0'>"+"Select Count"+"</option> \n");
        for (Map.Entry<String,String> e : cl.entrySet())
      	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
      	%>
        </select>
      </div></td>
      <td height="30"><div align="center">
        <input name="qty4" type="text" class="dynnumdec data" id="quantity4">
      </div></td>
      <td height="30"><div align="center">
        <input name="rate4" type="text" class="dynnumdec data" id="rate4">
      </div></td>
      <td height="30" colspan="2" align="center" ><input name="amt4" type="text" class="disableddata amount numdec" id="amt4"></td>
    </tr>
    <tr id="row5">
      <td height="30"><div align="center">
        <select name="countid5" class="data" id="count5">
          <%
      	out.write("<option value='0'>"+"Select Count"+"</option> \n");
        for (Map.Entry<String,String> e : cl.entrySet())
      	  out.write("<option value='"+e.getKey()+"'>"+e.getValue()+"</option> \n");
      	%>
        </select>
      </div></td>
      <td height="30"><div align="center">
        <input name="qty5" type="text" class="dynnumdec data" id="quantity5">
      </div></td>
      <td height="30"><div align="center">
        <input name="rate5" type="text" class="dynnumdec data" id="rate5">
      </div></td>
      <td height="30" colspan="2" align="center" ><input name="amt5" type="text" class="disableddata amount numdec" id="amt5"></td>
    </tr>
    <tr class="grid">
      <td height="30" colspan="3" valign="middle"><div align="right" class="label"><strong>Total</strong></div></td>
      <td colspan="2" valign="middle" align="center"><input name="total" type="text" class="disableddata numdec" id="total"></td>
    </tr>
    <tr class="grid">
      <td height="30" colspan="3" valign="middle"><div align="right" class="label"><strong>VAT @ <%String vat =in.vat();%><%=vat%>% </strong></div></td>
      <td colspan="2" valign="middle" align="center" > 
      <input type="hidden" id="vatpc" value="<%=vat%>">
      <input type="text" name="vatamt" class="disableddata numdec" id="vat">
      </td>
    </tr>
    <tr class="grid">
      <td height="30" colspan="3" valign="middle"><div align="right" class="label"><strong>Bill Amount </strong></div></td>
      <td colspan="2" valign="middle"  align="center"><input type="text" class="impdisableddata numdec" id="billamt" name="billamt"></td>
    </tr>
    <tr>
      <td height="30" colspan="3"></td>
      <td width="12%"><div align="center">
        <input id="submit" name="submit" type="submit" class="button" value="Bought">
      </div></td>
      <td width="13%"><div align="center">
        <input name="reset" type="reset" class="button" id ="reset" value="Reset">
      </div></td>
    </tr>
  </table>
</form>
<br>

<div id="overlay" class="web_dialog_overlay"></div>
<iframe src="" id="dilog" > hi boss</iframe><!-- class="web_dialog" -->


</body>
</html>