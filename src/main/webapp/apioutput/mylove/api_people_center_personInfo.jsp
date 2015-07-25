<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	int type = Integer.valueOf(request.getParameter("type"));
%>
</head>
<body>
<script type="text/javascript">
	$(function() {
	 	parent.$.messager.progress('close');
		$('#personInfo_<%=type %>_Form').form({
			url : '${pageContext.request.contextPath}/api/apiAccountController/personInfo',
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
				$("#personInfo_<%=type %>_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="personInfo_<%=type %>_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiAccountController/personInfo</td>
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
						<td align="right" style="width: 180px;"><label>byOpenId(对方账户号)：</label></td>
						<td><input name="byOpenId" type="text" class="span2" value="<%if(type == 2){%>10000001<%}%>"/>(不传表示查看自己个人资料)</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#personInfo_<%=type %>_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="personInfo_<%=type %>_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、openId	  用户openId值         <br/>
						sex	性别          <br/>
						birthday	生日          <br/>
						age	年龄         <br/>
						personDesc	    个性签名      <br/>
						nickName	昵称  <br/>
						address	居住地    <br/>
						height	身高          <br/>
						weight	体重          <br/>
						monthIncome			月收入          <br/>
						marryStatus	婚姻状态          <br/>
						education		学历      <br/>
						profession	职业      <br/>
						constellation	星座      <br/>
						qq	QQ号      <br/>
						qqSecret	QQ公开度      <br/>
						mobile	手机号    <br/>
						mobileSecret	手机公开度     <br/>
						headImg	头像图片路径     <br/>
						auditStatus	头像审核状态     <br/>
						vipLevel	VIP等级     <br/>
						vipOpenTime	VIP开通时间     <br/>
						vipEndTime	VIP到期时间     <br/>
						photoImg	照片路径     <br/>
						lastLoginTime	最近上线区域    <br/>
						lastLoginArea	最近上线时间     <br/>
			</div>
		</div>
	</div>
</body>
</html>