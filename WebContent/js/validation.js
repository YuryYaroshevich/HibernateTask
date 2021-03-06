$(document).ready(function() {
	$("#entites-number-form").submit(function() {
		var $input = $(this).find("#entities-number");
		return isValid($input.val(), $(this));
	});
	
	$("#go-to-page-form").submit(function() {
		var $input = $(this).find("input[name='currentIndex']");
		return isValid($input.val(), $(this));
	});
});

function isValid(text, $form) {
	var regExp = /^\d+$/;
	if (regExp.test(text)) {
		return true;
	} else {
		if ($("span#err-msg-" + $form.attr("id")).length == 0) {
			var $errorMsg = $("<span class='error-msg' id='err-msg-" + $form.attr("id") + "'>" +
				" There is should be a positive integer!</span>");
		    $errorMsg.appendTo($form);
	    }
		return false;
	}
}