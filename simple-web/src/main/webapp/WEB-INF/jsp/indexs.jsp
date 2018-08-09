<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index___</h1>
	<div>
		<div id="showUser">id:<input type="text" /><input type="button" value="ajax显示一个用户" /></div>
		<div id="showUser_"></div>
	</div>
	<div>
		<div id="showPage">page:<input type="text" value="1"/>
		limit:<input type="text" value="10"/><input type="button" value="分页用户" /></div>
		<div id="showPage_"></div>
		<div id="showPage_list"></div>
	</div>
	<div>
		<form action="${base}/permission/save" method="post" >
			name:<input name="name" /><br>
			code:<input name="code" /><br>
			createTime:<input name="createTime" /><br>
			<input type="submit" value="提交"/>
		</form>
	</div>
	<div>
		<form action="${base}/common/upload" method="post" enctype= "multipart/form-data">
			file:<input type="file" name="file" />
			<input type="submit" value="提交"/>
		</form>
	</div>
	
	
</body>
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript">
	$("#showUser").find("input").eq(1).click(function(){
		var id=$("#showUser").find("input").eq(0).val();
		$.ajax({
			"url":"${base}/showUser.do?id="+id,
			"type":"GET",
			"datatype":"json",
			"success":function(obj){
				console.log(1);
				console.log(obj);
				if(obj.success){
					$("#showUser_").html('<p>'+obj.data.id+'</p>'+'<p>'+obj.data.username+'</p>'+'<p>'+obj.data.password+'</p>');
				}else{
					$("#showUser_").html('<p>'+obj.msg+'</p>');
				}
			}
		})
	});

	$("#showPage").find("input").eq(2).click(function(){
		var page=$("#showPage").find("input").eq(0).val();
		var limit=$("#showPage").find("input").eq(1).val();
		$.ajax({
			"url":"${base}/showPage.do",
			"data":"page="+page+"&limit="+limit,
			"type":"GET",
			"dataType":"json",
			"success":function(obj){
				if(obj.success){
					$("#showPage_").html('<p>当前页：'+obj.page+" 总数 ："+obj.count+'</p>');
					var html='';
					for (var i = 0; i < obj.data.length; i++) {
						html+='<p>'+obj.data[i].username+'</p>'
					}
					$("#showPage_list").html(html);
				}else{
					$("#showPage_").html('<p>'+obj.msg+'</p>');
				}
			}
		})
	});
	
</script>

</html>