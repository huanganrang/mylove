<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body >
	<div id="index_mylove_chat_tabs" class="easyui-tabs" data-options="fit:true">
		<div title="获取消息" data-options="href:'api_mylove_chat_getMessage.jsp'"
			style="padding: 1px"></div>
		<div title="消息配置获取" data-options="href:'api_mylove_chat_messageConfig.jsp'"
			style="padding: 1px"></div>
		<div title="免费次数消耗" data-options="href:'api_mylove_chat_freeNumUsed.jsp'"
			style="padding: 1px"></div>
		<div title="语音消息获取" data-options="href:'api_mylove_chat_getVideoMessage.jsp'"
			style="padding: 1px"></div>
		<div title="推动测试" data-options="href:'api_mylove_chat_push_test.jsp'"
			style="padding: 1px"></div>	
	</div>
</body>
</html>