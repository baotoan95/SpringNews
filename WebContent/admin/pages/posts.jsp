<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Danh sách bài viết</h3>
						<ul class="pagination pagination-sm no-margin pull-right">
	                      <li <% if(request.getParameter("type").toString().equals("writing")) {out.print("class='active'");} %>>
	                      <a href="posts.html?type=writing&page=1">Đang soạn</a></li>
	                      <li <% if(request.getParameter("type").toString().equals("unapproved")) {out.print("class='active'");} %>>
	                      <a href="posts.html?type=unapproved&page=1">Chưa duyệt</a></li>
	                      <li <% if(request.getParameter("type").toString().equals("approved")) {out.print("class='active'");} %>>
	                      <a href="posts.html?type=approved&page=1">Đã duyệt</a></li>
	                    </ul>
					</div>
					<!-- /.box-header -->
					
					<script type="text/javascript">
						$(document).ready(function() {
							$('.del').click(function(e) {
								e.preventDefault();
								if(!confirm('Are you sure to delete?')) {
									return;
								}
								var element = $(this);
								$.ajax({
									type: 'GET',
									url: '${pageContext.request.contextPath}/admin/deletePost',
									data: {
										postId : $(this).attr('del'),
									},
									success: function(data) {
										if(data === true) {
											$(element).parent().parent().remove();
											if($('.del').length == 0) {
												$('#data').remove();
												$('.box-body').html('Danh sách trống...');
											}
										} else {
											alert('System error...');
										}
									}
								})
							});
						});
						</script>
					
					<div class="box-body">
						<c:if test="${not empty requestScope.posts }">
						<table class="table table-bordered" id="data">
							<tbody>
								<tr>
									<th style="width: 20px">#</th>
									<th>Tiêu Đề</th>
									<th>Thể Loại</th>
									<th style="width: 100px">View</th>
									<th style="width: 100px">Vote</th>
									<th style="width: 100px">Loại</th>
									<sec:authorize access="hasRole('ROLE_WRITER') and !hasRole('ROLE_ADMIN')">
									<c:if test="${posts.get(0).status.sttId == 4 or posts.get(0).status.sttId == 5}">
									<th style="width: 50px">Sửa</th>
									<th style="width: 50px">Xóa</th>
									</c:if>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th style="width: 50px">Sửa</th>
									<th style="width: 50px">Xóa</th>
									</sec:authorize>
								</tr>
								<c:forEach items="${requestScope.posts }" var="post" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${post.title }</td>
									<td>${post.category.name }</td>
									<td>${post.views }</td>
									<td>${post.votes }</td>
									<td>${post.type.name }</td>
									<sec:authorize access="hasRole('ROLE_WRITER') and !hasRole('ROLE_ADMIN')">
									<c:if test="${posts.get(0).status.sttId == 4 or posts.get(0).status.sttId == 5}">
									<td><a href="editView?pid=${post.postId }" class="fa fa-edit"></a></td>
									<th><a class="fa fa-trash-o del" del="${post.postId }"></a></th>
									</c:if>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
									<td><a href="editView?pid=${post.postId }" class="fa fa-edit"></a></td>
									<th><a class="fa fa-trash-o del" del="${post.postId }"></a></th>
									</sec:authorize>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:if>
						<c:if test="${empty requestScope.posts }">
							Không có bài viết nào...
						</c:if>
					</div>
					<!-- /.box-body -->
					<c:if test="${not empty requestScope.posts }">
					<div class="box-footer clearfix">
						${requestScope.pagination }
					</div>
					</c:if>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.box-body -->
		</div>
	</section>
</div>