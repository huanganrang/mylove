<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvOrder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvOrderController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${lvOrder.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvOrder.ALIAS_ORDER_NO%></th>	
					<td>
					<input class="span2" name="orderNo" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvOrder.orderNo}"/>
					</td>							
					<th><%=TlvOrder.ALIAS_OPEN_ID%></th>	
					<td>
					<input class="span2" name="openId" type="text" class="span2"  value="${lvOrder.openId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvOrder.ALIAS_VIP_LEVEL%></th>	
					<td>
					<input class="span2" name="vipLevel" type="text" class="span2"  value="${lvOrder.vipLevel}"/>
					</td>							
					<th><%=TlvOrder.ALIAS_ORDER_STATUS%></th>	
					<td>
					<input class="span2" name="orderStatus" type="text" class="span2"  value="${lvOrder.orderStatus}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvOrder.ALIAS_CHANNEL%></th>	
					<td>
					<input class="span2" name="channel" type="text" class="span2"  value="${lvOrder.channel}"/>
					</td>							
					<th><%=TlvOrder.ALIAS_AMOUNT%></th>	
					<td>
					<input class="span2" name="amount" type="text" class="span2"  value="${lvOrder.amount}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvOrder.ALIAS_PAYTIME%></th>	
					<td>
					<input class="span2" name="paytime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvOrder.FORMAT_PAYTIME%>'})"   maxlength="0" value="${lvOrder.paytime}"/>
					</td>							
					<th><%=TlvOrder.ALIAS_CREATETIME%></th>	
					<td>
					<input class="span2" name="createtime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvOrder.FORMAT_CREATETIME%>'})"   maxlength="0" value="${lvOrder.createtime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>