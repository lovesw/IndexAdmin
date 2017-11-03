/**
 * Created by Administrator on 2017/8/23.
 */
$(document).ready(function () {
    $(".step>div").eq(0).find(".circle_bg").css("background-color", "#1d8aa4");
    $(".step>div").eq(0).find(".line").css("background-color", "#1d8aa4");
    var uPattern = /^[a-zA-Z0-9_-]{5,16}$/;
    //输出 true
    var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    //输出 true
    $("#username").blur(function () {
        console.log($("#username").val());
        if ($(this).val() == "") {
            $("#username + span").text("用户名不能为空");
        } else if (!uPattern.test($("#username").val())) {
            $("#username + span").text("请输入正确字符");
        } else {
            var url = "/android/registAction/checkEmail";
            checkStatus(url, "account", $(this).val(), $("#username + span"), "用户名已经被占用");


        }
    });

    $("#password").blur(function () {
        if ($(this).val() == "") {
            $("#password + span").text("密码不能为空");
        } else if ($(this).val().length < 6) {
            $("#password + span").text("密码不能少于6位");
        } else {
            $("#password + span").text("");

        }
    });
    $("#password").keyup(function () {
        var num = checkStrong($(this).val());
        switch (num) {
            case 0:
                break;
            case 1:
                $(".pwd_status>span").css("background-color", "#b1b1b1").text("").eq(num - 1).css("background-color", "#18a9e1").text("弱");
                break;
            case 2:
                $(".pwd_status>span").css("background-color", "#b1b1b1").text("").eq(num - 1).css("background-color", "#e1dd38").text("中");
                break;
            case 3:
                $(".pwd_status>span").css("background-color", "#b1b1b1").text("").eq(num - 1).css("background-color", "#28e144").text("强");
                break;
            case 4:
                $(".pwd_status>span").css("background-color", "#b1b1b1").text("").eq(num - 1).css("background-color", "#e1154b").text("最强");
                break;
            default:
                break;
        }
    });

    $('#password').focus(function () {
        $('.pwd_status').css('display','inline-block');
    });

    function checkStrong(val) {
        var modes = 0;
        if (val.length < 6) return 0;
        if (/\d/.test(val)) modes++; //数字
        if (/[a-z]/.test(val)) modes++; //小写
        if (/[A-Z]/.test(val)) modes++; //大写
        if (/\W/.test(val)) modes++; //特殊字符
        if (val.length > 12) return 4;
        return modes;
    }

    $("#password_again").blur(function () {
        if ($(this).val() != $("#password").val()) {
            $("#password_again + span").text("密码不一致");
        } else {
            $("#password_again + span").text("");
        }
    });
    $("#email").blur(function () {
        if ($(this).val() == "") {
            $("#email + span").text("邮箱不能为空");
        } else if (!ePattern.test($(this).val())) {
            $("#email + span").text("请输入正确的邮箱格式");
        } else {
            var url = "/android/registAction/checkEmail";
            checkStatus(url, "account", $(this).val(), $("#email + span"), "邮箱已经被占用")

        }
    });
    // $(".step_btn button").attr({"disabled":"disabled"});
    $('.step_btn button').click(function () {
        var str = false;
        $('.ipt input').each(function (index,elem) {
            if(index>0){
                console.log($.trim($(elem).val())=="");
                if($.trim($(elem).val())==""){
                    str = true;
                }
            }
        });
        if( str || $.trim($('.ipt input').siblings('span').text())!==""){
            // $('form').submit();
            alert("请认真填写信息");
        }else {
            $('form').submit();
        }
    });


    $("#code").blur(function () {
        var _data = true;

        if ($(this).val() == "") {
            $("#code + span").text("请填写验证码");
        } else {
            $("#code + span").text("");
            var url = "/android/loginAction/login/authCodeCheck?ucode=" + $(this).val();

            $.get(url, function (data) {
                    if (data.valueOf()=="false") {
                        $("#code + span").text("验证码错误");
                        var time = new Date();
                        $("#ucode").attr("src", "/android/loginAction/loign/authcode?time=" + time);
                    }

                    $("#code + span").text("验证码错误");

            });


        }

    });



    $(".pre_btn").click(function () {
        var url = $("#url").text();
        location.href = url + "/android/registAction/inputRegiset";
    });

    /**
     * 重发邮件
     */
    $(".send").click(function () {
        var url = $("#url").text() + "/android/registAction/reSendEmail";
        $.post(url,
            {
                id: $("#id").text(),
                email: $("#email").text()
            }, function (data) {
                if (data.valueOf() == "true") {
                    $(".send").attr({"disabled": "disabled"});
                    $(".send").text("发送成功");
                } else {
                    $(".send").text("邮箱发送失败,邮箱可能已激活");
                }
            }
        );

    });
});

function checkStatus(url, keys, value, tips, str) {
    url = url + "?" + keys + "=" + value;
    $.get(url, function (data) {
        if (data.valueOf() == "true") {
            tips.text("");
        } else {
            tips.text(str);
        }

    });

}

function refesh() {
    var time = new Date();
    $("#ucode").attr("src", $("#url").text() + "android/loginAction/loign/authcode?time=" + time);

}