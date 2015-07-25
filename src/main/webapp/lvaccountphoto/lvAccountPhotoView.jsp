<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAccountPhoto" %>
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
					<th><%=TlvAccountPhoto.ALIAS_OPEN_ID%></th>	
					<td>
						${lvAccountPhoto.openId}							
					</td>							
					<th><%=TlvAccountPhoto.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvAccountPhoto.createTime}							
					</td>						
				</tr>		
				<tr>	
					<th><%=TlvAccountPhoto.ALIAS_AUDIT_STATUS%></th>	
					<td colspan="3">
						${lvAccountPhoto.auditStatusZh}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAccountPhoto.ALIAS_PHOTO_IMG%></th>	
					<td>
						<img alt="" src="${lvAccountPhoto.photoImg}">					
					</td>						
				</tr>		
		</table>
	</div>
</div>