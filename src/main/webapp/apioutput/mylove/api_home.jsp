<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body >
	<div id="index_home_tabs" class="easyui-tabs" data-options="fit:true">
		<div title="全国" data-options="href:'api_home_accountSearch.jsp?searchType=1'"
			style="padding: 1px"></div>
		<div title="同城" data-options="href:'api_home_accountSearch.jsp?searchType=2'"
			style="padding: 1px"></div>
		<div title="ID搜索" data-options="href:'api_home_accountSearch.jsp?searchType=3'"
			style="padding: 1px"></div>	
		<div title="个人详情" data-options="href:'api_people_center_personInfo.jsp?type=2'"
			style="padding: 1px"></div>	
		<div title="关注" data-options="href:'api_home_attention.jsp'"
			style="padding: 1px"></div>
		<div title="取消关注" data-options="href:'api_home_cancelAttention.jsp'"
			style="padding: 1px"></div>
		<div title="打招呼" data-options="href:'api_home_sayHello.jsp'"
			style="padding: 1px"></div>
		<div title="一键打招呼列表" data-options="href:'api_home_sayHelloList.jsp'"
			style="padding: 1px"></div>	
	</div>
</body>
</html>