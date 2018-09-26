<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="${base}/layui/css/layui.css" >
     <link rel="stylesheet" href="${base}/css/style.css">
     <link rel="icon" href="${base}/image/code.png" > 
   
</head>
<body>

<div class="login-main">
    <header class="layui-elip">后台登录</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="username" required lay-verify="required" placeholder="用户名" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline login-btn">
            <button type="button" class="layui-btn">登录</button>
        </div>
        <hr/>
        <!--<div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>-->
        <p><a href="javascript:;" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p>
    </form>
</div>


<script src="${base}/layui/layui.js"></script>
<%-- <script src="${base}/js/jquery.js"></script> --%>
<script type="text/javascript">
    layui.use(['layer','form'], function () {
        // 操作对象
        var layer = layui.layer,form = layui.form,	
        	$ = layui.jquery;
        layer.msg('什么情况',{icon: 5});
		$(".layui-btn").click(function(){
			var data = $(".layui-form").serialize();
			$.ajax({
				url : "${base}/login",
				data : data,
				type : "Post",
				dataType : "json",
				success : function(result) {
					if (result.success) {
						window.location.href="${base}/center";
					} else {
						layer.msg(result.msg,{icon: 5});
					}
				},
			});
		});

    });
</script>
</body>
</html>