
$(document).ready(function () {

    /* popup close */
     $('#close').click(function(){
        $('#fade').css('display','none');
    });


      /* tab02 */
    $(".tab02_container .tab_cont").hide();
    $(".tab_navi ul.tab02 li:first a").addClass("active").show();
    $(".tab02_container .tab_cont:first").show();
    $(".tab_navi ul.tab02 li a").click(function() {
        $(".tab_navi ul.tab02 li a").removeClass("active");
        $(this).addClass("active");
        $(".tab02_container .tab_cont").hide();
        var activeTab = $(this).attr("href");
        $(activeTab).fadeIn();
        return false;
    });
});

$(document).on('change', '.selectbox select', function() {
    var select_name = $(this).children('option:selected').text();
    $(this).siblings('label').text(select_name);
});




/* Side menu */
// ( function( $ ) {
// $( document ).ready(function() {
// $('#sidemenu li.has-sub>a').on('click', function(){
// 		$(this).removeAttr('href');
// 		var element = $(this).parent('li');
// 		if (element.hasClass('open')) {
// 			element.removeClass('open');
// 			element.find('li').removeClass('open');
// 			element.find('ul').slideUp();
// 		}
// 		else {
// 			element.addClass('open');
// 			element.children('ul').slideDown();
// 			element.siblings('li').children('ul').slideUp();
// 			element.siblings('li').removeClass('open');
// 			element.siblings('li').find('li').removeClass('open');
// 			element.siblings('li').find('ul').slideUp();
// 		}
// 	});

// 	$('#sidemenu>ul>li.has-sub>a').append('<span class="holder"></span>');


// 	function rgbToHsl(r, g, b) {
// 	    r /= 255, g /= 255, b /= 255;
// 	    var max = Math.max(r, g, b), min = Math.min(r, g, b);
// 	    var h, s, l = (max + min) / 2;

// 	    if(max == min){
// 	        h = s = 0;
// 	    }
// 	    else {
// 	        var d = max - min;
// 	        s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
// 	        switch(max){
// 	            case r: h = (g - b) / d + (g < b ? 6 : 0); break;
// 	            case g: h = (b - r) / d + 2; break;
// 	            case b: h = (r - g) / d + 4; break;
// 	        }
// 	        h /= 6;
// 	    }
// 	    return l;
// 	}
// });
// } )( jQuery );