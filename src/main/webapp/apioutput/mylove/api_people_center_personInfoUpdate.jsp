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
		$('#updateAccount_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/updateAccount',
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
				$("#updateAccount_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="updateAccount_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/updateAccount</td>
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
						<td align="right" style="width: 180px;"><label>personDesc(个性签名)：</label></td>
						<td><input name="personDesc" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>nickName(昵称)：</label></td>
						<td><input name="nickName" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>address(居住地)：</label></td>
						<td><input name="address" type="text" class="span2" value=""/>（格式：省code_市code_区code）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>height(身高)：</label></td>
						<td><input name="height" type="text" class="span2" value="170"/>（单位cm）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>monthIncome(月收入)：</label></td>
						<td><input name="monthIncome" type="text" class="span2" value="MI01"/>（MI01：小于2000；MI02：2000-5000；MI03：5000-10000；MI04：10000-20000;MI05：20000以上）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>marryStatus(婚姻状态)：</label></td>
						<td><input name="marryStatus" type="text" class="span2" value="MS01"/>（MS01：未婚；MS02：离异；MS03：丧偶）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>education(学历)：</label></td>
						<td><input name="education" type="text" class="span2" value="ED04"/>（ED01：初中及以下；ED02：高中及中专；ED03：大专；ED04：本科；ED05：硕士及以上）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>profession(职业)：</label></td>
						<td><input name="profession" type="text" class="span2" value="PF04"/>（PF01：在校学生；PF02：军人；PF03：私营业主；PF04：企业职工；PF05：政府机关/事业单位工作者；PF06：其他）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>birthdayStr(生日)：</label></td>
						<td><input name="birthdayStr" type="text" class="span2" value=""/>（格式：yyyy-MM-dd）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>weight(体重)：</label></td>
						<td><input name="weight" type="text" class="span2" value="55"/>（单位kg）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>constellation(星座)：</label></td>
						<td><input name="constellation" type="text" class="span2" value="CL01"/>（CL01：白羊座；CL02：金牛座；CL03：双子座；CL04：巨蟹座；CL05：狮子座；CL06：处女座；CL07：天秤座；CL08：天蝎座；CL09：射手座；CL10：摩羯座；CL11：水瓶座；CL12：双鱼座）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>qqSecret(QQ公开度)：</label></td>
						<td><input name="qqSecret" type="text" class="span2" value="0"/>（0：对VIP用户公开；1：保密）</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>mobileSecret(手机公开度)：</label></td>
						<td><input name="mobileSecret" type="text" class="span2" value="0"/>（0：对VIP用户公开；1：保密）</td>
					</tr>
					
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#updateAccount_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="updateAccount_result">
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