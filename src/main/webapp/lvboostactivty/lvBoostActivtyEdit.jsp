<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvBoostActivty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvBoostActivtyController/edit',
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
				<input type="hidden" name="id" value = "${lvBoostActivty.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_NAME%></th>	
					<td>
					<input class="span2" name="goodsName" type="text" class="span2"  value="${lvBoostActivty.goodsName}"/>
					</td>							
					<th><%=TlvBoostActivty.ALIAS_GOODS_PRICE%></th>	
					<td>
					<input class="span2" name="goodsPrice" type="text" class="span2"  value="${lvBoostActivty.goodsPrice}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_IMG%></th>	
					<td>
					<input class="span2" name="goodsImg" type="text" class="span2"  value="${lvBoostActivty.goodsImg}"/>
					</td>							
					<th><%=TlvBoostActivty.ALIAS_ASSIST_NUM%></th>	
					<td>
					<input class="span2" name="assistNum" type="text" class="span2"  value="${lvBoostActivty.assistNum}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_START_TIME%></th>	
					<td>
					<input class="span2" name="startTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvBoostActivty.FORMAT_START_TIME%>'})"   maxlength="0" value="${lvBoostActivty.startTime}"/>
					</td>							
					<th><%=TlvBoostActivty.ALIAS_END_TIME%></th>	
					<td>
					<input class="span2" name="endTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvBoostActivty.FORMAT_END_TIME%>'})"   maxlength="0" value="${lvBoostActivty.endTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_DETAIL_IMG%></th>	
					<td>
					<input class="span2" name="goodsDetailImg" type="text" class="span2"  value="${lvBoostActivty.goodsDetailImg}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>