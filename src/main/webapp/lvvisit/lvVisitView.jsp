<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvVisit" %>
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
					<th><%=TlvVisit.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${lvVisit.accountId}							
					</td>							
					<th><%=TlvVisit.ALIAS_VISIT_ACCOUNT_ID%></th>	
					<td>
						${lvVisit.visitAccountId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvVisit.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvVisit.createTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>