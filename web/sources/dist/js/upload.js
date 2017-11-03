/**
 * Created by Administrator on 2017/9/15.
 */
$(function () {
    $('#upload_icon').click(function () {
        $("#upload_icon_file").trigger("click");
    });
    $(".upload_apk").click(function () {
        $("#file").trigger("click");
    });
    //应用小图标
    $('.replace_small_img').click(function () {
        $("#upload_icon_file").trigger("click");
    });
    //应用图标
    $('#icon_app').click(function () {
        $("#app_icon").trigger("click");
    });
    $(".replace_app_icon").click(function () {
        $("#app_icon").trigger("click");
    });

    //应用截图
    $('.img_screen').click(function () {
        $('#img_screen').trigger("click");
    });
    //最终协议点击model
    var top;
    $(window).scroll(function () {
        top = $(window).scrollTop();
    });

    $(".model").css("height", $(document).height());
    $('.close').click(function () {
        $('.model').hide();
    });
    $('.model').hide();
    $('.pro').click(function () {
        $('.model').show();
        $(".model").css("top", 0);
        // $("body").css("overflow","hidden")
    });
    $(".agree").click(function () {
        _status = false;
        $(".ui-ico-chk").addClass("ui-ico-chked");
        $('.model').hide();
    });
    //----------------------------------------
    $(document).off('change', '#file').on('change', '#file', function () {
        var str = $("#file").val().split('.');
        var length = str.length;
        if (str[length - 1] !== "apk") {
            tips_div("请选择apk文件上传", 2000);
            $(this).val() == "";
            $('.upload_img').hide();
            $('.upload_apk').css("background-color", "none");

        } else {

            var url = "/SoftwareAction/user/uploadTempSoftware";
            var file = document.getElementById('file').files[0];
            console.log(document.getElementById('file').files);
            var fd = new FormData();
            fd.append("file", file);
            $.ajax({
                url: url,
                type: "POST",
                data: fd,
                dataType: 'json',
                cache: false,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                xhr: function () {
                    var xhr = $.ajaxSettings.xhr();
                    if (onprogress1 && xhr.upload) {
                        xhr.upload.addEventListener("progress", onprogress1, false);
                        return xhr;
                    }
                },

                success: function (data) {
                    if (data.code == 200) {
                        $('.upload_apk').css("background-color", "rgba(0,0,0,0.2)").text("上传成功!");
                        checkTrue($(this), true, "");
                        $('.data').text( "上传成功！").css('color','blue');
                    } else {
                        $('.upload_apk').css("background-color", "rgba(0,0,0,0.2)").text("上传失败！!");
                        //将错误的信息提示到界面中
                        $('.data').text( "上传失败！").css('color','red');
                        tips_div(data.msg, 3000);
                    }
                },
                error: function (e) {
                    alert(e);
                    $('.upload_apk').css("background-color", "rgba(0,0,0,0.2)").text("上传失败！");
                }
            });

            function onprogress1(evt) {

                //附件总大小

                if (evt.lengthComputable) {

                    $('.data').text(Math.round(evt.loaded / evt.total * 100) + "%" + "上传中。。。").css('color','black');


                }
            }
        }

    });
//-----------------------------------------------------------------------------------------
    var _status = true;
    $(".ui-ico-chk").click(function () {
        if (_status) {
            _status = !_status;
            $(this).addClass("ui-ico-chked");
        } else {
            _status = !_status;
            $(this).removeClass("ui-ico-chked");
        }
    });


    $('.ui-radio').click(function () {
        if (!$(this).hasClass("ui-radio-checked")) {
            $(this).parent().siblings().children().removeClass("ui-radio-checked");
            $(this).addClass("ui-radio-checked");
        } else {
        }
    });
//    textarea 字数限制
    $('.ui-textarea').keyup(function () {
        $('.actual_len').text(strlen($(".ui-textarea").val()) / 2);
    });

    $(".ui-textarea").blur(function () {
        if (strlen($(this).val()) / 2 < 60) {
            checkTrue($(this), false, "字数不能小于60字");
        } else {
            checkTrue($(this), true, "");
        }
    });
    //ui-radio-hover
    // $('')
    //单选框
    $(".ui-radio").hover(function () {
        $(this).addClass("ui-radio-hover");
    }, function () {
        $(this).removeClass("ui-radio-hover");
    });

    $("#name").blur(function () {
        // console.log($(this).val().length);
        console.log();
        if (strlen($(this).val()) > 40) {
            checkTrue($(this), false, "该项不能超过二十个汉字");
        } else if (strlen($(this).val()) == 0) {
            checkTrue($(this), false, "该项不能为空！");
        } else {
            checkTrue($(this), true, "");
        }
    });
    $('#name').focus(function () {
        $(".info_tips").hide();
    });


    //一句话简介验证
    $('#intro').blur(function () {
        console.log(strlen($(this).val()) / 2);
        if (strlen($(this).val()) / 2 < 5) {
            checkTrue($(this), false, "字数不能少于五个");
        } else if (strlen($(this).val()) / 2 > 15) {
            checkTrue($(this), false, "字数不能大于十五个");
        } else {
            checkTrue($(this), true, "");
        }
    });


    //
    function strlen(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            var c = str.charCodeAt(i);
            //单字节加1
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                len++;
            }
            else {
                len += 2;
            }
        }
        return len;
    }

//    弹框msg自定义
    var _top;
    _top = $(window).scrollTop();
    $(window).scroll(function () {
        _top = $(window).scrollTop();
    });

    function tips_div(str, time) {
        $(".tips_msg").css("top", _top + 350).text(str).fadeIn();
        setTimeout(function () {
            $(".tips_msg").fadeOut();
        }, time);
    }

    function checkTrue(icon_msg, status, str) {
        if (!status) {
            icon_msg.parent().siblings().css("display", "inline-block");
            icon_msg.parent().siblings().find("span").text(str);
            icon_msg.parent().siblings().find("b").css("background-position", " -297px -191px");
        } else {
            icon_msg.parent().siblings().css("display", "inline-block");
            icon_msg.parent().siblings().find("span").text("");
            icon_msg.parent().siblings().find("b").css("background-position", "-297px -165px");
        }
    }

    //图标上传以及显示
    $("#upload_icon_file").change(function () {
        var objUrl = getObjectURL(this.files[0]);
        obtain_size("upload_icon_file", $('#upload_icon'), $('#img_small'), $('.img_show_small'), 300, objUrl)
        // console.log(objUrl);
    });
    $("#app_icon").change(function () {
        var objUrl = getObjectURL(this.files[0]);
        obtain_size("app_icon", $('#icon_app'), $('#img'), $('.icon_app'), 300, objUrl)
    });

    //获取上传图片文件的大小 input_id
    function obtain_size(file_path, original_view, img_view, img_div, size, objUrl) {
        console.log(file_path);
        var MyTest = document.getElementById(file_path).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(MyTest);
        reader.onload = function (theFile) {
            var image = new Image();
            image.src = theFile.target.result;
            image.onload = function () {
                // console.log("图片的宽度为"+this.width+",长度为"+this.height);
                if (this.width == size && this.height == size) {
                    if (objUrl) {
                        original_view.hide();
                        img_view.attr("src", objUrl);
                        img_div.show();
                    }
                } else {
                    tips_div("请选择合适的尺寸上传", 2000);
                }
            };
        };
    }

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

    //上传图像截图

    bind_change_img();

    function bind_change_img() {
        var input = document.getElementById("img_screen");
        var result, div;
        if (typeof FileReader === 'undefined') {
            result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
            input.setAttribute('disabled', 'disabled');
        } else {
            input.addEventListener('change', readFile, true);
        }
    }


    var img_index = 0;
    var init_index = 0;
    var file;
    var pre_img_info = [];
    var all_data_time = new Array();
    var num = 0;

    function readFile() {
        // var img_info = document.getElementById("img_screen").files;
        var img_info = document.getElementById("img_screen");
        var input = document.getElementById("img_screen");
        var result, div;
        for (var j = 0; j < document.getElementById("img_screen").files.length; j++) {
            all_data_time.push(img_info.files[j]);
        }
        console.log("mmmmmmmmmmmmmm");
        img_index = img_index + this.files.length;
        if (img_index > 5) {
            tips_div("截图不能超过五张", 2000);
            if (this.files.length > 5 && init_index == 0) {
                img_index = 0;
            } else {
                init_index = 1;
                img_index = 5;
            }
        } else if (img_index < 2) {
            tips_div("截图不能少于两张", 2000);
            img_index = 2;
        } else {
            $('.img_click_upload').css('display', 'inline-block');
            for (var i = 0; i < this.files.length; i++) {
                if (!input['value'].match(/.jpg|.gif|.png|.bmp/i)) {　　//判断上传文件格式
                    return tips_div('上传的图片格式不正确，请重新选择', 2000);
                }
                var reader = new FileReader();
                reader.readAsDataURL(this.files[i]);
                reader.onload = function (e) {

                    result = '<div class="img_screen_div" data-num = "' + (num++) + '"><img src="' + this.result + '" alt="" class="img" /> ' +
                        '<a href="javascript:;" title="删除" class="delete_screen">X</a>' +
                        // '<span class="progress">等待中。。。。</span>' +
                        // '<progress id="progressBar" value="0" max="100"></progress><span id="progress"></span>'+
                        '<progress  class="progressBar" value="5" max="100" style="width:140px"></progress>' +
                        '<span class="percentage">等待上传。。。</span>' +
                        '</div>';
                    // console.log("图片。。。。"+this.result);
                    $("#img_show").css("display", "inline");
                    $('#img_show').append($(result));
                    $('.img_screen').hide();

                    $(".delete_screen").click(function () {
                        // console.log("index"+);
                        if ($(this).parent().index() > 0) {
                            --img_index;
                            if (img_index < 2) {
                                img_index = 2;
                                tips_div("截图不能少于两张", 2000);
                            } else {
                                //循环改变后面的data-num的值
                                for (var n = 0; n < $(this).parent().nextAll().length; n++) {
                                    var new_num = $(this).parent().nextAll().eq(n).attr("data-num");
                                    $(this).parent().nextAll().eq(n).attr("data-num", new_num - 1);
                                }
                                num = $('#img_show>div').length - 1;
                                $(this).parent().remove();
                                // console.log);
                                //删除数组处理
                                var m = parseInt($(this).parent().attr("data-num"));
                                all_data_time.splice(m, 1);

                            }
                        } else {
                            return false;
                        }
                    });
                };
            }
        }
    }

    $(".change_screen").click(function () {
        // input.value = "";
        $("#img_screen").trigger("click");
    });


    //点击上传截图按钮
    $('.img_click_upload').click(function () {
        var url ="/SoftwareAction/user/uploadTempSoftware";
        //点击上传清空图片名字数组
        pre_img_info = [];
        loop=0;
        //重置进度条
        $('.progressBar').attr('value','5');
        $('.progressBar').attr('max','100');
        $('.percentage').text('等待上传。。。');
        //点击上传的时候把 新的文件给数组中
        // var callbacks = $.Callbacks();
        for (var k = 0; k < all_data_time.length; k++) {
            var fd = new FormData();
            fd.append("file", all_data_time[k]);
            sendImg(fd, url, k);
        }
        $('#img_screen').replaceWith('<input type="file" class="file" hidden id="img_screen" accept=".jpg,.png,.jpeg,.gif" multiple name="file">');
        bind_change_img();
    });
    var loaded=[];
    var tot=[];
    var loop = 0;
    function sendImg(fd, url, k) {
        $.ajax({
            url: url,
            type: "POST",
            data: fd,
            cache: false,
            processData: false,  // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            xhr: function () {
                var xhr = $.ajaxSettings.xhr();
                if (onprogress && xhr.upload) {
                    xhr.upload.addEventListener("progress", onprogress.bind(k), false);
                    return xhr;
                }
            },

            success: function (data) {
                pre_img_info.push(data.msg);
                var b = pre_img_info.join(",");
                $('.img_data>input').val(b);
                var progressBar = $('.progressBar').eq(loop);
                var percentageDiv = $('.percentage').eq(loop);
                sleep(500);
                progressBar.attr("max", tot[loop]);
                progressBar.attr("value", loaded[loop]);
                // console.log(progressBar.parent().index());
                percentageDiv.text(Math.round(loaded[loop] / tot[loop] * 100) + "%" + "上传成功！");
                loop= loop+1;
            },
            error: function (e) {
                console.log("失败"+e);
            }
        });
        function onprogress(evt) {
            //附件总大小
            if (evt.lengthComputable) {
                loaded[k] = evt.loaded;     //已经上传大小情况
                tot[k] = evt.total;
            }
        }
    }


    function sleep(n) { //n表示的毫秒数
        var start = new Date().getTime();
        while (true) if (new Date().getTime() - start > n) break;
    }

    //单选获取值
    $('.ui-radio').click(function () {
        $(this).parent().siblings('input').val($.trim($(this).parent().text()));
        var type_str = $.trim($(this).parent().siblings('input').val());
        if (type_str == "游戏") {
            $(".type_app").val('1');
        } else {
            $(".type_app").val('0');
        }
    });
    //点击游戏，软件做请求

    $(".game .ui-radio").click(function () {

        var url = "/SoftwareAction/user/userMenu";
        var temp = $(".type_app").val();
        $.post(url, {param: temp}, function (data) {
            $("#ui-select").empty();
            //添加select的值
            $("#ui-select").append("<option style='color: #909090;display: none' selected disabled value=" + 0 + ">请选择子分类</option>");
            $.each(data, function (index, obj) {
                $("#ui-select").append("<option  value=" + obj[0] + ">" + obj[1] + "</option>");

            });
            option_click();//火狐下拉框点击事件
            option_change();//谷歌下拉框点击事件
        });


    });
    //option选择
    var _index = 0;
    $('.app_tags').hide();

    function option_click() {
        $('#ui-select>option').click(function () {
            var option_value = $(this).val();
            var type_value = $('.type_app').val();
            var url = "/SoftwareAction/user/userTwoMenu";

            $.post(url, {fid: option_value, type: type_value}, function (data) {
                console.log(data);
                if (data.length !== 0) {
                    $('.app_tags').show();
                    $(".tags").empty();
                    $.each(data, function (index, obj) {
                        console.log(data);
                        if ((index + 1) % 5 !== 0) {
                            $(".tags").append('<span class="ui-tag " data-value="' + obj[0] + '">' + obj[1] + '<i class="ui-tag-ico"></i> </span>');
                        } else {
                            $(".tags").append('<span class="ui-tag " data-value="' + obj[0] + '">' + obj[1] + '<i class="ui-tag-ico"></i> </span><br/>');
                        }
                    });
                    span_click();
                } else {
                    $('.app_tags').hide();
                }

            });
        });
    }

    //提交审核button监听
    $('.next').click(function () {
        if ($("input").val() !== "" && $('textarea').val() !== "" && $('span.tips').text() == "" && $('.ui-ico-chk').hasClass('ui-ico-chked')) {
            $(".form_data").submit();
            console.log('提交');
        } else {
            tips_div("请认真填写信息", 2000);

        }
    });

    function option_change() {
        $('#ui-select').change(function () {
            var option_value = $(this).val();
            var type_value = $('.type_app').val();
            var url = "/SoftwareAction/user/userTwoMenu";

            $.post(url, {fid: option_value, type: type_value}, function (data) {
                if (data.length !== 0) {
                    $('.app_tags').show();
                    $(".tags").empty();
                    $.each(data, function (index, obj) {
                        // console.log(index);
                        if ((index + 1) % 5 !== 0) {
                            $(".tags").append('<span class="ui-tag " data-value="' + obj[0] + '">' + obj[1] + '<i class="ui-tag-ico"></i> </span>');
                        } else {
                            $(".tags").append('<span class="ui-tag " data-value="' + obj[0] + '">' + obj[1] + '<i class="ui-tag-ico"></i> </span><br/>');
                        }
                    });
                    span_click();
                } else {
                    $('.app_tags').hide();
                }

            });
        });
    }

    span_click();

    function span_click() {
        $(".ui-tag").click(function () {
            var k = 0;
            if (_index == 3 && !$(this).hasClass("checked")) {
                // $(".ui-tag").unbind();
                console.log("只能选择三个标签");
                checkTrue($(this), false, "只能选择三个标签");
            } else {
                checkTrue($(this), true, "");
                if ($(this).hasClass("checked")) {
                    // $(".ui-tag").bind('click');
                    $(this).removeClass('checked');
                    var text = $.trim($(this).text());
                    $(this).parent().find('input').each(function (index, elem) {
                        if ($(elem).val() == text) {

                            $(elem).remove();
                        } else {
                            console.log("111");
                        }
                    });
                } else {
                    $(this).addClass('checked');
                    $(this).parent().append(' <input type="hidden" value="' + $.trim($(this).attr('data-value')) + '" name="menu">');
                }
            }
            $(".tags>span").each(function (index, elem) {
                if ($(elem).hasClass("checked")) {
                    k++;
                    _index = k;
                }

            });
        });
    }

    $('#provider').blur(function () {
        if ($(this).val() == "") {
            checkTrue($("#provider"), false, "提供方不能为空");
        } else {
            checkTrue($("#provider"), true, "");
            // submit_data(url, "");
        }
    });
    // $('#provider').focus(function () {
    //
    // });
    function submit_data(url, data) {
        $.post(url, {}, function (data) {
            if (data.valueOf() == 'true') {
                checkTrue($("#provider"), true, "");
            } else {
                checkTrue($("#provider"), false, "提供方名称不一致");
            }
        });
    }

});