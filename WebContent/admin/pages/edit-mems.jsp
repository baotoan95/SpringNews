<%@page import="com.news.cd.entities.UserRole"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="com.news.cd.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- chauphi90 -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#avatar').change(function() {
			var imgPath = URL.createObjectURL(event.target.files[0]);
			$("#avatarPreview").fadeIn("fast").attr('src', imgPath);
		});
	});
</script>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">${title} ${requestScope.message }</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form:form modelAttribute="user" role="form"
						action="${requestScope.action }?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
						<!-- For not null columns -->
						<form:hidden path="code" value="${user.code}" />
						<form:hidden path="enabled" value="${user.enabled}" />
						<form:hidden path="nonLocked" value="${user.nonLocked}" />

						<div class="box-body">
							<div class="form-group">
								<label for="name">Email</label>
								<form:errors path="email" cssClass="error"></form:errors>
								<form:input readonly='${fn:length(user.email) > 0 }' class="form-control" id="email" path="email" />
							</div>
							<div class="form-group">
								<label for="password">Mật khẩu</label>
								<form:errors path="password" cssClass="error"></form:errors>
								<form:input type="password" class="form-control" id="password"
									path="password" />
							</div>
							<div class="form-group">
								<label for="firstName">Họ</label>
								<form:errors path="firstName" cssClass="error"></form:errors>
								<form:input type="text" class="form-control" id="firstName"
									placeholder="First name" path="firstName" />
							</div>
							<div class="form-group">
								<label for="lastName">Tên</label>
								<form:errors path="lastName" cssClass="error"></form:errors>
								<form:input type="text" class="form-control" id="lastName"
									placeholder="Last name" path="lastName" />
							</div>
							<div class="form-group">
								<label for="birth">Ngày sinh</label>
								<fmt:formatDate value="${user.birthDay}" var="formattedDate"
									type="date" pattern="yyyy-MM-dd" />
								<form:input type="date" class="form-control" id="birth"
									value="${formattedDate}" path="birthDay" />
							</div>
							<div class="form-group">
								<label for="gender">Giới tính</label>
								<form:select path="gender" class="form-control">
									<form:options items="${genders}" />
								</form:select>
							</div>
							<div class="form-group">
								<label for="address">Địa chỉ</label> 
								<form:input path="address" class="form-control" id="address" />
							</div>
							<div class="form-group">
								<label for="desc">Mô tả</label> 
								<form:textarea path="desc" class="form-control" id="desc" />
							</div>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div class="form-group">
								<label>Vai trò</label> 
								 <select multiple="multiple" class="form-control" name="roleList">
									<% 
									User user = (User) request.getAttribute("user");
									List<UserRole> roles = new ArrayList<UserRole>(user.getRoles());
 									%>
									<option value="1" <%= roles.contains(new UserRole(user, 1)) ? "selected" : "" %>>Admin</option>
									<option value="2" <%= roles.contains(new UserRole(user, 2)) ? "selected" : "" %>>Writer</option>
									<option value="3" <%= roles.contains(new UserRole(user, 3)) ? "selected" : "" %>>Audient</option> 
								</select> 
							</div>
							</sec:authorize>
							<div class="form-group">
								<label for="avatar">Avatar</label>
								<form:hidden path="avatarUrl" />
								<input type="file" id="avatar" name="avatar">
								<div id="showAvatar">
									<img id="avatarPreview" alt="(avatar)"
										src="<c:url value='${user.avatarUrl}' />" />
								</div>
							</div>
						</div>
						<!-- /.box-body -->

						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form:form>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.box-body -->
		</div>
	</section>
</div>