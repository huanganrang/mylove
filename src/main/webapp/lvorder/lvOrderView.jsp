<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvOrder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvOrder.ALIAS_ORDER_NO%></th>	
					<td>
						${lvOrder.orderNo}							
					</td>							
					<th><%=TlvOrder.ALIAS_OPEN_ID%></th>	
					<td>
						${lvOrder.openId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvOrder.ALIAS_VIP_LEVEL%></th>	
					<td>
						${lvOrder.vipLevel}							
					</td>							
					<th><%=TlvOrder.ALIAS_ORDER_STATUS%></th>	
					<td>
						${lvOrder.orderStatus}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvOrder.ALIAS_CHANNEL%></th>	
					<td>
						${lvOrder.channel}							
					</td>							
					<th><%=TlvOrder.ALIAS_AMOUNT%></th>	
					<td>
						${lvOrder.amount}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvOrder.ALIAS_PAYTIME%></th>	
					<td>
						${lvOrder.paytime}							
					</td>							
					<th><%=TlvOrder.ALIAS_CREATETIME%></th>	
					<td>
						${lvOrder.createtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>