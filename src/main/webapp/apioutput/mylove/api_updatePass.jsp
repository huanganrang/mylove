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
		$('#updatePass_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/updatePass',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				//$("input[name=password]").val($("input[name=password1]").val());
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				$("#updatePass_result").text(result);
			}
		});
		
		$("#ep1").click($("#updatePass_Form").find("[name=oldPass]"), encrypt);
		$("#ed1").click($("#updatePass_Form").find("[name=oldPass]"), decrypt);
		$("#ep2").click($("#updatePass_Form").find("[name=password]"), encrypt);
		$("#ed2").click($("#updatePass_Form").find("[name=password]"), decrypt);
	});
	
	
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="updatePass_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/updatePass</td>
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
						<td align="right" style="width: 180px;"><label>oldPass(旧密码)：</label></td>
						<td><input name="oldPass" type="text" class="span2" value=""/>(需要加密)<input type="button" value="加密" id="ep1">&nbsp;&nbsp;<input type="button" value="解密" id="ed1"></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>password(新密码)：</label></td>
						<td>
							<input name="password" type="text" class="span2" value=""/>(需要加密)<input type="button" value="加密" id="ep2">&nbsp;&nbsp;<input type="button" value="解密"  id="ed2">
							<!-- <input type="hidden" name="password"> -->
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#updatePass_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="updatePass_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
			</div>
		</div>
	</div>
</body>
</html>