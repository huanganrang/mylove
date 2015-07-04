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
					<th><%=TlvAccountPhoto.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${lvAccountPhoto.accountId}							
					</td>							
					<th><%=TlvAccountPhoto.ALIAS_PHOTO_IMG%></th>	
					<td>
						${lvAccountPhoto.photoImg}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAccountPhoto.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvAccountPhoto.createTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>