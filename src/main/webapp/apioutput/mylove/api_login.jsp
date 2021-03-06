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
		$('#login_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/login',
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
				$("#login_result").text(result);
			}
		});
		
		$("#le").click($("#login_Form").find("[name=password]"), encrypt);
		$("#ld").click($("#login_Form").find("[name=password]"), decrypt);
	});
	
	
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="login_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/login</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 180px;"><label>loginName(账户)：</label></td>
						<td><input name="loginName" type="text" class="span2" value="10000000"/>(账号或openId登录)</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>password(密码)：</label></td>
						<td><input name="password" type="text" class="span2" value="123456"/>(需要加密)<input type="button" value="加密" id="le">&nbsp;&nbsp;<input type="button" value="解密" id="ld"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#login_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="login_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、openId：服务器生产的用户唯一ID值<br/>
						tokenId：token值
			</div>
		</div>
	</div>
</body>
</html>