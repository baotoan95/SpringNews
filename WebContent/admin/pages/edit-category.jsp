<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">${not empty requestScope.message ? requestScope.message : 'Thêm thể loại' }</h3>
					</div>

					<!-- form start -->
					<form:form modelAttribute="category"
						action="${requestScope.action }?${_csrf.parameterName}=${_csrf.token}"
						role="form" method="POST" enctype="multipart/form-data">
						<form:hidden path="cateId"/>
						<div class="box-body">
							<div class="form-group">
								<label for="name">Tên thể loại</label><br/>
								<form:errors path="name" cssClass="error"></form:errors>
								<form:input type="text" class="form-control" id="name" path="name" />
							</div>
							<div class="form-group">
								<label for="parent">Parent</label>
								<form:select class="form-control" id="parent" name="parent"
									path="parent">
									<form:option value="0">Mới</form:option>
									<c:forEach items="${requestScope.categories }" var="cate">
										<form:option value="${cate.cateId }">${cate.name }</form:option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group">
								<label for="showIndex">Hiển thị trang chủ: </label>
								<form:radiobutton name="showIndex" path="show" value="1" />
								Có
								<form:radiobutton path="show" name="showIndex" value="0" />
								Không
							</div>
							<div class="form-group">
								<label for="isMenu">Làm menu: </label>
								<form:radiobutton name="isMenu" path="menu" value="1" />
								Có
								<form:radiobutton name="isMenu" path="menu" value="0" />
								Không
							</div>
							<div class="form-group">
								<label>Mô tả</label>
								<form:textarea class="form-control" rows="3"
									placeholder="Enter ..." path="desc"></form:textarea>
							</div>
							<div class="form-group">
								<label for="avatar">Avatar</label> <input type="file"
									id="avatar" name="avatar">
								<div id="showAvatar">
									<img alt="Avatar"
										src='<c:url value="${category.avatarUrl }" />'>
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