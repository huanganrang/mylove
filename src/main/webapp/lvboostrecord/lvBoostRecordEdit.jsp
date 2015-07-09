<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvBoostRecord" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvBoostRecordController/edit',
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
				<input type="hidden" name="id" value = "${lvBoostRecord.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvBoostRecord.ALIAS_OPEN_ID%></th>	
					<td>
					<input class="span2" name="openId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvBoostRecord.openId}"/>
					</td>							
					<th><%=TlvBoostRecord.ALIAS_ACTIVTY_ID%></th>	
					<td>
					<input class="span2" name="activtyId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${lvBoostRecord.activtyId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostRecord.ALIAS_ASSIST_NUM%></th>	
					<td>
					<input class="span2" name="assistNum" type="text" class="span2"  value="${lvBoostRecord.assistNum}"/>
					</td>							
					<th><%=TlvBoostRecord.ALIAS_VISIT_NUM%></th>	
					<td>
					<input class="span2" name="visitNum" type="text" class="span2"  value="${lvBoostRecord.visitNum}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostRecord.ALIAS_BOOST_TIME%></th>	
					<td>
					<input class="span2" name="boostTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvBoostRecord.FORMAT_BOOST_TIME%>'})"   maxlength="0" value="${lvBoostRecord.boostTime}"/>
					</td>							
					<th><%=TlvBoostRecord.ALIAS_OPEN_TIME%></th>	
					<td>
					<input class="span2" name="openTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvBoostRecord.FORMAT_OPEN_TIME%>'})"   maxlength="0" value="${lvBoostRecord.openTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostRecord.ALIAS_OPEN_STATUS%></th>	
					<td>
					<input class="span2" name="openStatus" type="text" class="span2"  value="${lvBoostRecord.openStatus}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>