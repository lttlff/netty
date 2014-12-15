$(document).ready(function()
{

	 $('.dd').nestable({group:1 })
	 		.on('change', updateOutput);
	
	
    var updateOutput = function(e)
    {
        var list   = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));// ,
																			// null,
																			// 2));
        } else {
            output.val('JSON browser support required for this demo.');
        }
    };

});