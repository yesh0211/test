$(document).ready(function(){
	
	/*function loadtag(e){
		$("#framediv").load(e.data.link);
		return false;
	};*/
	
	
	switch($("#mainpage").val())
	{
	case "Invoice" :
		$("#framediv").load("Invoice.jsp?mode=edit");	
		break;
	case "Voucher" :
		$("#framediv").load("Voucher.jsp?mode=edit");
		break;
	case "Cashbill" :
		$("#framediv").load("Cashbill.jsp?mode=edit");
		break;
	case "Addcust" :
		$("#framediv").load("Addcust.jsp?mode=edit");
		break;
	case "Addcount" :
		$("#framediv").load("Addcount.jsp?mode=edit");
		break;
	default:
				
	}
	
	
	/*if ($("#mainpage").val()=="Invoice")
		$("#framediv").load("Invoice.jsp?mode=edit"); 
		 
	
	$('#link1').on('click',{link:"Invoice.jsp?mode=edit"},loadtag);
	$('#link2').on('click',{link:"Cashbill.jsp?mode=edit"},loadtag);
	$('#link3').on('click',{link:"Voucher.jsp?mode=edit"},loadtag);
	$('#link4').on('click',{link:"Addcust.jsp?mode=edit"},loadtag);
	$('#link5').on('click',{link:"Addcount.jsp?mode=edit"},loadtag);*/
	
		
		
});

