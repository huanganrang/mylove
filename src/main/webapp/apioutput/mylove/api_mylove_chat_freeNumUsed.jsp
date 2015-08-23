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
		$('#mylove_chat_freeNumUsed_Form').form({
			url : '${pageContext.request.contextPath}/api/apiChatController/freeNumUsed',
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
				$("#mylove_chat_freeNumUsed_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="mylove_chat_freeNumUsed_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td>${pageContext.request.contextPath}/api/apiChatController/freeNumUsed</td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>openId(用户openId)：</label></td>
						<td><input name="openId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>ftype(免费类型)：</label></td>
						<td><input name="ftype" type="text" class="span2" value=""/>(FT01：聊天信息；FT02：搜索；FT03：打招呼；FT04：查看照片；FT05：听语音)</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#mylove_chat_freeNumUsed_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="mylove_chat_freeNumUsed_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
					3、obj：返回当前类型剩余次数<br/>
			</div>
		</div>
	</div>
</body>
</html>