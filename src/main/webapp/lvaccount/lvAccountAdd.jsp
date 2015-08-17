<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvAccount" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>
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
				
				var province = $("#province").val();
				var city = $("#city").val();
				var district = $("#district").val();
				if(city != "") {
					province += "_" + city;
				}
				if(district != "") {
					province += "_" + district;
				}
				$("#address").val(province);
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
		
		drawArea($("#province"), 0); // 初始化省份

		$("#province").bind("change", changeProvince); // 改变省份
		$("#city").bind("change", changeCity); // 改变城市
		
		function ProcessFile() {
			var file = document.getElementById('headImg').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function ( event ) {
					var txt = event.target.result;
					$('.img-preview').attr('src',txt);
				};
			}
		    reader.readAsDataURL(file);
		}
		$(document).delegate('#headImg','change',function () {
			ProcessFile();
		});
	});
	
	// 获取数据填充省市区
	function drawArea(obj, parentCode) {
		$.post('${pageContext.request.contextPath}/lvAreaController/getByParentCode', {
			parentCode : parentCode
		}, function(result) {
			if (result.success) {
				for(var i=0; i<result.obj.length; i++) {
					var $opt = $("<option value=\""+result.obj[i].code+"\">"+result.obj[i].name+"</option>");
					$(obj).append($opt);
       			}
			}
		}, 'JSON');
	}
	
	// 改变省份
	function changeProvince() {
		$("#city option:not(:first)").remove();
		$("#district option:not(:first)").remove();
		var province_id = $("#province").val();
		if(province_id != '') {
			drawArea($("#city"), province_id);
		}
	}

	// 改变城市
	function changeCity() {
		$("#district option:not(:first)").remove();
		var city_id = $("#city").val();
		if(city_id != '') {
			drawArea($("#district"), city_id);
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">	
		<form id="form" method="post" enctype="multipart/form-data">		
				<input type="hidden" name="address" id="address"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th width="12%"><%=TlvAccount.ALIAS_NICK_NAME%></th>	
					<td width="38%">
						<input class="span2" name="nickName" type="text" class="span2"/>
					</td>							
					<th width="12%"><%=TlvAccount.ALIAS_SEX%></th>	
					<td width="38%">
						<jb:select dataType="SX" name="sex" value="SX02"></jb:select>
					</td>						
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_HEAD_IMG%></th>	
					<td colspan="3">
						<img class="img-preview" src="" width="50" height="50"/> 
						<input type="file" id="headImg" name="headImgFile">
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_AUDIT_STATUS%></th>	
					<td>
						<jb:select dataType="AD" name="auditStatus" value="AD01"></jb:select>
					</td>
					<th><%=TlvAccount.ALIAS_BIRTHDAY%></th>	
					<td>
						<input class="span2" name="birthdayStr" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  maxlength="0" class="required " />
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_ADDRESS%></th>	
					<td colspan="3">
						<select id="province" style="width: 140px;">
		                  	<option value="">请选择省</option>
		                </select>
						<select id="city" style="width: 140px;">
		                  	<option value="">请选择市</option>
		                </select>
						<select id="district" style="width: 140px;">
		                  	<option value="">请选择区</option>
		                </select>
					</td>						
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_HEIGHT%></th>	
					<td>
						<input class="span2" name="height" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_WEIGHT%></th>	
					<td>
						<input class="span2" name="weight" type="text" class="span2"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_PERSON_DESC%></th>	
					<td colspan="3">
						<textarea style="width: 500px;" name="personDesc"></textarea>
					</td>							
				</tr>
				<tr>	
					<th><%=TlvAccount.ALIAS_EDUCATION%></th>	
					<td>
						<jb:select dataType="ED" name="education" value="ED04"></jb:select>
					</td>		
					<th><%=TlvAccount.ALIAS_PROFESSION%></th>	
					<td>
						<jb:select dataType="PF" name="profession" value="PF01"></jb:select>
					</td>						
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_MONTH_INCOME%></th>	
					<td>
						<jb:select dataType="MI" name="monthIncome" value="MI01"></jb:select>
					</td>		
					<th><%=TlvAccount.ALIAS_MARRY_STATUS%></th>	
					<td>
						<jb:select dataType="MS" name="marryStatus" value="MS01"></jb:select>
					</td>						
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_CONSTELLATION%></th>	
					<td>
						<jb:select dataType="CL" name="constellation" value="CL06"></jb:select>
					</td>
					<th><%=TlvAccount.ALIAS_LAST_LOGIN_AREA%></th>	
					<td >
						<input class="span2" name="lastLoginArea" type="text" class="span2"/>
					</td>								
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_LONGITUDE%></th>	
					<td>
						<input class="span2" name="longitude" type="text" class="span2"/>
					</td>
					<th><%=TlvAccount.ALIAS_LATITUDE%></th>	
					<td >
						<input class="span2" name="latitude" type="text" class="span2"/>
					</td>								
				</tr>	
				<tr>	
					<th><%=TlvAccount.ALIAS_MOBILE%></th>	
					<td>
						<input class="span2" name="mobile" type="text" class="span2"/>
					</td>							
					<th><%=TlvAccount.ALIAS_QQ%></th>	
					<td>
						<input class="span2" name="qq" type="text" class="span2"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>