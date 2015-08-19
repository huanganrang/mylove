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
		$('#mylove_chat_getMessage_Form').form({
			url : '${pageContext.request.contextPath}/api/apiChatController/getMessage',
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
				$("#mylove_chat_getMessage_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="mylove_chat_getMessage_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiChatController/getMessage</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>openId(女神openId，为空时则系统随机挑选一位女神返回)：</label></td>
						<td><input name="openId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>type(消息类型)：</label></td>
						<td><input name="type" type="text" class="span2" value=""/>（MQ：文本；VQ：语音；其他：消息随机）</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#mylove_chat_getMessage_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="mylove_chat_getMessage_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、openId：女神openId<br/>
						type：消息类型（MT01：文本；MT02：语音）<br/>
						duration：时长（单位秒）<br/>
						message：文本消息内容/语音地址<br/>
			</div>
		</div>
	</div>
</body>
</html>