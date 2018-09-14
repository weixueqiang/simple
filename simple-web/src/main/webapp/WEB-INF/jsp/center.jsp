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
	<h1>center___</h1>
	<div>
		<a href="${base}/leaveBill">请假</a><a href="${base }/logout">退出</a>
		<a href="${base }/flow">flow</a>
	<div>
	<div id="myInfo"></div>
	</div>
		<p>shiro权限测试,数据转换测试</p>
		<div id="showUser">
			id:<input type="text" /><input type="button" value="ajax显示一个用户" />
		</div>
		<div id="showUser_"></div>
	</div>
	<div>
		<p>mybatis分页测试</p>
		<div id="showPage">
			page:<input type="text" value="1" /> limit:<input type="text"
				value="10" /><input type="button" value="分页用户" />
		</div>
		<div id="showPage_"></div>
		<div id="showPage_list"></div>
	</div>
	<div>
		<p>新增权限功能:测试VO数据校验</p>
		<form action="${base}/permission/save" method="post">
			name:<input name="name" /><br> code:<input name="code" /><br>
			createTime:<input name="createTime" /><br> <input type="submit"
				value="提交" />
		</form>
	</div>
	<div>
		<p>图片上传测试</p>
		<input type="file" name="file" id="FileUpload">
		 <a class="layui-btn layui-btn-mini" id="btn_uploadimg">上传图片</a>
	</div>
	<div id="show_img"></div>
</body>
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn_uploadimg").click(function() {
			var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
			if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
				alert("请选择图片");
				return;
			}
			var formFile = new FormData();
			formFile.append("action", "UploadVMKImagePath");
			formFile.append("file", fileObj);
			var data = formFile;
			$.ajax({
				url : "${base}/common/upload",
				data : data,
				type : "Post",
				dataType : "json",
				cache : false,//上传文件无需缓存
				processData : false,//用于对data参数进行序列化处理 这里必须false
				contentType : false, //必须
				success : function(result) {
					if(result.success){
						$("#show_img").html('<img style="width:400px;height:400px;" src="${base}'+result.data+'"/>');
					}else{
						$("#show_img").html('<p>'+result.msg+'</p>');
					}
				},
			})
		})
	})

	$.ajax({
		"url":"${base}/myInfo",
		"type":"get",
		"datatype":"json",
		"success":function(obj){
			var html='';
			if(obj.success){
				html='我的信息,Id: '+obj.data.id+' 名称: '+obj.data.username+' 加密密码: '+obj.data.password;
			}else{
				html=obj.msg;
			}
			$("#myInfo").html(html);
		}
	});
	
	$("#showUser").find("input").eq(1).click(
			function() {
				var id = $("#showUser").find("input").eq(0).val();
				$.ajax({
					"url" : "${base}/showUser.do?id=" + id,
					"type" : "GET",
					"datatype" : "json",
					"success" : function(obj) {
						console.log(1);
						console.log(obj);
						if (obj.success) {
							$("#showUser_").html(
									'<p>' + obj.data.id + '</p>' + '<p>'
											+ obj.data.username + '</p>'
											+ '<p>' + obj.data.password
											+ '</p>');
						} else {
							$("#showUser_").html('<p>' + obj.msg + '</p>');
						}
					}
				})
			});

	$("#showPage").find("input").eq(2).click(
			function() {
				var page = $("#showPage").find("input").eq(0).val();
				var limit = $("#showPage").find("input").eq(1).val();
				$.ajax({
					"url" : "${base}/showPage.do",
					"data" : "page=" + page + "&limit=" + limit,
					"type" : "GET",
					"dataType" : "json",
					"success" : function(obj) {
						if (obj.success) {
							$("#showPage_").html(
									'<p>当前页：' + obj.page + " 总数 ：" + obj.count
											+ '</p>');
							var html = '';
							for (var i = 0; i < obj.data.length; i++) {
								html += '<p>' + obj.data[i].username + '</p>'
							}
							$("#showPage_list").html(html);
						} else {
							$("#showPage_").html('<p>' + obj.msg + '</p>');
						}
					}
				})
			});
</script>

</html>