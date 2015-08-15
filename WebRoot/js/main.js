/*初始化全局变量 start*/
var page_parameters = {};
/*初始化全局变量 end*/

//计算整体布局
function init_layout() {
    //取值
    var window_height = $(window).height();
    var window_width = $(window).width();
    var header_height = $(".header").outerHeight();
    var side_nav_width = $(".side-nav").outerWidth();

    //赋值
    $(".side-nav").height(window_height - header_height);
    $(".main-content").height(window_height - header_height);
    $(".main-content").width(window_width - side_nav_width);
}


//跳转页面
function load_page(page_id) {
    switch (page_id) {
        case "nav_home":
            $(".main-content").load("page/home.html");
            break;
        case "nav_user":
            $(".main-content").load("page/user.html");
            break;
        case "nav_goods_type":
            $(".main-content").load("page/goods_type.html");
            break;
        case "nav_goods":
            $(".main-content").load("page/goods.html");
            break;
        case "nav_order":
            $(".main-content").load("page/order.html");
            break;
        case "nav_activity":
            $(".main-content").load("page/activity.html");
            break;
        case "nav_problem":
            $(".main-content").load("page/problem.html");
            break;
        default:
            $(".main-content").load("page/home.html");
            break;
    }
}
	
$(function () {
    //计算整体布局
    init_layout();

    //载入管理首页
    load_page("nav_home");
   

    //菜单切换
    $(".side-nav").on("click", "a", function () {
        $(".side-nav li").each(function () {
            if ($(this).hasClass("uk-active")) {
                $(this).removeClass("uk-active");
            }
        });
        $(this).parent("li").addClass("uk-active");

        var page_id = $(this).attr("id");
        if (page_id != undefined) {
            load_page(page_id);
        }
    });
    

});

$(window).resize(function () {
    //计算整体布局
    init_layout();
});