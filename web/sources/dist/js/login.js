/**
 * Created by Administrator on 2017/8/23.
 */
$(document).ready(function () {
    $("#username").blur(function () {
        console.log($("#username").val());
        if ($(this).val() == "") {
            $("#username + span").text("用户名不能为空");
        } else {
            $("#username + span").text("");

        }

    });

    $("#password").blur(function () {
        if ($(this).val() == "") {
            $("#password + span").text("密码不能为空");
        } else {
            $("#password + span").text("");

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
                if (data.valueOf() == "false") {
                    $("#code + span").text("验证码错误");
                    refesh();
                }
            });


        }

    });

    $("#code").focus(function () {
        $("#code + span").text("");
    });
    $(".step_btn button").click(function () {
        submit_check();
    });
});

function refesh() {
    var time = new Date();
    $("#ucode").attr("src", "/android/loginAction/loign/authcode?time=" + time);

}
var _status = false;
function submit_check() {

    if ($("#username").val() !== "" && $("#password").val() !== "" && $("#code").val() !== "") {
        if ($("#code+span").text() == "") {
            $("#login_info").submit();
        }

    }


}