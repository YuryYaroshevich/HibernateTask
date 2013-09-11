$(document).ready(function() {
	$("form").submit(function() {
		var $input = $(this).find("#empls-number");
		return isValid($input.val(), $(this));
	});
});

function isValid(text, $form) {
	var regExp = /^\d+$/;
	if (regExp.test(text)) {
		return true;
	} else {
		if ($("span.error-msg").length == 0) {
			var $errorMsg = $("<span class='error-msg'>" +
				" There is should be a positive integer!</span>");
		    $errorMsg.appendTo($form);
	    }
		return false;
	}
}