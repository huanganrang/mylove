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
					<th><%=TlvVisit.ALIAS_OPEN_ID%></th>	
					<td>
						${lvVisit.openId}							
					</td>							
					<th><%=TlvVisit.ALIAS_VISIT_OPEN_ID%></th>	
					<td>
						${lvVisit.visitOpenId}							
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