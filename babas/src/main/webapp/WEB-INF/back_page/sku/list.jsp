<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>babasport-list</title>
    <script type="text/javascript">
        function updateSku(skuId) {
            $("#m" + skuId).attr("disabled", false);
            $("#p" + skuId).attr("disabled", false);
            $("#i" + skuId).attr("disabled", false);
            $("#l" + skuId).attr("disabled", false);
            $("#f" + skuId).attr("disabled", false);
        }
        ;
        function addSku(skuId) {
            var m = $("#m" + skuId).val();
            var p = $("#p" + skuId).val();
            var i = $("#i" + skuId).val();
            var l = $("#l" + skuId).val();
            var f = $("#f" + skuId).val();
            var url = "/sku/add.do";
            var params = {
                "id": skuId,
                "marketPrice": m,
                "skuPrice": p,
                "stockInventory": i,
                "skuUpperLimit": l,
                "deliveFee": f
            };
            $.post(url, params, function (data) {
                alert(data.message);
            }, "json");
            $("#m" + skuId).attr("disabled", true);
            $("#p" + skuId).attr("disabled", true);
            $("#i" + skuId).attr("disabled", true);
            $("#l" + skuId).attr("disabled", true);
            $("#f" + skuId).attr("disabled", true);
        }
    </script>

</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置: 库存管理 - 列表</div>
    <div class="clear"></div>
</div>
<div class="body-box">
    <form method="post" id="tableForm">
        <table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
            <thead class="pn-lthead">
            <tr>
                <th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
                <th>商品编号</th>
                <th>商品颜色</th>
                <th>商品尺码</th>
                <th>市场价格</th>
                <th>销售价格</th>
                <th>库 存</th>
                <th>购买限制</th>
                <th>运 费</th>
                <th>是否赠品</th>
                <th>操 作</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <c:forEach items="${sku}" var="sku">
                <tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
                    <td><input type="checkbox" value="73" name="ids"/></td>
                    <td>${pno }</td>
                    <td align="center">${sku.color.name }</td>
                    <td align="center">${sku.size }</td>
                    <td align="center"><input type="text" size="10" disabled="disabled" value="${sku.marketPrice }"
                                              id="m${sku.id }"/></td>
                    <td align="center"><input type="text" size="10" disabled="disabled" value="${sku.skuPrice }"
                                              id="p${sku.id }"/></td>
                    <td align="center"><input type="text" size="10" disabled="disabled" value="${sku.stockInventory }"
                                              id="i${sku.id }"/></td>
                    <td align="center"><input type="text" size="10" disabled="disabled" value="${sku.skuUpperLimit }"
                                              id="l${sku.id }"/></td>
                    <td align="center"><input type="text" size="10" disabled="disabled" value="${sku.deliveFee }"
                                              id="f${sku.id }"/></td>
                    <td align="center"><c:if test="${sku.skuType == 1}">不是</c:if><c:if
                            test="${sku.skuType == 0}">是</c:if></td>
                    <td align="center"><a class="pn-opt" href="javascript:updateSku(${sku.id })">修改</a> | <a
                            class="pn-opt" href="javascript:addSku(${sku.id })">保存</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>