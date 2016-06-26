<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Danh sách thể loại</h3>
					</div>
					<c:if test="${requestScope.data.size() <= 0 }">
					Chưa có thể loại nào được tạo...
					</c:if>
					<c:if test="${requestScope.data.size() > 0 }">
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
								url: '${pageContext.request.contextPath}/admin/deleteCategory',
								data: {
									cateId : $(this).attr('del'),
								},
								success: function(data) {
									if(data === 'success') {
										$(element).parent().parent().remove();
									} else {
										alert('System error...');
									}
								}
							})
						});
					});
					</script>
					
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th style="width: 20px">#</th>
									<th>Tên Thể Loại</th>
									<th>Mô Tả</th>
									<th style="width: 200px">Hiển Thị Ở Trang Chủ</th>
									<th style="width: 100px">Là Menu</th>
									<th style="width: 50px">Sửa</th>
									<th style="width: 50px">Xóa</th>
								</tr>
								<c:forEach items="${requestScope.data }" var="cate">
								<tr>
									<td>${cate.cateId }</td>
									<td>${cate.name }</td>
									<td>${cate.desc == null ? 'N/A' : cate.desc }</td>
									<td>${cate.show }</td>
									<td>${cate.menu }</td>
									<td><a href="editCategory?cate=${cate.cateId }" class="fa fa-edit"></a></td>
									<th><a class="fa fa-trash-o del" href="#" del='${cate.cateId }'></a></th>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
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