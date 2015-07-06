<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAccount" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>LvAccount管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/lvAccountController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/lvAccountController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/lvAccountController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/lvAccountController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
				}, {
				field : 'openId',
				title : '<%=TlvAccount.ALIAS_OPEN_ID%>',
				width : 50		
				}, {
				field : 'loginName',
				title : '<%=TlvAccount.ALIAS_LOGIN_NAME%>',
				width : 50		
				}, {
				field : 'nickName',
				title : '<%=TlvAccount.ALIAS_NICK_NAME%>',
				width : 50		
				}, {
				field : 'password',
				title : '<%=TlvAccount.ALIAS_PASSWORD%>',
				width : 50		
				}, {
				field : 'sex',
				title : '<%=TlvAccount.ALIAS_SEX%>',
				width : 50		
				}, {
				field : 'birthday',
				title : '<%=TlvAccount.ALIAS_BIRTHDAY%>',
				width : 50		
				}, {
				field : 'height',
				title : '<%=TlvAccount.ALIAS_HEIGHT%>',
				width : 50		
				}, {
				field : 'weight',
				title : '<%=TlvAccount.ALIAS_WEIGHT%>',
				width : 50		
				}, {
				field : 'mobile',
				title : '<%=TlvAccount.ALIAS_MOBILE%>',
				width : 50		
				}, {
				field : 'qq',
				title : '<%=TlvAccount.ALIAS_QQ%>',
				width : 50		
				}, {
				field : 'address',
				title : '<%=TlvAccount.ALIAS_ADDRESS%>',
				width : 50		
				}, {
				field : 'education',
				title : '<%=TlvAccount.ALIAS_EDUCATION%>',
				width : 50		
				}, {
				field : 'profession',
				title : '<%=TlvAccount.ALIAS_PROFESSION%>',
				width : 50		
				}, {
				field : 'monthIncome',
				title : '<%=TlvAccount.ALIAS_MONTH_INCOME%>',
				width : 50		
				}, {
				field : 'marryStatus',
				title : '<%=TlvAccount.ALIAS_MARRY_STATUS%>',
				width : 50		
				}, {
				field : 'constellation',
				title : '<%=TlvAccount.ALIAS_CONSTELLATION%>',
				width : 50		
				}, {
				field : 'personDesc',
				title : '<%=TlvAccount.ALIAS_PERSON_DESC%>',
				width : 50		
				}, {
				field : 'createTime',
				title : '<%=TlvAccount.ALIAS_CREATE_TIME%>',
				width : 50		
				}, {
				field : 'updateTime',
				title : '<%=TlvAccount.ALIAS_UPDATE_TIME%>',
				width : 50		
				}, {
				field : 'auditStatus',
				title : '<%=TlvAccount.ALIAS_AUDIT_STATUS%>',
				width : 50		
				}, {
				field : 'headImg',
				title : '<%=TlvAccount.ALIAS_HEAD_IMG%>',
				width : 50		
				}, {
				field : 'longitude',
				title : '<%=TlvAccount.ALIAS_LONGITUDE%>',
				width : 50		
				}, {
				field : 'latitude',
				title : '<%=TlvAccount.ALIAS_LATITUDE%>',
				width : 50		
				}, {
				field : 'vipLevel',
				title : '<%=TlvAccount.ALIAS_VIP_LEVEL%>',
				width : 50		
				}, {
				field : 'vipOpenTime',
				title : '<%=TlvAccount.ALIAS_VIP_OPEN_TIME%>',
				width : 50		
				}, {
				field : 'online',
				title : '<%=TlvAccount.ALIAS_ONLINE%>',
				width : 50		
				}, {
				field : 'lastLoginTime',
				title : '<%=TlvAccount.ALIAS_LAST_LOGIN_TIME%>',
				width : 50		
				}, {
				field : 'visitNum',
				title : '<%=TlvAccount.ALIAS_VISIT_NUM%>',
				width : 50		
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前数据？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/lvAccountController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/lvAccountController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function viewFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '查看数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/lvAccountController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/lvAccountController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function downloadTable(){
		var options = dataGrid.datagrid("options");
		var $colums = [];		
		$.merge($colums, options.columns); 
		$.merge($colums, options.frozenColumns);
		var columsStr = JSON.stringify($colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/lvAccountController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>	
							<th><%=TlvAccount.ALIAS_OPEN_ID%></th>	
							<td>
									<input type="text" name="openId" maxlength="10" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_LOGIN_NAME%></th>	
							<td>
									<input type="text" name="loginName" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_NICK_NAME%></th>	
							<td>
									<input type="text" name="nickName" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_PASSWORD%></th>	
							<td>
									<input type="text" name="password" maxlength="50" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_SEX%></th>	
							<td>
									<input type="text" name="sex" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_BIRTHDAY%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_BIRTHDAY%>'})" id="birthdayBegin" name="birthdayBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_BIRTHDAY%>'})" id="birthdayEnd" name="birthdayEnd"/>
							</td>
							<th><%=TlvAccount.ALIAS_HEIGHT%></th>	
							<td>
									<input type="text" name="height" maxlength="10" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_WEIGHT%></th>	
							<td>
									<input type="text" name="weight" maxlength="10" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_MOBILE%></th>	
							<td>
									<input type="text" name="mobile" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_QQ%></th>	
							<td>
									<input type="text" name="qq" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_ADDRESS%></th>	
							<td>
									<input type="text" name="address" maxlength="100" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_EDUCATION%></th>	
							<td>
									<input type="text" name="education" maxlength="20" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_PROFESSION%></th>	
							<td>
									<input type="text" name="profession" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_MONTH_INCOME%></th>	
							<td>
									<input type="text" name="monthIncome" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_MARRY_STATUS%></th>	
							<td>
									<input type="text" name="marryStatus" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_CONSTELLATION%></th>	
							<td>
									<input type="text" name="constellation" maxlength="20" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_PERSON_DESC%></th>	
							<td>
									<input type="text" name="personDesc" maxlength="65535" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_CREATE_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_CREATE_TIME%>'})" id="createTimeBegin" name="createTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_CREATE_TIME%>'})" id="createTimeEnd" name="createTimeEnd"/>
							</td>
							<th><%=TlvAccount.ALIAS_UPDATE_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_UPDATE_TIME%>'})" id="updateTimeBegin" name="updateTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_UPDATE_TIME%>'})" id="updateTimeEnd" name="updateTimeEnd"/>
							</td>
							<th><%=TlvAccount.ALIAS_AUDIT_STATUS%></th>	
							<td>
									<input type="text" name="auditStatus" maxlength="20" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_HEAD_IMG%></th>	
							<td>
									<input type="text" name="headImg" maxlength="100" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_LONGITUDE%></th>	
							<td>
									<input type="text" name="longitude" maxlength="10" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_LATITUDE%></th>	
							<td>
									<input type="text" name="latitude" maxlength="10" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_VIP_LEVEL%></th>	
							<td>
									<input type="text" name="vipLevel" maxlength="20" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TlvAccount.ALIAS_VIP_OPEN_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_VIP_OPEN_TIME%>'})" id="vipOpenTimeBegin" name="vipOpenTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_VIP_OPEN_TIME%>'})" id="vipOpenTimeEnd" name="vipOpenTimeEnd"/>
							</td>
							<th><%=TlvAccount.ALIAS_ONLINE%></th>	
							<td>
									<input type="text" name="online" maxlength="20" class="span2"/>
							</td>
							<th><%=TlvAccount.ALIAS_LAST_LOGIN_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_LAST_LOGIN_TIME%>'})" id="lastLoginTimeBegin" name="lastLoginTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_LAST_LOGIN_TIME%>'})" id="lastLoginTimeEnd" name="lastLoginTimeEnd"/>
							</td>
							<th><%=TlvAccount.ALIAS_VISIT_NUM%></th>	
							<td>
									<input type="text" name="visitNum" maxlength="10" class="span2"/>
							</td>
						</tr>	
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/lvAccountController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/lvAccountController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>