<%@page import="com.news.cd.constants.PostStatus"%>
<%@page import="com.news.cd.entities.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Info user -->
	<sec:authentication property="principal.authorities" var="authorities" />
	
	<section class="content">
		<form:form method="POST" action="${requestScope.action }?${_csrf.parameterName}=${_csrf.token}" modelAttribute="post" enctype="multipart/form-data">
			<form:hidden path="postId"/>
			
			<div class="row">
				<div class="col-md-6">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">${empty requestScope.message ? 'Thông Tin Bài Viết' : requestScope.message }</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<div class="box-body">
							<div class="form-group">
								<label for="title">Tiêu đề</label>
								<br/><form:errors path="title" cssClass="error"></form:errors>
								<form:input class="form-control" id="title" path="title"/>
							</div>
							<div class="form-group">
								<label for="category">Thể loại</label>
								<form:select class="form-control" id="category" path="category">
									<option value="0">Chọn thể loại</option>
									<c:forEach items="${requestScope.categories }" var="cate">
									<option ${cate.cateId == post.category.cateId ? 'selected' : 'false' } value="${cate.cateId }">${cate.name }</option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group">
								<label for="desc">Mô tả</label>
								<br/><form:errors path="desc" cssClass="error"></form:errors>
								<form:textarea class="form-control" rows="3" path="desc"></form:textarea>
							</div>
							<!-- Nếu postType là own thì enable -->
							<c:if test="${post.type.postTypeId == 2 }">
							<div class="form-group">
								<label for="avatar">Avatar</label> 
								<input id="avatar" type="file" name="avatar" />
								<p class="help-block">Hình đại diện cho bài viết</p>
								<div id="showAvatar">
									<img alt="Banner" src="<c:url value='${post.avatarUrl }' />">
								</div>
							</div>
							<% Post post = (Post) request.getAttribute("post"); %>
							<div class="checkbox">
								Trạng thái
								<label for="check">
									<select name="statusId">
									<sec:authorize access="hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')">
										<option <%= post.getStatus().getSttId() == PostStatus.WRITING ? "selected" : "" %> value="<%= PostStatus.WRITING %>">Đang soạn</option>
										<option <%= post.getStatus().getSttId() == PostStatus.UNAPPROVED ? "selected" : "" %> value="<%= PostStatus.UNAPPROVED %>">Chưa duyệt</option>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<option <%= post.getStatus().getSttId() == PostStatus.APPROVED ? "selected" : "" %> value="<%= PostStatus.APPROVED %>">Đã duyệt</option>
									</sec:authorize>
									</select>
								</label>
							</div>
							</c:if>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">${requestScope.action == 'updatePost' ? 'Update' : 'Submit' }</button>
						</div>
					</div>
					<!-- /.box -->
				</div>
				<!-- /.box-body -->
			</div>
			<c:if test="${requestScope.post.type.postTypeId == 2 or empty requestScope.post.type }">
			<div class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">Nội Dung</h3>
							<!-- tools box -->
							<div class="pull-right box-tools">
								<button class="btn btn-info btn-sm" data-widget="collapse"
									data-toggle="tooltip" title="Collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button class="btn btn-info btn-sm" data-widget="remove" 
								data-toggle="tooltip" title="Remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
							<!-- /. tools -->
						</div>
						<!-- /.box-header -->
						<div class="box-body pad">
							<br/><form:errors path="content" cssClass="error"></form:errors>
							<form:textarea id="editor" name="editor" path="content" cols="80" rows="10"></form:textarea>
							<script type="text/javascript">
								CKEDITOR.replace("editor");
							</script>
						</div>
					</div>
					<!-- /.box -->
				</div>
			</div>
			</c:if>
		</form:form>
	</section>
</div>