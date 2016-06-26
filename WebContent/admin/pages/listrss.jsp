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
						<h3 class="box-title">Danh sách RSS đã đăng ký</h3>
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
									url: '${pageContext.request.contextPath}/admin/deleteRSS',
									data: {
										rssId : $(this).attr('del'),
									},
									success: function(data) {
										if(data === true) {
											$(element).parent().parent().remove();
											if($('.del').length == 0) {
												$('#data').remove();
												$('.box-body').html('Chưa có kênh nào được đăng ký...');
											}
										} else {
											alert('System error...');
										}
									}
								})
							});
							
							$('.edit').click(function(e) {
								e.preventDefault();
								$('#linkRSS').val($(this).attr('edit'));
								$('#rssForm').submit();
							});
						});
						</script>
					
					<div class="box-body">
						<c:if test="${requestScope.listRSS.size() <= 0 }">
						Chưa có kênh nào được đăng ký...
						</c:if>
					
						<c:if test="${requestScope.listRSS.size() > 0 }">
						<form:form action="getrss" method="POST" id="rssForm">
						<input type="hidden" name="link" id="linkRSS" value="">
						<table class="table table-bordered" id="data">
							<tbody>
								<tr>
									<th style="width: 2%">#</th>
									<th style="width: 20%">Mô tả</th>
									<th style="width: 10%">Thể loại</th>
									<th style="width: 5%">Nguồn</th>
									<th style="width: 10%">Ngày Cập Nhật</th>
									<th style="width: 8%; text-align: center;">Cập Nhật</th>
									<th style="width: 2%; text-align: center;">Xóa</th>
								</tr>
								<c:forEach items="${requestScope.listRSS }" var="rss" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${rss.desc == null ? 'N/A' : rss.desc }</td>
									<td>${rss.category.name }</td>
									<td>${rss.generator }</td>
									<td>${rss.lastBuildDate }</td>
									<td><a href="#" edit="${rss.link }" class="fa fa-edit edit"></a></td>
									<th><a class="fa fa-trash-o del" href="#" del='${rss.rssChannelId }'></a></th>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						</form:form>
						</c:if>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.box-body -->
		</div>
	</section>
</div>