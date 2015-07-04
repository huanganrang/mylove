<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvPartnerCondition" %>
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
					<th><%=TlvPartnerCondition.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${lvPartnerCondition.accountId}							
					</td>							
					<th><%=TlvPartnerCondition.ALIAS_AGE%></th>	
					<td>
						${lvPartnerCondition.age}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvPartnerCondition.ALIAS_ADDRESS%></th>	
					<td>
						${lvPartnerCondition.address}							
					</td>							
					<th><%=TlvPartnerCondition.ALIAS_HEIGHT%></th>	
					<td>
						${lvPartnerCondition.height}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvPartnerCondition.ALIAS_WEIGHT%></th>	
					<td>
						${lvPartnerCondition.weight}							
					</td>							
					<th><%=TlvPartnerCondition.ALIAS_EDUCATION%></th>	
					<td>
						${lvPartnerCondition.education}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvPartnerCondition.ALIAS_MONTH_INCOME%></th>	
					<td>
						${lvPartnerCondition.monthIncome}							
					</td>							
				</tr>		
		</table>
	</div>
</div>