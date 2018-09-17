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
	<h1>FlowConfiguation___</h1>
	<div>
		<a href="${base}/leaveBill">请假</a><a href="${base }/logout">退出</a>
	<div>
	<div><h3>自定义流程列表</h3></div>
	<div id="listProcess"></div>
	<div><h3>业务与流程关联,不想写编辑了</h3></div>
	<div id="listForm"></div>
	
	<div>
		<p>新增自定义流程</p>
		<form id="process_form">
			<p>名称:<input name="name" ></p>
			<p>描述:<input name="content" ></p>
			
			<p>任务Json:<textarea rows="10" cols="100" name="stepArr"></textarea></p>
			<p><input type="button" value="提交" onclick="saveProcess()"></p>
		</form>
	</div>
	<div>
		<p>新增demo</p>
		<p>会签指定人员:[{"usersId":"b1,b2,b3","type":1,"seq":0}]</p>
		<p>会签指定角色:[{"roleId":"xxrole","type":1,"seq":0}]</p>
		<p>或签(可以是单人):[{"usersId":"a1,a2,a3","type":2,"seq":0}]</p>
		<p>或签:[{"roleId":"xxrole","type":2,"seq":0}]</p>
		<p>多个任务:[{"usersId":"${userId}","type":2,"seq":0},{"usersId":"9528","type":2,"seq":1},{"usersId":"9529","type":2,"seq":2}]</p>
		<p>usersId:用户id,当指定为自己时${userId}</p>
		<p>roleId:角色标识</p>
		<p>type:会签1,或签2</p>
		<p>seq:任务排序</p>
	</div>
</body>
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript">
	function saveProcess(){
		var param= $("#process_form").serialize();
		console.log(param);
		$.ajax({
			"url":"${base}/flow/deploy",
			"data":param,
			"type":"post",
			"dataType":"json",
			success:function(){
				listProcess();
			}
		});
	}
	
	function listProcess(){
		$.ajax({
			"url":"${base}/flow/listProcess",
			"type":"get",
			"dataType":"json",
			success:function(obj){
				var html='';
				var arr=obj.data;
				for (var i = 0; i < arr.length; i++) {
					var data=arr[i];
					html+='<p>'+data.id+' _ '+data.name+' _ '+data.processKey+' _ '+data.content+'</p>'
				}
				$("#listProcess").html(html);
			}
		});
	}
	
	function listForm(){
		$.ajax({
			"url":"${base}/flow/listForm",
			"type":"get",
			"dataType":"json",
			success:function(obj){
				var html='';
				var arr=obj.data;
				for (var i = 0; i < arr.length; i++) {
					var data=arr[i];
					html+='<p>'+data.id+' _ '+data.serviceName+' _ '+data.workflowId+' _ '+data.name+'</p>'
				}
				$("#listForm").html(html);
			}
		});
	}
	
	$(function(){
		listProcess();
		listForm();
	})
	
</script>

</html>