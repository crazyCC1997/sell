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
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderMasterDTO.orderId}</td>
                            <td>${orderMasterDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <#-- 订单详情表 -->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>单格</th>
                            <th>数量</th>
                            <th>总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderMasterDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#-- 操作 -->
                <div class="col-md-12 column">
                    <#if orderMasterDTO.getOrderStatusEnum().message == "新订单">
                        <a href="/sell/seller/order/finish?orderId=${orderMasterDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderMasterDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>