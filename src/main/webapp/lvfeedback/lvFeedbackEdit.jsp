<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvFeedback" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvFeedbackController/edit',
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
				<input type="hidden" name="id" value = "${lvFeedback.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvFeedback.ALIAS_OPEN_ID%></th>	
					<td>
					<input class="span2" name="openId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvFeedback.openId}"/>
					</td>							
					<th><%=TlvFeedback.ALIAS_CONTACT_WAY%></th>	
					<td>
					<input class="span2" name="contactWay" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvFeedback.contactWay}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvFeedback.ALIAS_CONTENT%></th>	
					<td>
					<input class="span2" name="content" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvFeedback.content}"/>
					</td>							
					<th><%=TlvFeedback.ALIAS_CREATE_TIME%></th>	
					<td>
					<input class="span2" name="createTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvFeedback.FORMAT_CREATE_TIME%>'})"   maxlength="0" value="${lvFeedback.createTime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>