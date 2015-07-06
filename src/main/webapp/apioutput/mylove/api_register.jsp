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
		$('#register_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/register',
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
				$("#register_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="register_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/register</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 180px;"><label>nikeName(昵称)：</label></td>
						<td><input name="nickName" type="text" class="span2" value="John"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>age(年龄)：</label></td>
						<td><input name="age" type="text" class="span2" value="25"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>sex(性别)：</label></td>
						<td><input name="sex" type="text" class="span2" value="SX01"/>（男：SX01；女：SX02）</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#register_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="register_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、obj:xxxx  成功时token值
			</div>
		</div>
	</div>
</body>
</html>