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
		$('#mylove_chat_messageConfig_Form').form({
			url : '${pageContext.request.contextPath}/api/apiChatController/messageConfig',
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
				$("#mylove_chat_messageConfig_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="mylove_chat_messageConfig_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiChatController/messageConfig</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>openId(用户openId)：</label></td>
						<td><input name="openId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#mylove_chat_messageConfig_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="mylove_chat_messageConfig_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、voicePercent：语音消息所占百分比<br/>
						replyPro：消息回复概率值<br/>
						FT01：剩余免费聊天信息次数<br/>
						FT02：剩余免费搜索次数<br/>
						FT03：剩余免费打招呼次数<br/>
						FT04：剩余免费查看照片次数<br/>
						FT05：剩余免费听语音次数<br/>
			</div>
		</div>
	</div>
</body>
</html>