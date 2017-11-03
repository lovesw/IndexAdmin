/**
 * Created by Administrator on 2017/9/20.
 */
$(function () {
    // $()

    var _index = 1;
    $('.total_num').text($('.unable tbody tr').length);
    $('.total_num2').text($('.unable tbody tr').length);
    $('.total_num1').text($('.able tbody tr').length);
    $('.classification_left a').click(function () {
        $('.classification_left a').removeClass('checked');
        $(this).addClass('checked');
        _index = $(this).index();
        // console.log(_index);
        // console.log($('.total_num').text($('.unable tbody tr').length));
        if(_index == 1){
            $('.total_num').text($('.unable tbody tr').length);
        }else {
            $('.total_num').text($('.able tbody tr').length);
        }
        if ($(this).index()) {
            table_status($(this));
        } else {
            table_status($(this));
        }
    });



    $('.search_ipt').keyup(function () {
        var value = $.trim($(this).val());
        if (value === '') {
            $('table').eq(_index).find('tr').show();
        } else {
            $('table ').eq(_index).find('tr:gt(0)').hide().find(".id,.name").filter(":contains('" + value + "')").parent("tr").show();
        }
    });
    $('.downofflie span').each(function (index,elem) {
       if($.trim($(elem).text())=="已上线"){
           $(this).css("color","green");
       } else {
           $(this).css("color","red");
           $('.downofflie1').eq(index).removeAttr("onclick")
       }
    });

    function table_status(this_table) {
        $('.table_info_none>table').hide();
        $('.table_info_none>table').eq(this_table.index()).show();
    }
});


function deleteApk(obj, id, md5) {
    layer.confirm('确认要删除吗？', function (index) {
        url = $("#url").text() + "SoftwareAction/user/deleteSoftware";
        $.post(url, {id: id, md5: md5}, function (data) {
            if (data.valueOf() == "true") {
                //删除当前行
                $(obj).parents("tr").remove();
                layer.msg("删除成功", {icon: 1, time: 1000});
            } else {
                layer.msg("删除失败,请刷新后重试", {icon: 4, time: 1000});
            }
        });
    });
}

function downOffline(obj, id) {

    layer.confirm('确认要下线该软件吗？', function (index) {
        url = $("#url").text() + "SoftwareAction/user/downOffline";
        $.post(url, {id: id}, function (data) {
            if (data.valueOf() == "true") {
                // console.log($(obj));
                $(obj).removeAttr("onclick");
                $(obj).parent().parent().siblings().eq(2).find("span").text("下线审核中").css("color","red");
                layer.msg("申请成功", {icon: 1, time: 1000});
            } else {
                layer.msg("提交失败,请刷新后重试", {icon: 4, time: 1000});
            }
        });

    });

}