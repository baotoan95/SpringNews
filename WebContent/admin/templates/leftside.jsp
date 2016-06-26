<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
<sec:authentication property="principal" var="principal"/>
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="<c:url value='${principal.avatarUrl }'/>" class="img-circle" alt="User Image" />
			</div>
			<div class="pull-left info">
				<p>${principal.firstName } ${principal.lastName }</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..." /> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">MAIN NAVIGATION</li>
			<li class="active treeview">
              	<a href="${pageContext.request.contextPath }/admin/index.html">
                	<i class="fa fa-dashboard"></i> <span>Dashboard</span>
              	</a>
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="treeview"><a href="#"> <i
					class="fa fa-calendar"></i> <span>Quản lý thể loại</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href="categories.html?page=1"><i class="fa fa-circle-o"></i>Danh sách thể loại</a></li>
					<li><a href="add-category.html"> <i class="fa fa-circle-o"></i>Thêm thể loại</a></li>
				</ul></li>
				
			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
					<span>RSS</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href="rss.html"><i class="fa fa-circle-o"></i> Danh sách RSS</a></li>
					<li><a href="addRss.html"><i class="fa fa-circle-o"></i> Đăng ký</a></li>
				</ul></li>
			</sec:authorize>
			
			<li class="treeview">
				<a href="#"> <i class="fa fa-pie-chart"></i>
					<span>Bài viết</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href="posts.html?type=writing&page=1"><i class="fa fa-circle-o"></i>Danh sách bài viết</a></li>
					<li><a href="write.html"><i class="fa fa-circle-o"></i> Viết bài</a></li>
				</ul></li>
				
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="treeview">
				<a href="contact.html?repo=inbox&p=1"> <i class="fa fa-envelope"></i> <span>Thư góp ý</span>
					<span class="label label-primary pull-right">${sessionScope.totalInbox }</span>
				</a>
			</li>
			
			<li class="treeview">
				<a href="#"> <i class="fa fa-edit"></i>
					<span>Quản lý thành viên</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
				<!-- chauphi90 -->
					<li><a href="mems-admin.html?page=1"><i class="fa fa-circle-o"></i> Quản trị</a></li>
					<li><a href="mems-writer.html?page=1"><i class="fa fa-circle-o"></i> Tác giả</a></li>
					<li><a href="mems-audient.html?page=1"><i class="fa fa-circle-o"></i> Thành viên thường</a></li>
					<li><a href="add-mem.html"><i class="fa fa-circle-o"></i> Thêm</a></li>
				</ul>
			</li>
			</sec:authorize>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>