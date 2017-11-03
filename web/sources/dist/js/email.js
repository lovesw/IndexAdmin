$(function () {
    $(".email_input").focus(function () {
        $('.email_error').hide();
    });
    $('.model').hide();
    $(".change_email_text").click(function () {
        $(".model").show();
    });
    $(".close").click(function () {
        $('.model').hide();
    });
    $("#email").blur(function () {
        if(!CheckMail($(this).val())){
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("邮箱地址输入有误");
        }
    });
    function CheckMail(mail) {
        var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (filter.test(mail)) return true;
        else {
            // alert('您的电子邮件格式不正确');
            return false;}
    }
    var top;
    $(window).scroll(function () {
        top = $(window).scrollTop();
        // console.log(top);
    });

    $(".model").css("height",$(window).height());
    $(".change_email_body").css("top",top+350);
});