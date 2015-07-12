<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAddress" %>
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
					<th><%=TlvAddress.ALIAS_OPEN_ID%></th>	
					<td>
						${lvAddress.openId}							
					</td>							
					<th><%=TlvAddress.ALIAS_CONSIGNEE%></th>	
					<td>
						${lvAddress.consignee}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAddress.ALIAS_MOBILE%></th>	
					<td>
						${lvAddress.mobile}							
					</td>							
					<th><%=TlvAddress.ALIAS_PROVINCE%></th>	
					<td>
						${lvAddress.province}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAddress.ALIAS_CITY%></th>	
					<td>
						${lvAddress.city}							
					</td>							
					<th><%=TlvAddress.ALIAS_DISTRICT%></th>	
					<td>
						${lvAddress.district}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAddress.ALIAS_ADDRESS%></th>	
					<td>
						${lvAddress.address}							
					</td>							
					<th><%=TlvAddress.ALIAS_CREATE_TIME%></th>	
					<td>
						${lvAddress.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvAddress.ALIAS_UPDATE_TIME%></th>	
					<td>
						${lvAddress.updateTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>