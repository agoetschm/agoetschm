(function ($) {
    $(function () {

        $('.button-collapse').sideNav();
        $('.parallax').parallax();
        $('.scrollspy').scrollSpy({scrollOffset: 0});
        
        $('#arrow_down_1').click(function () {
            $("html, body").animate({ scrollTop: $("#projects").offset().top  }, 2000);
        });

        $('#arrow_down_2').click(function () {
            $("html, body").animate({ scrollTop: $("#contact").offset().top  }, 2000);
        });

        Materialize.updateTextFields();
        
        // display contact messages
        if(displayCaptchaError) {
            Materialize.toast('Something went wrong with the captcha.', 6000);
        } 
        else if(displayContactOk) {
            Materialize.toast('Message sent successfully.', 6000);
        }


    }); // end of document ready
})(jQuery); // end of jQuery name space