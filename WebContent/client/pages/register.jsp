<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet"
	href="<c:url value='/resources/client/css/login-register.css'/>" />
<style type="text/css">
	.error {
		color: red;
	}
</style>
</head>
<body>
	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<h2>Đăng Ký</h2>
			</div>
			<div style="color: red;">${requestScope.error }<br />${requestScope.message }</div>
			<form:form action="${pageContext.request.contextPath }/registry.html"
				method="post" modelAttribute="user">
				<ul>
					<form:errors cssClass="error" path="email"></form:errors>
					<li><form:input type="text" class="text" placeholder="E-mail"
							tabindex="1" name="email" path="email"></form:input> <i
						class=" icon2 user2"></i></li>

					<form:errors cssClass="error" path="password"></form:errors>
					<li><form:input type="password" placeholder="Password"
							tabindex="2" name="password" path="password"></form:input> <i
						class=" icon2 lock2"></i></li>
						
					<li><input type="password" placeholder="RE-Password"
						name="passConfirm" tabindex="3" /> <i class=" icon2 lock2"></i></li>
						
					<form:errors cssClass="error" path="firstName"></form:errors>
					<li><form:input type="text" class="text"
							placeholder="First name" tabindex="4" name="firstName"
							path="firstName"></form:input> <i class=" icon2 mail"></i></li>
							
					<form:errors cssClass="error" path="lastName"></form:errors>
					<li><form:input type="text" class="text"
							placeholder="Last name" name="lastName" path="lastName"
							tabindex="5"></form:input> <i class=" icon2 mail"></i></li>
					
					<li><input type="date" class="text"
						placeholder="Birth of date" name="birthDay" tabindex="6" /> <i
						class=" icon2 mail"></i></li>
						
					<li><select name="gender" tabindex="7">
							<option value="M">Male</option>
							<option value="F">Female</option>
					</select> <i class="icon2 mail"></i></li>
					
					<li><form:input type="text" class="text" placeholder="Address"
							name="address" path="address" tabindex="8"></form:input> <i
						class=" icon2 mail"></i></li>
				</ul>
				<div class="p-container">
					<label class="checkbox two"> <input type="checkbox"
						name="checkbox">Tôi đồng ý với <a href="#">Điều khoản
							sử dụng</a>
					</label>
				</div>
				<div class="submit two">
					<input type="submit" value="ĐĂNG KÝ">
				</div>
				<h5>
					Nếu đã có tài khoản?<a
						href="${pageContext.request.contextPath }/login.html"> Đăng
						nhập ở đây</a>
				</h5>
			</form:form>
		</div>
	</div>

</body>
</html>