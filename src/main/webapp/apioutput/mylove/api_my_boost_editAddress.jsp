<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	$(function() {
	 	parent.$.messager.progress('close');
		$('#editAddress_Form').form({
			url : '${pageContext.request.contextPath}/api/apiBoostController/editAddress',
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
				$("#editAddress_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="editAddress_Form" action="" method="post">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiBoostController/editAddress</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 180px;"><label>tokenId(token值)：</label></td>
						<td><input name="tokenId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>openId(用户openId)：</label></td>
						<td><input name="openId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>consignee(收货人姓名)：</label></td>
						<td><input name="consignee" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>mobile(手机号)：</label></td>
						<td><input name="mobile" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>province(所在省code)：</label></td>
						<td><input name="province" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>city(所在市code)：</label></td>
						<td><input name="city" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>district(所在区code)：</label></td>
						<td><input name="district" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>address(详细地址)：</label></td>
						<td><input name="address" type="text" class="span2" value=""/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#editAddress_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="editAddress_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
			</div>
		</div>
	</div>
</body>
</html>