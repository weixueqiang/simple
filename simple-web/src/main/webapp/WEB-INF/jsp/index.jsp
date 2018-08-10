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
	<h1>login___</h1>


	<div>
		<form id="login_form">
			name:<input name="username" /><br> password:<input
				name="password" /><br> <br> <input type="button"
				id="login" value="提交" />
		</form>
	</div>
	<a href="${base }/logout">退出</a>
</body>
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			var data = $("#login_form").serialize();
			$.ajax({
				url : "${base}/login",
				data : data,
				type : "Post",
				dataType : "json",
				success : function(result) {
					if (result.success) {
						console.log(1);
						window.location.href="${base}/center";
					} else {
						alert(result.msg);
					}
				},
			})
		})
	})
</script>

</html>