jQuery(document).ready(function() {
    jQuery("body").queryLoader2({
        barColor: "#000",
        backgroundColor: "#fff",
        percentage: true,
        barHeight: 0,
        completeAnimation: "fade",
        minimumTime: 500,
    });

    /* nav div active */
    jQuery('.sf-menu').onePageNav({
        begin: function() {
        console.log('start')
        },
        end: function() {
        console.log('stop')
        }
    });

    /* Supperfish */
    jQuery('ul.sf-menu').superfish(
         {
            delay: 200,
            animation: {
                opacity: "show",
                height: "show"
            },
            speed: "fast",
            autoArrows: false,
            dropShadows: false
        });

    /* sticky menu  */
    jQuery(document).scroll(function () {
        var position = jQuery(document).scrollTop();
        var headerHeight = jQuery('#header').outerHeight();
        if (position >= headerHeight - 62){
                jQuery('.main_wrapper_inner .header_top_second').addClass('sticked');
        } else {
                jQuery('.main_wrapper_inner .header_top_second').removeClass('sticked');
        }

    });

    /*  Totop plugin   */
    jQuery().UItoTop({
        scrollSpeed: 500,
        easingType: 'easeOutQuart' 
    });

    /* smooth scrolling to div */
    $('a[href*=#]:not([href=#])').click(function(){
        if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') 
            || location.hostname == this.hostname) 
        {
          
          var target = $(this.hash),
          headerHeight = $(".header_top_inner1").height() + 40; // Get fixed header height
                
          target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
                  
          if (target.length) 
          {
            $('html,body').animate({
              scrollTop: target.offset().top - headerHeight
            }, 500);
            return false;
          }
        }
    });

});