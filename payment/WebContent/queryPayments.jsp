<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>付款记录查询</title>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">  
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">  
<script type="text/javascript" src="js/json2.js"></script> 
<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>

<body>
	<div style="padding:10px">		
		<table id="tt"></table>
		<div id="tb" style="padding:5px;height:auto">
			<form metod="post" action="">
			<table border="0" width="650px">
				<tr>
					<td align="right">付款日期范围&nbsp</td>
					<td colspan="2">
					    <span><input id="paymentDateFrom" type="text" class="easyui-datebox"></input></span> 至 
					    <span><input id="paymentDateTo" type="text" class="easyui-datebox"></input></span>
					</td>
					<td align="center">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查 询</a> 
						<!-- font size="-1" color="grey">&nbsp#表示此条件支持模糊查询</font-->
					</td>
				</tr>
			</table>
			</form>			
		</div>
	</div>	
	<div id="win2"></div>
	
	<script type="text/javascript">
		$(function(){	
			//{'total':100,'rows':[{id:'1',name:'一'},{id:'2',name:'二'}]};
			//var nullData = {"total":1,"rows":[],"footer":[{"policyNo":"Total","premium":18.00}]};
			//var json = JSON.parse(nullData);
			//data:{'total':1,'rows':[],'footer':[{'policyNo':'Total','premium':18.00}]}			
			$('#tt').datagrid({
				//width: 830,
				height: 480,
				//showFooter:true,
				pagination: true,	
				columns:[[
					{field:'payer',title:'付款人',width:150,sortable:true},
					{field:'date',title:'付款日期',width:80,sortable:true,
						formatter:function(value){
							if(value == null)	return '';
							
			            	var date = new Date(value.time);
			            	if(isNaN(date))
			            		return '';
			            	
			                var year = date.getFullYear();
			                var month = date.getMonth() + 1;
			                month = month < 10 ? '0' + month : month;
			                var date = date.getDate();
			                date = date < 10 ? '0' + date : date ;
			                //alert(date.pattern('yyyy-MM-dd hh:mm:ss.S'));
			                return year + '-' + month + '-' + date;			            	
			        	}
					},
					{field:'amount',title:'金额',width:80,sortable:true},
					{field:'license',title:'款项对应牌照号',width:600,sortable:true,
						formatter:function(value){
							var licenses = '';
							for(var i=0; i<value.length; i++){
								if(i != 0)
									licenses = licenses + ',';
								licenses = licenses + value[i].licenseNo;
							}
							return licenses;
						}
					}
				]],	                   
				toolbar:'#tb'				
			});	
			
			
			$('#inputDateFrom').datebox({  
				width:100,
				editable:false,
				formatter:dateFormatter,
				parser:dateParser
			}); 
			
			$('#inputDateTo').datebox({  
				width:100,
				editable:false,
				formatter:dateFormatter,
				parser:dateParser
			}); 			

		});
		
		function query(){
			//alert($('#dateFrom').datebox.currentText);			
			//alert($('#remarkScope').combobox('getValue'));
			//var policyNo = $('#policyNo').val();
			//$('#inputDateFrom').datebox('setValue','2013-01-01');
			if($('#paymentDateFrom').datebox('getValue') == '' || $('#paymentDateTo').datebox('getValue') == ''){
				alert('请选择日期范围!');
				return;
			}
			
			$('#tt').datagrid('options').url = "queryPayment.action";
			$('#tt').datagrid('load',{
				queryTimestamp: new Date().getTime(),
				paymentDateFrom:$('#paymentDateFrom').datebox('getValue'),
				paymentDateTo:$('#paymentDateTo').datebox('getValue')
			});
			
		}
		
		function dateFormatter(date){ 			
            var y = date.getFullYear();  
            var m = date.getMonth()+1;  
            var d = date.getDate();  
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
        }  
		
        function dateParser(s){  
            if (!s) return new Date();  
            var sss = s.split('-');  
            var y = parseInt(ss[0],10);  
            var m = parseInt(ss[1],10);  
            var d = parseInt(ss[2],10);  
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
                return new Date(y,m-1,d);  
            } else {  
                return new Date();
            }  
        }
	</script>
</body>
</html>