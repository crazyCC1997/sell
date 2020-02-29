<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#-- 侧边栏sidebar -->
    <#include "../common/nav.ftl">
    <#-- 主体内容 -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <input name="categoryId" type="text" value="${(productCategory.categoryId)!''}" hidden />
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}" />
                        </div>
                        <div class="form-group">
                            <label>类目类型</label>
                            <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!''}" />
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>