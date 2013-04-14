$(document).ready(function(){

	$("#submit").click(function(){
		$.post('Invoice', $("#mainform").serialize(),
				function(data){
			alert(data);
			
		});
		$("#mainform").resetForm();
		return false;
	});
});