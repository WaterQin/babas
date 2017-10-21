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
    <div class="rpos">当前位置: 订单管理 - 列表</div>
    <div class="clear"></div>
</div>
<div class="body-box">
    <input type="hidden" name="pageNo" value="${pageNo}"/>
    <form method="post" id="tableForm">
        <input type="hidden" value="" name="pageNo"/>
        <input type="hidden" value="" name="queryName"/>
        <table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
            <thead class="pn-lthead">
            <tr>
                <th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
                <th>订单号</th>
                <th>订单金额</th>
                <th>运费</th>
                <th>应付金额</th>
                <th>支付方式</th>
                <th>支付要求</th>
                <th>支付状态</th>
                <th>订单状态</th>
                <th>下单时间</th>
                <th>备注</th>
                <th>操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <c:forEach items="${orders}" var="order">
                <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
                    <td><input type="checkbox" name="ids" value="73"/></td>
                    <td align="center">${order.oid}</td>
                    <td align="center">${order.payableFee}</td>
                    <td align="center">${order.deliverFee}</td>
                    <td align="center">${order.totalPrice}</td>
                    <td align="center">
                        <c:if test="${order.paymentWay == 0 }">到付</c:if>
                        <c:if test="${order.paymentWay == 1 }">在线支付</c:if>
                        <c:if test="${order.paymentWay == 2 }">邮局支付</c:if>
                        <c:if test="${order.paymentWay == 3 }">公司转账</c:if>
                    </td>
                    <td align="center">
                        <c:if test="${order.paymentCash == 0 }">现金</c:if>
                        <c:if test="${order.paymentCash == 1 }">刷卡</c:if>
                    </td>
                    <td align="center">
                        <c:if test="${order.isPaiy==0}">到付</c:if>
                        <c:if test="${order.isPaiy==1}">待付款</c:if>
                        <c:if test="${order.isPaiy==2}">已付款</c:if>
                        <c:if test="${order.isPaiy==3}">带退款</c:if>
                        <c:if test="${order.isPaiy==4}">退款成功</c:if>
                        <c:if test="${order.isPaiy==5}">退款失败</c:if>
                    </td>
                    <td align="center">
                        <c:if test="${order.state==0}">提交订单</c:if>
                        <c:if test="${order.state==1}">仓库配货</c:if>
                        <c:if test="${order.state==2}">商品出库</c:if>
                        <c:if test="${order.state==3}">等待收货</c:if>
                        <c:if test="${order.state==4}">完成</c:if>
                        <c:if test="${order.state==5}">待退货</c:if>
                        <c:if test="${order.state==6}">已退货</c:if>
                    </td>
                    <td align="center">${order.createDate}</td>
                    <td align="center">${order.note}</td>
                    <td align="center">
                        <a href="/order/view.do?id=${order.id}" class="pn-opt">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="margin-top:15px;">
            <!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
                <input class="submit" type="button" value="通过" onclick="optPass();"/> -->
        </div>
    </form>
</div>
</body>
</html>