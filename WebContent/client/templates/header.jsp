<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Header -->
<header id="header">
<sec:authentication property="principal" var="principal"/>
	<div class="container">
		<div class="column">
			<div class="logo">
				<a href="${pageContext.request.contextPath }/index.html"><img
					src="<c:url value='/resources/client/images/logo.png'/>"
					alt="MyPassion" /></a>
			</div>

			<div id="user-panel">
				<sec:authorize access="!isAuthenticated()">
				<a href="${pageContext.request.contextPath }/login.html">Đăng nhập</a> | <a href="${pageContext.request.contextPath }/registry.html">Đăng ký</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				Xin chào <a href="${pageContext.request.contextPath }/profile.html">${principal.firstName } ${principal.lastName }</a> | <a href="${pageContext.request.contextPath }/logout">Đăng xuất</a>
				</sec:authorize>
			</div>

			<!-- Nav -->
			<nav id="nav">
				${sessionScope.main_menu }
			</nav>
			<!-- /Nav -->
		</div>
	</div>
</header>
<!-- /Header -->