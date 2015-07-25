<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TlvBoostActivty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	var editor;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#goodsDetailImg', {
				width : '580px',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${pageContext.request.contextPath}/fileController/upload',
				fileManagerJson : '${pageContext.request.contextPath}/fileController/fileManage',
				allowFileManager : true
			});
		}, 1);
	 	parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/lvBoostActivtyController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				editor.sync();
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
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">	
		<form id="form" method="post" enctype="multipart/form-data">		
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_NAME%></th>	
					<td>
					<input class="span2" name="goodsName" type="text" class="span2"/>
					</td>		
					<th><%=TlvBoostActivty.ALIAS_GOODS_PRICE%></th>	
					<td>
						<input class="span2" name="goodsPrice" type="text" class="span2"/>
					</td>					
				</tr>	
				<tr>
					<th><%=TlvBoostActivty.ALIAS_GOODS_IMG%></th>	
					<td colspan="3">
						<input type="file" name="goodsImgFile">
					</td>							
				</tr>
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_ASSIST_NUM%></th>	
					<td>
						<input class="span2" name="assistNum" type="text" class="span2"/>
					</td>	
					<th>状态</th>	
					<td>
						<select name="status" style="width: 140px;">
							<option value="1">启用</option>
							<option value="2">停用</option>
						</select>
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_HOUR_OF_DAY%></th>	
					<td colspan="3">
						<input class="span2" name="hourOfDay" type="text" class="span2"/>(-1~23，-1代表全天、0代表0点到1点等)
					</td>							
				</tr>	
				<tr>	
					<th><%=TlvBoostActivty.ALIAS_GOODS_DETAIL_IMG%></th>	
					<td colspan="3">
						<textarea  name="goodsDetailImg" id="goodsDetailImg" style="height:180px;visibility:hidden;"></textarea>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>