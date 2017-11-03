/**
 * Created by Administrator on 2017/8/31.
 */
$(document).ready(function () {
    var height = $(window).outerHeight();
    var width = $(window).outerWidth();
    if(height<720 && width/height>1){
        $("#small_resolution").attr("href","sources/dist/css/index_small.css");
        $(".content>section").css("height", height);
    }else if(width/height>1){
        $(".content>section").css("height", height);
    }else {
        $(".content>section").css("height", 950);
    }
    $('body').bind('touchmove',function (e) {
        k = Math.round($(window).scrollTop() / height)+1;
        console.log(k);
    });
//轮播
    var pic_arr = ["sources/dist/image/index/car_01.png", "sources/dist/image/index/car_02.png", "sources/dist/image/index/car_03.png"];
    var class_arr = ["left_img", "scale_img", "right_img"];

//        var _count = Math.floor(pic_arr.length/2);  中间值
    source();
    $(".pic_car li").click(function () {
        var index = $(this).index();
        if ($(this)[0].className == 'left_img') {
            arr_check(0, true);
        } else if ($(this)[0].className == 'right_img') {
            arr_check(class_arr.length - 1, false);
        }
    });
    $(".left").click(function () {
        arr_check(0, true);
    });
    $(".right").click(function () {
        arr_check(class_arr.length - 1, false);
    });

    function source() {
        for (var i = 0; i < pic_arr.length; i++) {
            $(".pic_car ul").append('<li class=""><img src="" alt=""></li>');
            $(".pic_car li").eq(i).find("img").attr("src", pic_arr[i]);
            $(".pic_car li").eq(i).attr("class", class_arr[i]);
        }
    }

    function arr_check(index, status) {
        var str = class_arr.splice(index, 1);
        if (status) {
            class_arr.push(str[0]);
        } else {
            class_arr.unshift(str[0]);
        }
        for (var i = 0; i < class_arr.length; i++) {
            $(".pic_car li").eq(i).attr("class", class_arr[i]);
        }
    }

    var k = 0;
    if ($(window).scrollTop() > 0) {
        k = Math.round($(window).scrollTop() / height);
    }

//    页面翻页特效
    $('.down img').click(function () {
        // $('html body').animate({scrollTop: $(".content>section").eq(1).offset().top}, 200);
        dosomething(1);
        k = k + 1;
    });
    var timeoutflag = null;
    var scrollFunc = function (e) {
        e = e || window.event;
        if (e.wheelDelta) {
            if (e.wheelDelta < 0) {//向下
                if (timeoutflag != null) {
                    clearTimeout(timeoutflag);
                }
                timeoutflag = setTimeout(function () {
                    if (k >= 3) {
                        k = 3;
                    } else {
                        k = k + 1;
                    }
                    dosomething(k);
                    // changeAnimate();
                }, 150);
            }
            if (e.wheelDelta > 0) {//向上
                if (timeoutflag != null) {
                    clearTimeout(timeoutflag);
                }
                timeoutflag = setTimeout(function () {
                    if (k <= 0) {
                        k = 0;
                    } else {
                        k = k - 1;
                    }
                    dosomething(k);
                }, 150);
            }
        } else if (e.detail) {
            if (e.detail > 0) {
                if (timeoutflag != null) {
                    clearTimeout(timeoutflag);
                }
                timeoutflag = setTimeout(function () {
                    if (k >= 3) {
                        k = 3;
                    } else {
                        k = k + 1;
                    }
                    dosomething(k);
                }, 150);
            }
            if (e.detail < 0) {
                if (timeoutflag != null) {
                    clearTimeout(timeoutflag);
                }
                timeoutflag = setTimeout(function () {
                    if (k <= 0) {
                        k = 0;
                    } else {
                        k = k - 1;
                    }
                    dosomething(k);
                }, 150);
            }
        }
    };

    function dosomething(k) {
        $('.nav_index li').css("width", "1px").css("background-color", "#9d9d9d").css("margin-left", '0px');
        $('.nav_index li').eq(k + 1).css("width", "3px").css("background-color", "#ea1e63").css("margin-left", '-1px');
        $('html,body').animate({scrollTop: $(".content>section").eq(k).offset().top}, 200, "linear");
        // console.log($(".content>section").eq(k).offsetTop);
        // $('html body').animate({scrollTop: $(".content>section").eq(k).offsetTop}, 200, "linear");
        changeAnimate(k);
    }

    // $('html body').animate({scrollTop:$(".content>section").eq(0).offset().top},200,"linear");
    //给页面绑定滑轮滚动事件
    if (document.addEventListener) {
        document.addEventListener('DOMMouseScroll', scrollFunc, false);
    }
    //滚动滑轮触发scrollFunc方法
    window.onmousewheel = document.onmousewheel = scrollFunc;
//回到顶部功能
    topShow();
    $(window).scroll(function () {
        topShow();
    });
    $(".top").click(function () {
        k = 0;
        dosomething(k);
    });
    function topShow() {
        if ($(window).scrollTop() < 600) {
            $('.top').fadeOut();
        } else {
            $(".top").fadeIn();
        }
        $('.nav_index li').eq(k + 1).css("width", "3px").css("background-color", "#ea1e63").css("margin-left", '-1px');
        changeAnimate(k);
    }

//    动画效果
//     console.log($('.third .first_content').data("animation"));
    function changeAnimate(k) {
        var strClass = $(".content>section").eq(k).data("animation");
        // console.log(strClass);
        $(".content>section").eq(k).addClass(strClass);
    }
    $('body').css('width',width);
});