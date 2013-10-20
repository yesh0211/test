$(document).ready(function()
		{
			// reset count fields
			$("#reset").click(function(){
				$("#count1").changeVal(0);
				$(".grid").hide("fast");
				$("#row1").hide("fast");
				$("#billdaterow").hide("fast");
			});
			
			$("#submit").click(function(){
				$.post('Invoice', $("#mainform").serialize(),
						function(data){
					if($("#page").val()==("Invoice" || "Cashbill"))
						{
						if(data!=null)
							{
							alert("Sold successfully, Bill no:" + data);
							$("#billno").val(parseInt(data)+1);
							}
						else
							alert("Not Sold");
						}
					else
						alert(data);
					
			});
			$("#reset").click();
			return false;
			});
			
			
			//numeric fields integer
			$('.dynnumint').on('keydown keyup keypress blur change', intOnly);
			
			
			//numeric fields with decimal 3
			$('.dynnumdec').on('keydown keyup keypress blur change', {dec:"3"} ,decOnly);
			
			
			//trigger to calculate amount
			$('.dynnumdec').on('keyup', function() {
				calAmt();
			});
			
			//calculate amount values
			
			function calAmt()
			{
				//alert("cal");
				if($("#count1").val()!="0")
				{
	if(($.isNumeric($("#quantity1").val())) & ($.isNumeric($("#rate1").val())))
		$("#amt1").changeVal(trueRound($("#quantity1").val()*$("#rate1").val(),2));
	else
		$("#amt1").changeVal("");
				}
				if($("#count2").val()!="0")
				{
	if(($.isNumeric($("#quantity2").val())) & ($.isNumeric($("#rate2").val())))
		$("#amt2").changeVal(trueRound($("#quantity2").val()*$("#rate2").val(),2));
	else
		$("#amt2").changeVal("");
				}
				if($("#count3").val()!="0")
				{
	if(($.isNumeric($("#quantity3").val())) & ($.isNumeric($("#rate3").val())))
		$("#amt3").changeVal(trueRound($("#quantity3").val()*$("#rate3").val(),2));
	else
		$("#amt3").changeVal("");
				}
				if($("#count4").val()!="0")
				{
	if(($.isNumeric($("#quantity4").val())) & ($.isNumeric($("#rate4").val())))
		$("#amt4").changeVal(trueRound($("#quantity4").val()*$("#rate4").val(),2));
	else
		$("#amt4").changeVal("");
				}
				if($("#count5").val()!="0")
				{
	if(($.isNumeric($("#quantity5").val())) & ($.isNumeric($("#rate5").val())))
		$("#amt5").changeVal(trueRound($("#quantity5").val()*$("#rate5").val(),2));
	else
		$("#amt5").changeVal("");
				}
			}
			//trigger to calculate the total, vat and bill amt
			$(".amount").change(function () {
				var amount;
				if($.isNumeric($('#amt1').val()))
					{
					amount= parseFloat($('#amt1').val());
					if($.isNumeric($('#amt2').val()))
						amount= parseFloat(amount)+parseFloat($('#amt2').val());
					if($.isNumeric($('#amt3').val()))
						amount= parseFloat(amount)+parseFloat($('#amt3').val());
					if($.isNumeric($('#amt4').val()))
						amount= parseFloat(amount)+parseFloat($('#amt4').val());
					if($.isNumeric($('#amt5').val()))
						amount= parseFloat(amount)+parseFloat($('#amt5').val());
					if($.isNumeric(amount))
						{
						$('#total').val(amount.toFixed(2));
						$('#vat').val(trueRound((($('#vatpc').val()/100)*amount),2));
						$('#billamt').val(Math.round(parseFloat($('#total').val())+parseFloat($('#vat').val())).toFixed(2));
						$("#submit").show();
						}
					}
				else
					{
					$('.amount').val("");
					$('#total').val("");
					$('#vat').val("");
					$('#billamt').val("");
					$("#submit").hide("fast");
					}
			});
			
			
			//on change of customer field
			$("#cust").change(function(){
				if($("#cust").val()=="0")
					{
					$("#billdaterow").hide("fast");
					$("#reset").click();
					}
				else
					$("#billdaterow").show("fast");
			});
			
			
			
			
			//on change of count field
			$("#count1").change(function(){
				calAmt();
				if($("#count1").val()!="0")
					{
					$("#row2").show("fast");
					$("#count2").change();
					}								
				else
					{
					$("#row2").hide("fast");
					$("#row3").hide("fast");
					$("#row4").hide("fast");
					$("#row5").hide("fast");
					$("#amt1").changeVal("");
					}
					
				});
			$("#count2").change(function(){
				calAmt();
				if($("#count2").val()!="0")
				{
					calAmt();
					$("#row3").show("fast");
					$("#count3").change();
					}
				else
					{
					$("#row3").hide("fast");
					$("#row4").hide("fast");
					$("#row5").hide("fast");
					$("#amt2").changeVal("");
					}
					
				});
			$("#count3").change(function(){
				calAmt();
				if($("#count3").val()!="0")
				{
					$("#row4").show("fast");
					$("#count4").change();
					}
				else
					{
					$("#row4").hide("fast");
					$("#row5").hide("fast");
					$("#amt3").changeVal("");
					}
					
				});
			$("#count4").change(function(){
				calAmt();
				if($("#count4").val()!="0")
				{
					$("#row5").show("fast");
					$("#count5").change();
					}
				else
					{
					$("#row5").hide("fast");
					$("#amt4").changeVal("");
					}
					
				});
			$("#count5").change(function(){
				calAmt();
				if($("#count5").val()=="0")
					$("#amt5").changeVal("");
				});
			
			//perform based on mode
			if($("#page").val()!="Voucher")
				{
			if($("#mode").val()=="view")
				{
				$(".data").addClass("disableddata");
				$("#billno").removeClass("disableddata");
				}
			else
				{
				$("#billdate").datepicker( { dateFormat: 'dd-M-yy',
											onSelect: function() {
												$(this).change();
											}
				})
				              .addClass("datepickerfield");					
				$("#billno").addClass("disableddata string");
				}
				}
			else
				{
			if($("#mode").val()=="view")
				{
				$(".data").addClass("disableddata");
			//	$("#billno").removeClass("disableddata");
				}
			else
				{
				$("#billdate").datepicker( { dateFormat: 'dd-M-yy',
					onSelect: function() {
						$(this).change();
					}
				})
				              .addClass("datepickerfield");					
				$("#billno").addClass("data string");
				}
				}
			;
				
				
		
			//Trigger the starting functions
				
			//trigger the reset at first

			$("#reset").trigger('click');
			$(".disableddata, .impdisableddata, .datepickerfield").attr('readonly', true);
			//readOnly function
			$(".disableddata, .impdisableddata").focus(readOnlySelected).click(readOnlySelected);
			
			
			
			//hide until validated
			$("#submit").hide("fast");
			
			
			
			$("#billdate").on("change", function()
			{
				if($("#billdate").value!="")
					{
					$(".grid").show("fast");
					$("#row1").show("fast");					
					}
				else
					{
					$(".grid").hide("fast");
					$("#row1").hide("fast");					
					}
			});		
		
		
		});

function selectCust()
{
	$.ajax({
		//url:
	});
	
};

function addCust()
{
	/*$.get(
		    "Addcust.jsp",
		    //{paramOne : 1, paramX : 'abc'},
		    function(data) {
		       //alert('page content: ' + data);
		    	$("#overlay").show();
		    	$("#dilog").fadeIn(300);
		    	$("#overlay").unbind("click");
		    	//$("#dilog").text(data);
		    	//$("#dilog").html(data);
		    	$("#dilog").attr("src",data);
		       //alert(data.getElementsByTagName("div")[0]);
		    }
		);*/	
	//$("#overlay").show();
	//$("#dilog").fadeIn(300);
	//$("#overlay").unbind("click");
	
	$("#dilog").attr("src","Addcust.jsp");
	$("#dilog").dialog({modal:true,resizable:true});//,height:'auto',width:'auto'
	//$("#dilog").dialog();
};

function addCust1()
{
	//popup dilog
	
	$("#overlay").show();
	$("#dilog").fadeIn(300);
	$("#overlay").unbind("click");
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  xmlDoc=xmlhttp.responseXML;
		  //alert(xmlDoc.getElementById("addcust"));
		 // alert(xmlDoc.getElementsByTagName("div")[0].toString());
		//  alert(xmlDoc.getElementById("addcust").textContent);
		  //$("#dilog").html(xmlDoc.getElementById("addcust"));
		  document.getElementById("dilog").appendchild(xmlDoc.getElementsByTagName("div")[0]);
		  
	    //$("#dilog").html(xmlhttp.responseText);
	    }
	  };
	  
	  xmlhttp.open("GET","Addcust.jsp",true);
	  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xmlhttp.overrideMimeType("text/xml; charset=ISO-8859-1");
	  xmlhttp.send(null);
	  
	  
	  /*xmlhttp.open("POST","Addcust.jsp",true);
	  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xmlhttp.overrideMimeType("text/xml; charset=ISO-8859-1");
	  xmlhttp.send("action=addcust");*/
	  } 
	//alert("demo only");
	
};

