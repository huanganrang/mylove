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
		$('#updatePartnerCondition_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/updatePartnerCondition',
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
				$("#updatePartnerCondition_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="updatePartnerCondition_Form" action="" method="post">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/updatePartnerCondition</td>
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
						<td align="right" style="width: 180px;"><label>age(年龄)：</label></td>
						<td><input name="age" type="text" class="span2" value="不限"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>address(居住地)：</label></td>
						<td><input name="address" type="text" class="span2" value="不限"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>height(身高)：</label></td>
						<td><input name="height" type="text" class="span2" value="不限"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>education(学历)：</label></td>
						<td><input name="education" type="text" class="span2" value="不限"/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>monthIncome(月收入)：</label></td>
						<td><input name="monthIncome" type="text" class="span2" value="不限"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#updatePartnerCondition_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="updatePartnerCondition_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、name	   登录名         <br/>
						utype	    账号类型      <br/>
						third_user	第三方账号  <br/>
						head_image	头像地址    <br/>
						nickname	昵称          <br/>
						usex			性别          <br/>
						areaCode	地区          <br/>
						birthday	生日          <br/>
						bardian		个性签名      <br/>
						member_v	会员级别      <br/>
					
			</div>
		</div>
	</div>
</body>
</html>