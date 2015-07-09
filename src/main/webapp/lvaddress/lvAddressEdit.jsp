<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAddress" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvAddressController/edit',
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
				<input type="hidden" name="id" value = "${lvAddress.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvAddress.ALIAS_OPEN_ID%></th>	
					<td>
					<input class="span2" name="openId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvAddress.openId}"/>
					</td>							
					<th><%=TlvAddress.ALIAS_CONSIGNEE%></th>	
					<td>
					<input class="span2" name="consignee" type="text" class="span2"  value="${lvAddress.consignee}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAddress.ALIAS_MOBILE%></th>	
					<td>
					<input class="span2" name="mobile" type="text" class="span2"  value="${lvAddress.mobile}"/>
					</td>							
					<th><%=TlvAddress.ALIAS_PROVINCE%></th>	
					<td>
					<input class="span2" name="province" type="text" class="span2"  value="${lvAddress.province}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAddress.ALIAS_CITY%></th>	
					<td>
					<input class="span2" name="city" type="text" class="span2"  value="${lvAddress.city}"/>
					</td>							
					<th><%=TlvAddress.ALIAS_DISTRICT%></th>	
					<td>
					<input class="span2" name="district" type="text" class="span2"  value="${lvAddress.district}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAddress.ALIAS_ADDRESS%></th>	
					<td>
					<input class="span2" name="address" type="text" class="span2"  value="${lvAddress.address}"/>
					</td>							
					<th><%=TlvAddress.ALIAS_CREATE_TIME%></th>	
					<td>
					<input class="span2" name="createTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAddress.FORMAT_CREATE_TIME%>'})"   maxlength="0" value="${lvAddress.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAddress.ALIAS_UPDATE_TIME%></th>	
					<td>
					<input class="span2" name="updateTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAddress.FORMAT_UPDATE_TIME%>'})"   maxlength="0" value="${lvAddress.updateTime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>