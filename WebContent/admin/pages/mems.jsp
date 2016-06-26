<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">${title }</h3>
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
									url: '${pageContext.request.contextPath}/admin/deleteUser',
									data: {
										email : $(this).attr('del'),
									},
									success: function(data) {
										if(data == "success") {
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
						<c:if test="${requestScope.data.size() <= 0 }">
						Chưa có ${role} nào !
						</c:if>
						<c:if test="${requestScope.data.size() > 0 }">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th style="width: 20px">#</th>
									<th>Họ và tên</th>
									<th style="width: 200px">Email</th>
									<th style="width: 120px">Trạng thái</th>
									<th style="width: 50px">Sửa</th>
									<th style="width: 50px">Xóa</th>
								</tr>
								<%-- chauphi90 --%>
								<c:forEach items="${requestScope.data }" var="user"
									varStatus="status">
									<tr>
									 <td>${status.index + 1}</td> 
										<%-- <td>${page}</td> --%>
										<td>${user.firstName}&nbsp;${user.lastName}</td>
										<td>${user.email }</td>
										<td>${user.enabled == true ? 'Đã kích hoạt' : 'Chưa kích hoạt' }</td>
										<td><a href="editUser?email=${user.email}" class="fa fa-edit"></a></td>
										<td><a href="#" del="${user.email}" class="fa fa-trash-o del"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:if>
					</div>
					<!-- /.box-body -->
					<c:if test="${requestScope.data.size() > 0 }">
					<div class="box-footer clearfix">${requestScope.pagination }</div>
					</c:if>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.box-body -->
		</div>
	</section>
</div>