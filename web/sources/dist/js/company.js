/**
 * Created by Administrator on 2017/9/14.
 */
$(document).ready(function () {


    $('#province, #city').citylist({
        data    : data,
        id      : 'id',
        children: 'cities',
        name    : 'name',
        metaTag : 'name'
    });

    $(".add-pic-pre").click(function () {
        $(".file").trigger("click");
    });
    $("#file").change(function () {
        var objUrl = getObjectURL(this.files[0]);
        // console.log(objUrl);
        if (objUrl) {
            $('.add-pic-pre').hide();
            $("#img").attr("src", objUrl);
            $(".img_show").show();
        }
    });
    $('.close').click(function () {
        $('.model').hide();
        // $("body").css("overflow","auto");

    });
    var top;
    $(window).scroll(function () {
        top = $(window).scrollTop();
        // console.log(top);
    });

    $(".model").css("height",$(document).height());
    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    $('.change>a').eq(0).click(function () {
        $(".file").trigger("click");
    });
//    验证是否输入的是否是汉字
    function checkname(name) {
        var strExp = new RegExp(/^[\u4E00-\u9FA5]+$/);
        if (name == "" || name.length < 2) {
            console.log("//" + name.length);
            return 2;
        } else if (strExp.test(name)) {
            return 1;
        } else {
            return 3;
        }
    }

    $(".ui-intxt").focus(function () {
        $(this).parent().siblings().hide();
    });
    $("#name").blur(function () {
        if (checkname($(this).val()) == 2) {
            // console.log($(this).parent().siblings());
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("公司名称不能为空");
        } else if (checkname($(this).val()) == 3) {
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("公司名称不是有效字符");
        }
    });
    //身份证号码验证
    var city = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    };
    var tips = '';
    var status = true;
    function isCardID(code) {
        if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
            tips = "身份证号格式错误";
            status = false;
        }else if(!city[code.substr(0,2)]){
            tips = "地址编码错误";
            status = false;
        }
        else {
            //18位身份证需要验证最后一位校验位
            if (code.length == 18) {
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                //校验位
                var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++) {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];

                if (parity[sum % 11] != code[17]) {
                    tips = "校验位错误";
                    status = false;
                }else {
                    return true;
                }
            }
        }
    }

    $('#sId').blur(function () {
        console.info("ss" + isCardID($("#sId").val()));
        status = isCardID($("#sId").val());
        if (!status) {
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text(tips);
        }else{
            // $(this).parent().siblings().hide();
        }
    });


//    手机号码验证
    function checkPhone(phone){
        var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        var isMob=/^((\+?86)|(\+86))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
        // if(isPhone.test(phone) || isMob.test(phone)){
            // alert("");
            // console.log("111");
            return false;
        }else {
            return true;
        }
    }
    $("#phone").blur(function () {
        console.log($(this).val());
        if(!checkPhone($(this).val())){
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("手机号码有误");
        }
    });
//    电子邮箱验证
    function CheckMail(mail) {
        var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (filter.test(mail)) return true;
        else {
            // alert('您的电子邮件格式不正确');
            return false;}
    }
    $("#email").blur(function () {
        if(!CheckMail($(this).val())){
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("邮箱地址输入有误");
        }
    });
    //
    // $(".re_send_email").click(function () {
    //     console.log("111");
    //     if(!CheckMail($(".email_input").val())){
    //         $('.email_error').show();
    //         $('.email_error').text("请输入正确邮箱");
    //     }else {
    //         console.log("222");
    //         // $.ajax()
    //     }
    // });


    $("#address").blur(function () {
        if($(this).val()==""){
            $(this).parent().siblings().show();
            $(this).parent().siblings().find("span").text("地址不能为空");
        }
    });
    var _status = true;
    $(".ui-ico-chk").click(function () {
        if(_status){
            _status = !_status;
            $(this).addClass("ui-ico-chked");
        }else {
            _status = !_status;
            $(this).removeClass("ui-ico-chked");
        }
    });


    var top;
    $(window).scroll(function () {
        top = $(window).scrollTop();
        // console.log(top);
    });
    $('.pro').click(function () {
        $('.model').show();
        $(".model").css("top",0);
        // $("body").css("overflow","hidden");
    });
    $(".agree").click(function () {
        _status = false;
        $(".ui-ico-chk").addClass("ui-ico-chked");
        $('.model').hide();
    });

    // $(".next").click(function () {
    //     if($('.tips').is(":hidden") && $("input").val() !="" && $(".ui-ico-chk").hasClass("ui-ico-chked") ){
    //         $('.next').removeAttr("disabled");
    //         $("form").submit();
    //     }else{
    //         $(".error").css("top",top+300);
    //         $('.error').fadeIn();
    //         $('.next').attr("disabled","disabled");
    //         setTimeout(function () {
    //             $('.error').fadeOut();
    //             $('.next').removeAttr("disabled");
    //         },1000);
    //     }
    // });

    $('.next').click(function () {
        var str = false;
        $('.ui-intxt').each(function (index,elem) {
            if(index>0){
                console.log($.trim($(elem).val())=="");
                if($.trim($(elem).val())==""){
                    str = true;
                }
            }
        });
        if( str || $.trim($('.tips').text())!=="" || !$(".ui-ico-chk").hasClass("ui-ico-chked")){
            // $('form').submit();
            // alert("请认真填写信息");
            $(".error").css("top",top+300);
            $('.error').fadeIn();
            setTimeout(function () {
                $('.error').fadeOut();
            },1000);
        }else {
            $('form').submit();
        }
    });
    //禁用回车键
    document.onkeydown = function(event) {
        var target, code, tag;
        if (!event) {
            event = window.event; //针对ie浏览器
            target = event.srcElement;
            code = event.keyCode;
            if (code == 13) {
                tag = target.tagName;
                if (tag == "TEXTAREA") { return true; }
                else { return false; }
            }
        }
        else {
            target = event.target; //针对遵循w3c标准的浏览器，如Firefox
            code = event.keyCode;
            if (code == 13) {
                tag = target.tagName;
                if (tag == "INPUT") { return false; }
                else { return true; }
            }
        }
    };

});