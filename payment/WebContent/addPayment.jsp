<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加付款记录</title>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">  
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">  
<script type="text/javascript" src="js/json2.js"></script> 
<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table>
		<tr>
			<td width="60px"><font size="-1">付款人</font></td>
			<td><input type="text" id="payer" size="15" style="height:20px;border:1px solid #9D9D9D"></td>
		</tr>
		<tr>
			<td><font size="-1">付款日期</font></td>
			<td><input type="text" id="payer" size="15" style="height:20px;border:1px solid #9D9D9D"></td>
		</tr>
		<tr>
			<td><font size="-1">金额</font></td>
			<td><input type="text" id="payer" size="15" style="height:20px;border:1px solid #9D9D9D"></td>
		</tr>
		<tr>
			<td><font size="-1">添加牌照</font></td>
			<td>
				<input id="license" type="text" size="15" style="height:20px;border:1px solid #9D9D9D">
				<input type="button" value="+" onclick="addLicense()">
				<input type="button" value="×" onclick="deleteLicense()">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>			
				<select id="licenses" size="8" style="width:200px"></select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="#" class="easyui-linkbutton" onclick="save()">保存</a>&nbsp&nbsp
				<a href="#" class="easyui-linkbutton" >清空</a>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function addLicense(){
			if($.trim($('#license').val()) == ''){
				alert('请先输入牌照号再做添加操作!');
				return
			}
				
			var isExisted = false;
			$('#licenses option').each(function(){  
			    if($(this).text() == $('#license').val())  {			    	
			    	isExisted = true;
			    	return;
			    } 			    		    
			 }); 
			
			if(isExisted){
				alert("此牌照号已添加!");	
				return;
			}				
			
			$('#licenses').append('<option>' + $.trim($('#license').val()) + '</option>');
		}
		
		function deleteLicense(){
			//var selectedIndex = $('#licenses').get(0).selectedIndex;
			
			$('#licenses option:selected').remove();
		}
	</script>	
</body>
</html>