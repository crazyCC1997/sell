<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏sidebar -->
    <#include "../common/nav.ftl">
    <#-- 主要内容content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderMasterDTOPage.content as orderMaster>
                            <tr>
                                <td>${orderMaster.orderId}</td>
                                <td>${orderMaster.buyerName}</td>
                                <td>${orderMaster.buyerPhone}</td>
                                <td>${orderMaster.buyerAddress}</td>
                                <td>${orderMaster.orderAmount}</td>
                                <td>${orderMaster.orderStatusEnum.message}</td>
                                <td>${orderMaster.payStatusEnum.message}</td>
                                <td>${orderMaster.createTime}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderMaster.orderId}">详情</a>
                                </td>
                                <td>
                                    <#if orderMaster.orderStatusEnum.message = "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderMaster.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..orderMasterDTOPage.totalPages as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderMasterDTOPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#-- 新订单弹窗 -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提示信息
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单！
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('noticeMusic').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>

    </div>

</div>

<#-- 订单提示音乐 -->
<audio id="noticeMusic" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>

    var websocket = null;
    //判断浏览器是否支持websocket
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://cchen1997.natapp1.cc/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket！')
    }

    //开启websocket
    websocket.onopen = function (event) {
        console.log('建立连接！');
    }

    //关闭websocket
    websocket.onclose = function (event) {
        console.log('关闭连接！');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息！' + event.data);
        //弹窗提醒，播放提示音乐
        $('#myModal').modal('show');

        document.getElementById('noticeMusic').play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    //关闭窗口前，关闭websocket
    window.onbeforeunload = function () {
        websocket.close();
    }

</script>

</body>
</html>
