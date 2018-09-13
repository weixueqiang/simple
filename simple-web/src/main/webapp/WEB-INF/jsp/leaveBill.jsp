<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Leave___</h1>
	<div>
		<form id="leave_form">
			<p>reason:<input name="reason"></p>
			<p>dayTime:<input name="dayTime"></p>
			<p><input type="button" value="提交" onclick="leaveSubmit()"></p>
		</form>
	</div>
	<div><input type="button" value="查看所有请假" onclick="showAllLeave()"></div>
	
	<div id="showLeaveList"></div>
	<div><input type="button" value="查看请假任务" onclick="showLeaveTask()"></div>
	<div id="showLeaveTask"></div>
	<div id="doTaskShow"></div>
</body>
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript">
	function leaveSubmit(){
		var params=$("#leave_form").serialize();
		console.log(params);
		$.ajax({
			"url":"${base}/leave/save",
			"type":"post",
			"data":params,
			"datatype":"json",
			"success":function(obj){
				var html='';
				if(obj.success){
					alert("success!")
				}else{
					alert("fail!")
				}
			}
		});
	}
	function showLeaveTask(){
		$.ajax({
			"url":"${base}/leave/listTask",
			"type":"get",
			"datatype":"json",
			"success":function(obj){
				var html='';
				if(obj.success){
					html+='<table>';
					var arr=obj.data;
					console.log(arr);
					for(var i=0;i<arr.length;i++){
						html+='<tr><td>'+arr[i].id+'</td><td><td>'+arr[i].name+'</td>'+
						'<td><input type="button" value="办理" onclick="doTask('+arr[i].id+')"></td>'
						+'</tr>'
					}
					
				html+='</table>';
				$("#showLeaveTask").html(html);
				}else{
					alert("fail!")
				}
			}
		});
	}
	
	function doTask(taskId){
		$.ajax({
			"url":"${base}/leave/getTask",
			"type":"get",
			"data":"taskId="+taskId,
			"datatype":"json",
			"success":function(obj){
				var html='';
				if(obj.success){
					html+='<p><input id="task_id" type="hidden" value="'+taskId+'"></p>'
					html+='<p><input id="leave_id" type="hidden" value="'+obj.data.id+'"></p>'
					html+='<p>'+obj.data.id+'->'+obj.data.reason+'->'+obj.data.dayTime+'</p>';
					html+='<p><input id="comment"></p>'
					html+='<p>';
				
						
					html+='<input type="button" value="同意" onclick="complateTask(true)">'
					html+='<input type="button" value="不同意" onclick="complateTask(false)">'
					
					html+='</p>'
						for(var i=0;i<obj.datas.listComment.length;i++){
							html+='<p>'+obj.datas.listComment[i].fullMessage+'</p>'
						}
					$("#doTaskShow").html(html);
				}else{
					alert("fail!")
				}
			}
		});
		
	}
	
	function complateTask(condition){
		var taskId=$("#task_id").val();
		var comment=$("#comment").val();
		var id=$("#leave_id").val();
		console.log(id+" : "+taskId+" : "+comment+" : "+condition);
		
		$.ajax({
			"url":"${base}/leave/complate",
			"type":"get",
			"data":"id="+id+"&taskId="+taskId+"&comment="+comment+"&condition="+condition,
			"datatype":"json",
			"success":function(obj){
				if(obj.success){
					$("#doTaskShow").html("<p>办理成功</p>");
				}else{
					alert("fail!")
				}
			}
		});
	}
	
	function showAllLeave(){
		$.ajax({
			"url":"${base}/leave/list",
			"type":"get",
			"datatype":"json",
			"success":function(obj){
				var html='';
				if(obj.success){
					html+='<table>';
					var arr=obj.data;
					for(var i=0;i<arr.length;i++){
						html+='<tr><td>'+arr[i].id+'</td><td><td>'+arr[i].reason+'</td>'+
						'<td>'+arr[i].dayTime+'</td><td>'+arr[i].status+'</td>'
						+'</tr>'
					}
					
				html+='</table>';
				$("#showLeaveList").html(html);
				}else{
					alert("fail!")
				}
			}
		});
	}
	
</script>

</html>