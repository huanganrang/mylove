<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvFollow" %>
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
					<th><%=TlvFollow.ALIAS_FROM_OPEN_ID%></th>	
					<td>
						${lvFollow.fromOpenId}							
					</td>							
					<th><%=TlvFollow.ALIAS_TO_OPEN_ID%></th>	
					<td>
						${lvFollow.toOpenId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvFollow.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvFollow.createTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>