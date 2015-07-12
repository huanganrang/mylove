<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String searchType = request.getParameter("searchType");
%>
</head>
<body>
<script type="text/javascript">
	$(function() {
	 	parent.$.messager.progress('close');
		$('#accountSearch_<%=searchType %>_Form').form({
			url : '${pageContext.request.contextPath}/api/apiHomeController/accountSearch',
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
				$("#accountSearch_<%=searchType %>_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="accountSearch_<%=searchType %>_Form">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiHomeController/accountSearch</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 180px;"><label>tokenId(token值)：</label></td>
						<td><input name="tokenId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>openId(账户号)：</label></td>
						<td><input name="openId" type="text" class="span2" value="10000000"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>searchType(搜索类型)：</label></td>
						<td><input name="searchType" type="text" class="span2" value="<%=searchType %>"/>(1、全国；2、同城；3、openId搜索)</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>searchOpenId(搜索账户号)：</label></td>
						<td><input name="searchOpenId" type="text" class="span2" value=""/>（当searchType=3时需要传入值）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>page(第几页)：</label></td>
						<td><input name="page" type="text" class="span2" value="1"/></td>
					</tr>
					
					<tr>
						<td align="right" style="width: 180px;"><label>rows(每页数)：</label></td>
						<td><input name="rows" type="text" class="span2" value="10"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#accountSearch_<%=searchType %>_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="accountSearch_<%=searchType %>_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、page	   当前页         <br/>
						pageSize	   每页数      <br/>
						total	总数  <br/>
						openId	用户openId值    <br/>
						headImg	头像图片路径     <br/>
					
			</div>
		</div>
	</div>
</body>
</html>