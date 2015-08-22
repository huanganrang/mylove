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
		
		$("#re").click($("#register_Form").find("[name=password]"), encrypt);
		$("#rd").click($("#register_Form").find("[name=password]"), decrypt);
	});
	
	function encrypt(obj) {
		var password = $(obj.data).val();
		$.post('${pageContext.request.contextPath}/api/apiAccountController/encrypt', {
			password : password
		}, function(result) {
			if (result.success) {
				$(obj.data).val(result.obj);
			} else {
				alert("加密失败");
			}
		}, 'JSON');
	}
	function decrypt(obj) {
		var password = $(obj.data).val();
		$.post('${pageContext.request.contextPath}/api/apiAccountController/decrypt', {
			password : password
		}, function(result) {
			if (result.success) {
				$(obj.data).val(result.obj);
			} else {
				alert("解密失败");
			}
		}, 'JSON');
	}
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
						<td align="right" style="width: 180px;"><label>loginName(*账号)：</label></td>
						<td><input name="loginName" type="text" class="span2" value="John"/>varchar(20)</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>password(*密码)：</label></td>
						<td><input name="password" type="text" class="span2" value="123456"/>(需要加密)<input type="button" value="加密" id="re">&nbsp;&nbsp;<input type="button" value="解密" id="rd"></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>nikeName(昵称)：</label></td>
						<td><input name="nickName" type="text" class="span2" value=""/>（昵称不填则自动赋值loginName）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>age(*年龄)：</label></td>
						<td><input name="age" type="text" class="span2" value="25"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>sex(*性别)：</label></td>
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
					3、openId：服务器生成的用户唯一ID值<br/>
						tokenId：token值
			</div>
		</div>
	</div>
</body>
</html>