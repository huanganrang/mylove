<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAccount" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvAccountController/edit',
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
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${lvAccount.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvAccount.ALIAS_OPEN_ID%></th>	
					<td>
					<input class="span2" name="openId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvAccount.openId}"/>
					</td>
					<th><%=TlvAccount.ALIAS_NICK_NAME%></th>	
					<td>
					<input class="span2" name="nickName" type="text" class="span2"  value="${lvAccount.nickName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_SEX%></th>	
					<td>
						<jb:select dataType="SX" name="sex" value="${lvAccount.sex}"></jb:select>
					</td>							
					<th><%=TlvAccount.ALIAS_AUDIT_STATUS%></th>	
					<td>
						<jb:select dataType="AD" name="auditStatus" value="${lvAccount.auditStatus}"></jb:select>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_HEAD_IMG%></th>	
					<td colspan="3">
						<img alt="" src="${lvAccount.headImg}">
													
					</td>							
				</tr>
			</table>				
		</form>
	</div>
</div>