<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvFeedback" %>
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
					<th><%=TlvFeedback.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${lvFeedback.accountId}							
					</td>							
					<th><%=TlvFeedback.ALIAS_CONTACT_WAY%></th>	
					<td>
						${lvFeedback.contactWay}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvFeedback.ALIAS_CONTENT%></th>	
					<td>
						${lvFeedback.content}							
					</td>							
					<th><%=TlvFeedback.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvFeedback.createTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>