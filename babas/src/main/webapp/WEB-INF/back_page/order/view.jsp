<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>babasport-list</title>
</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置: 订单管理 - 查看</div>
    <div class="clear"></div>
</div>
<div class="body-box">
    <form id="jvForm" action="v_list.do" method="post">
        <table cellspacing="1" cellpadding="2" border="0" width="100%" class="pn-ftable">
            <tbody>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">收货人:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${addr.name}</td>
                <td width="12%" class="pn-flabel pn-flabel-h">手机:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${addr.phone}</td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">所在地区:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${addr.city}</td>
                <td width="12%" class="pn-flabel pn-flabel-h">地址:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${addr.addr}</td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">订单号:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.oid}</td>
                <td width="12%" class="pn-flabel pn-flabel-h">运费:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.deliverFee}</td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">订单金额:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.payableFee}元</td>
                <td width="12%" class="pn-flabel pn-flabel-h">应付金额:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.totalPrice}元</td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">支付方式:</td>
                <td width="38%" colspan="1" class="pn-fcontent">
                    <c:if test="${order.paymentWay == 0 }">到付</c:if>
                    <c:if test="${order.paymentWay == 1 }">在线支付</c:if>
                    <c:if test="${order.paymentWay == 2 }">邮局支付</c:if>
                    <c:if test="${order.paymentWay == 3 }">公司转账</c:if>
                </td>
                <td width="12%" class="pn-flabel pn-flabel-h">付款要求:</td>
                <td width="38%" colspan="1" class="pn-fcontent">
                    <c:if test="${order.paymentCash == 0 }">现金</c:if>
                    <c:if test="${order.paymentCash == 1 }">刷卡</c:if>
                </td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">支付状态:</td>
                <td width="38%" colspan="1" class="pn-fcontent">
                    <c:if test="${order.isPaiy==0}">到付</c:if>
                    <c:if test="${order.isPaiy==1}">待付款</c:if>
                    <c:if test="${order.isPaiy==2}">已付款</c:if>
                    <c:if test="${order.isPaiy==3}">带退款</c:if>
                    <c:if test="${order.isPaiy==4}">退款成功</c:if>
                    <c:if test="${order.isPaiy==5}">退款失败</c:if>

                </td>
                <td width="12%" class="pn-flabel pn-flabel-h">订单状态:</td>
                <td width="38%" colspan="1" class="pn-fcontent">
                    <c:if test="${order.state==0}">提交订单</c:if>
                    <c:if test="${order.state==1}">仓库配货</c:if>
                    <c:if test="${order.state==2}">商品出库</c:if>
                    <c:if test="${order.state==3}">等待收货</c:if>
                    <c:if test="${order.state==4}">完成</c:if>
                    <c:if test="${order.state==5}">待退货</c:if>
                    <c:if test="${order.state==6}">已退货</c:if>
                </td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">送货时间:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.delivery}</td>
                <td width="12%" class="pn-flabel pn-flabel-h">电话确认:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.isConfirm}</td>
            </tr>
            <tr>
                <td width="12%" class="pn-flabel pn-flabel-h">创建时间:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.createDate}</td>
                <td width="12%" class="pn-flabel pn-flabel-h">备注:</td>
                <td width="38%" colspan="1" class="pn-fcontent">${order.note}</td>
            </tr>
            </tbody>
        </table>
        <table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
            <thead class="pn-ltbody">
            <tr>
                <th>商品编号</th>
                <th>商品名称</th>
                <th>商品颜色</th>
                <th>商品尺码</th>
                <th>商品销售价</th>
                <th>购买数量</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <c:forEach items="${details}" var="detail">
                <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
                    <td align="center">${detail.productNo}</td>
                    <td align="center">${detail.productName}</td>
                    <td align="center">${detail.color}</td>
                    <td align="center">${detail.size}</td>
                    <td align="center">${detail.skuPrice}</td>
                    <td align="center">${detail.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<div style="margin-top:15px;">
    <input type="button" onclick="javascript:window.print()" value="打印" class="submit"/>
</div>
</body>
</html>