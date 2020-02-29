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
</body>
</html>
