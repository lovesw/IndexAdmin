/**
 * Created by Administrator on 2017/9/25.
 */
$(function () {
    $(".message_left li").click(function () {
        $(".message_left li").removeClass("checked");
        $(this).addClass("checked");
        $('.info').text($(this).find("a").text());
        $('.rightInfo>div').hide();
        $('.rightInfo>div').eq($(this).index()).show();
        var url = $('#url').text() + "indexUserAction/user/lookDeveloper";
        if ($(this).index() == 1) {
            $.get(url, function (data) {
                if (data[0][2] == 1) {
                    $('.status').text("通过").css("color", "green");
                } else {
                    $('.status').text("未通过").css("color", "red");
                }

                $('.name').text(data[0][0]);
                $('.type_num').text(data[0][1]);
                $('.phone').text(data[0][6]);
                $('.email').text(data[0][3]);
                $('.address').text(data[0][4]);
                // console.log(data);
            });
        }
    });
    if ($.trim($('.phone').text()) == "") {
        $('.phone').append('<a href="javascript:;"> 绑定手机号</a>');
        // $(this).append('<a href="javascript:;"> 绑定手机号</a>');
    }
    $('.re_pwd').blur(function () {
        if ($(this).val() !== $('.new_pwd').val()) {
            console.log($(this).next("div"));
            $(this).next("div").text("密码不一致").show();
        }
    });
    $('.change_pwd input').focus(function () {
        $(this).next("div").hide();
    });
    $('.change_pwd input').blur(function () {
        if ($(this).val() == "") {
            console.log($(this));
            $(this).next("div").text("不能为空！").show();
        }
    });

    $('.changeEmail').click(function () {
        $('.model').show();
    });
    $('.close_email').click(function () {
        $('.model').hide();
    });
    //更换邮箱按钮点击事件
    var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    $(".model_div input").focus(function () {
        $('.email_error').hide();
    });
    $('.change_email').click(function () {
        var email_value = $.trim($(".model_div input").val());
        if ($.trim($(".model_div input").val()) == "") {
            console.log($.trim($(".model_div input").val()));
            $('.email_error').text("邮箱不能为空！").css("display", "inline-block");
        } else if (!isEmail.test(email_value)) {
            $('.email_error').text("邮箱格式不正确！").css("display", "inline-block");
        } else {
            var url = $("#url").text() + "indexUserAction/user/changeEmail";
            $.post(url, {email: email_value, id: $(".id").text()}, function (data) {
                if (data.valueOf() == "true") {
                    layer.msg("邮箱更换成功，请到相应的邮箱进行激活");
                    $('.model').hide();
                } else {
                    layer.msg("邮箱更换失败，邮箱可能已经被占用或邮箱格式不对");
                }
            });
        }
    });
    //更换密码点击事件
    $('.submit').click(function () {
        if ($('.change_pwd input') !== "" && !$('.tips_error').is(":visible")) {
            var oldPassword = $('.old_pwd').val();
            var newPassword = $('.new_pwd').val();
            var password = $('.re_pwd').val();
            var url = $("#url").text() + "indexUserAction/user/changePassword";
            $.post(url, {
                oldPassword: oldPassword,
                newPassword: newPassword,
                password: password
            }, function (data) {
                if (data.valueOf() == "true") {
                    layer.msg("密码修改成功,自动跳转到登录界面", {icon: 1, time: 2000});
                    setTimeout(function () {
                        location.href = $("#url").text() + "android/loginAction/login/input";
                    }, 2000);

                } else {
                    $('.submit_error').text(data).show();
                }
            });
        }
    });
});