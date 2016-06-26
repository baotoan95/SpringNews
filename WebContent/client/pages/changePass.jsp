<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="<c:url value='/resources/client/css/login-register.css'/>" />
</head>
<body>
	<!--/start-login-one-->
	<div class="login-01">
		<div class="one-login  hvr-float-shadow">
			<div class="one-login-head">
				<img src="<c:url value='/resources/client/images/top-lock.png'/>" alt="" />
				<h1>Đổi Mật Khẩu</h1>
			</div>
			<div style="color: red;">${requestScope.message }</div>
			<form:form action="changePass" method="post" modelAttribute="changePassForm">
				<ul>
					<li>
						<form:password placeholder="Mật khẩu" path="password"
						name="password" tabindex="1"></form:password>
						<a href="#" class=" icon user"></a></li>
					<li>
						<form:password placeholder="Xác nhận mật khẩu" path="passConfirm"
						name="passConfirm" tabindex="2"></form:password>
						<a href="#" class=" icon lock"></a></li>
				</ul>
				<div class="submit">
					<form:hidden path="email"/>
					<form:hidden path="code"/>
					<input type="submit" onclick="myFunction()" value="XÁC NHẬN">
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>