<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout">	

	<div data-options="region:'center',border:false">
		<div id="index_tabs" class="easyui-tabs" data-options="fit:true">
			<div title="首页" data-options="href:'api_home.jsp'"
				style="padding: 1px"></div>
			<div title="信箱" data-options="href:'../api_doing.jsp'"
				style="padding: 1px"></div>
			<div title="我的挖宝" data-options="href:'../api_doing.jsp'"
				style="padding: 1px"></div>	
			<div title="个人中心" data-options="href:'api_people_center.jsp'"
				style="padding: 1px"></div>	
		</div>
	</div>	
</body>
</html>