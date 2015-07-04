<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAccount" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvAccountController/add',
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
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">	
		<form id="form" method="post">		
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvAccount.ALIAS_LOGIN_NAME%></th>	
					<td>
					<input class="span2" name="loginName" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_NICK_NAME%></th>	
					<td>
					<input class="span2" name="nickName" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_PASSWORD%></th>	
					<td>
					<input class="span2" name="password" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TlvAccount.ALIAS_SEX%></th>	
					<td>
					<input class="span2" name="sex" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_BIRTHDAY%></th>	
					<td>
					<input class="span2" name="birthday" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_BIRTHDAY%>'})"  maxlength="0" class="required " />
					</td>							
					<th><%=TlvAccount.ALIAS_HEIGHT%></th>	
					<td>
					<input class="span2" name="height" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_WEIGHT%></th>	
					<td>
					<input class="span2" name="weight" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_MOBILE%></th>	
					<td>
					<input class="span2" name="mobile" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_QQ%></th>	
					<td>
					<input class="span2" name="qq" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_ADDRESS%></th>	
					<td>
					<input class="span2" name="address" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_EDUCATION%></th>	
					<td>
					<input class="span2" name="education" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_PROFESSION%></th>	
					<td>
					<input class="span2" name="profession" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_MONTH_INCOME%></th>	
					<td>
					<input class="span2" name="monthIncome" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_MARRY_STATUS%></th>	
					<td>
					<input class="span2" name="marryStatus" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_CONSTELLATION%></th>	
					<td>
					<input class="span2" name="constellation" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_PERSON_DESC%></th>	
					<td>
					<input class="span2" name="personDesc" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_CREATE_TIME%></th>	
					<td>
					<input class="span2" name="createTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_CREATE_TIME%>'})"  maxlength="0" class="required " />
					</td>							
					<th><%=TlvAccount.ALIAS_UPDATE_TIME%></th>	
					<td>
					<input class="span2" name="updateTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_UPDATE_TIME%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_AUDIT_STATUS%></th>	
					<td>
					<input class="span2" name="auditStatus" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_HEAD_IMG%></th>	
					<td>
					<input class="span2" name="headImg" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_LONGITUDE%></th>	
					<td>
					<input class="span2" name="longitude" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_LATITUDE%></th>	
					<td>
					<input class="span2" name="latitude" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_VIP_LEVEL%></th>	
					<td>
					<input class="span2" name="vipLevel" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_VIP_OPEN_TIME%></th>	
					<td>
					<input class="span2" name="vipOpenTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_VIP_OPEN_TIME%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_ONLINE%></th>	
					<td>
					<input class="span2" name="online" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_LAST_LOGIN_TIME%></th>	
					<td>
					<input class="span2" name="lastLoginTime" type="text" onclick="WdatePicker({dateFmt:'<%=TlvAccount.FORMAT_LAST_LOGIN_TIME%>'})"  maxlength="0" class="required " />
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_VISIT_NUM%></th>	
					<td>
					<input class="span2" name="visitNum" type="text" class="span2"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>