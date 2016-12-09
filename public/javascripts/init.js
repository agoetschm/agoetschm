(function ($) {
    $(function () {

        $('.button-collapse').sideNav();
        $('.parallax').parallax();
        $('.scrollspy').scrollSpy({scrollOffset: 0});
        
        $('#arrow_down_1').click(function () {
            $("html, body").animate({ scrollTop: $("#about").offset().top  }, 2000);
        });

        $('#arrow_down_2').click(function () {
            $("html, body").animate({ scrollTop: $("#contact").offset().top  }, 2000);
        });


        Materialize.updateTextFields();

        // var options = [{
        //     selector: '#about', offset: 1, callback: function () {
        //         console.log("test");
        //         $('nav').fadeTo('slow', 1);
        //     }
        // }];
        // Materialize.scrollFire(options);


    }); // end of document ready
})(jQuery); // end of jQuery name space