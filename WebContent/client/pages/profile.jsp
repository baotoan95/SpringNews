<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content">
	<div class="column-two-third single">
	${requestScope.message }
	<form:form action="${pageContext.request.contextPath }/updateUser?${_csrf.parameterName}=${_csrf.token}" 
	method="POST" modelAttribute="user" enctype="multipart/form-data">
	<!-- For not null columns -->
	<form:hidden path="code" value="${user.code}" />
	<form:hidden path="enabled" value="${user.enabled}" />
	<form:hidden path="nonLocked" value="${user.nonLocked}" />
	<div id="profile_header">
		<div id="profile_avatar">
			<img alt="${requestScope.user.firstName } ${requestScope.user.lastName}" 
			src="<c:url value='${requestScope.user.avatarUrl }'/>">
			<form:hidden path="avatarUrl" />
			<input type="file" name="avatar"/>
			<br/>
		</div>
		<div id="profile_desc">
			<p>Email: <form:errors cssClass="error" path="email"/><form:input path="email" readonly="true"/></p>
			<p>Họ: <form:errors cssClass="error" path="firstName"/><form:input path="firstName"/></p>
			<p>Tên: <form:errors cssClass="error" path="lastName"/><form:input path="lastName"/></p>
			<p> Giới tính: 
				<form:select path="gender" class="form-control">
					<form:options items="${genders}" />
				</form:select>
			</p>
			<p>Mật khẩu: <form:errors cssClass="error" path="password"/><form:input type="password" path="password"/></p>
			<fmt:formatDate value="${user.birthDay}" var="birthDay" type="date" pattern="yyyy-MM-dd" />
			<p>Ngày sinh: <form:input path="birthDay" value="${birthDay}"/></p>
			<p>Địa chỉ: <form:input path="address"/></p>
			<p>Mô tả:</p>
			<form:textarea path="desc" cssClass="desc" rows="10"/>
			<br/>
			<input type="submit" value="Cập Nhật"/>
		</div>
	</div>
	</form:form>
	<div id="profile_content">
		<h3>Danh sách bài đã viết</h3>
		<table class="table table-condensed table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên bài viết</th>
					<th>Ngày viết</th>
					<th>Trạng thái</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${requestScope.listPost }" varStatus="status">
				<fmt:formatDate value="${post.publishedDate}" var="date"
					type="date" pattern="HH:mm:ss dd/MM/yyyy" />
				<tr>
					<td>${status.count }</td>
					<td>${post.title }</td>
					<td>${date }</td>
					<td>${post.status.name }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</div>