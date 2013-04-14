$.fn.changeVal = function (v) {
	return $(this).val(v).trigger("change"); 
    
};

function trueRound(value, digits){
    return (Math.round((value*Math.pow(10,digits)).toFixed(digits-1))/Math.pow(10,digits)).toFixed(digits);
};

function readOnlySelected(e){    
    var elem = $(e.currentTarget);
    if (elem.val() == "") {
       elem.blur();                
    } else {
       elem.select();
    }
};

function intOnly(e) {
	$(this).val(($(this).val()).replace(/[^0-9]/,""))
};

function decOnly(e) {
	var regexp=/[.]/;
	var str;
	if(regexp.test(str=$(this).val()))
		{
		var ind=str.search(/\./);
		$(this).val(str.substr(0,(ind+1)).concat(str.substr(ind+1,e.data.dec).replace(/[^0-9]/,"")));
		}
	else
		$(this).val($(this).val().replace(/[^0-9.]+/,""));
};


function frameResize(e){
	    $(this).height($(this).contents().height());
	    $(this).width($(this).contents().width());
};

$.fn.resetForm = function () {
	  $(this).each (function() { this.reset(); });
	}