$(document).ready(function()
		{
//numeric fields integer
			$('.dynnumint').on('keydown keyup keypress blur change', intOnly);
		
			$("#submit").click(function(){
				$.post('Invoice', $("#mainform").serialize(),
						function(data){
					alert(data);
					//$("#overlay").hide();
					//$("#dilog").fadeOut(300);
					
				});
				$("#mainform").resetForm();
				return false;
			});
			
		});