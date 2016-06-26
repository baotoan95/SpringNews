<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="main-header">
<sec:authentication property="principal" var="principal"/>
	<!-- Logo -->
	<a href="${pageContext.request.contextPath }/admin/" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>Admin</b>LTE</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="<c:url value='${principal.avatarUrl}'/>"
						class="user-image" alt="User Image" /> 
						<span class="hidden-xs">${principal.firstName } ${principal.lastName }</span>
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img
							src="<c:url value='${principal.avatarUrl}'/>"
							class="img-circle" alt="User Image" />
							<p>${principal.firstName } ${principal.lastName }</p>
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="editUser?email=${principal.username }" class="btn btn-default btn-flat">Profile</a>
							</div>
							<div class="pull-right">
								<c:url value="logout" var="logoutUrl"/>
								<a href="<c:url value="/logout" />" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul></li>
				<li style="width: 20px;"></li>
			</ul>
		</div>

	</nav>
</header>