<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvBoostActivty" %>
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
					<th><%=TlvBoostActivty.ALIAS_GOODS_NAME%></th>	
					<td>
						${lvBoostActivty.goodsName}							
					</td>							
					<th><%=TlvBoostActivty.ALIAS_GOODS_PRICE%></th>	
					<td>
						${lvBoostActivty.goodsPrice}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_ASSIST_NUM%></th>	
					<td>
						${lvBoostActivty.assistNum}							
					</td>							
					<th><%=TlvBoostActivty.ALIAS_HOUR_OF_DAY%></th>	
					<td>
						${lvBoostActivty.hourOfDay}							
					</td>							
				</tr>	
				<tr>	
					<th>状态</th>	
					<td colspan="3">
						<c:choose>
							<c:when test="${lvBoostActivty.status == 1}">启用</c:when>
							<c:otherwise>停用</c:otherwise>
						</c:choose>
					</td>							
				</tr>
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_IMG%></th>	
					<td colspan="3">
						<img alt="" src="${lvBoostActivty.goodsImg}">
					</td>									
				</tr>		
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_DETAIL_IMG%></th>	
					<td colspan="3">
						${lvBoostActivty.goodsDetailImg}							
					</td>							
				</tr>		
		</table>
	</div>
</div>