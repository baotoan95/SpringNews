<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="<c:url value='/resources/client/css/login-register.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/client/js/jquery.js'/>"></script>
</head>
<body>
	<!--/start-login-one-->
	<div class="login-01">
		<div class="one-login  hvr-float-shadow">
			<div class="one-login-head">
				<img src="<c:url value='/resources/client/images/top-lock.png'/>" alt="" />
				<h1>Đăng Nhập</h1>
			</div>
			<div style="color: red;">${requestScope.message }</div>
			<c:url value="/login" var="loginUrl"/>
			<form action="${loginUrl }" method="post">
				<ul>
					<li>
						<input type="text" class="text" placeholder="Email" autofocus="autofocus"
						name="username" tabindex="1"><i class=" icon user"></i>
					</li>
					<li>
						<input type="password" placeholder="Password" name="password" tabindex="2"><i class=" icon lock"></i>
					</li>
				</ul>
				<div class="p-container">
					<label class="checkbox">
						<input type="checkbox" name="remember-me" tabindex="3">Ghi nhớ đăng nhập
					</label>
					<h6>
						<script type="text/javascript">
							$(document).ready(function() {
								$("input[name='username']").blur(function() {
									$('#remember').attr('href', 'remember?uid=' + $(this).val());
								});
							});
						</script>
						<a id="remember" href="remember?uid=" tabindex="5" 
						title="Vui lòng nhập email của bạn để sử dụng chức năng này">Quên mật khẩu?</a>
					</h6>
					<div class="clear"></div>
				</div>
				<div class="submit">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
					<input type="submit" value="ĐĂNG NHẬP">
				</div>
				<div class="social-icons">
					<p>Hoặc đăng nhập bằng</p>
					<ul class="soc_icons2">
						<li class="pic"><a href="#"><i class="icon_4"></i></a></li>
						<li class="pic"><a href="#"><i class="icon_5"></i></a></li>
						<li class="pic"><a href="#"><i class="icon_6"></i></a></li>
						<li class="clear"></li>
					</ul>
				</div>
				<h5>
					Bạn có tài khoản chưa?<a href="${pageContext.request.contextPath }/registry.html"> Đăng ký </a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>