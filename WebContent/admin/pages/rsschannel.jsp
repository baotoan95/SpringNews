<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content">
		<form:form method="POST" action="${requestScope.action }">
			<div class="row">
				<div class="col-md-6">
					<c:if test="${empty sessionScope.rssChannel}">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">${empty requestScope.message ? 'Nhập RSS url' : requestScope.message}</h3>
							</div>
							<div class="box-body">
								<div class="form-group">
									<input type="text" name="link" class="form-control" id="rss" value="${rssChannel.link }" placeholder="RSS URL" />
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Lấy Thông Tin Kênh">
							</div>
						</div>
					</c:if>

					<c:if test="${not empty sessionScope.rssChannel}">
						<!-- /.box-header form start -->
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">${empty requestScope.message ? 'Thông tin kênh' : requestScope.message}</h3>
							</div>
							<div class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										<label class="col-sm-3">Tên kênh</label>
										<div class="col-sm-9">${rssChannel.title }</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3">Ngày xuất bản</label>
										<div class="col-sm-9">${rssChannel.pubDate }</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3">Nguồn</label>
										<div class="col-sm-9"><input type="text" name="generator" value="${rssChannel.generator }"/></div>
									</div>
									<div class="form-group">
										<label class="col-sm-3">Thể loại</label>
										<div class="col-sm-9">
											<select name="cate">
											<c:forEach items="${requestScope.categories }" var="cate">
												<option ${rssChannel.category.cateId == cate.cateId ? 'selected' : '' } value="${cate.cateId }">${cate.name }</option>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="box-footer">
									<input type="submit" class="btn btn-primary" value="${requestScope.action == 'updateRSS' ? 'Cập Nhật' : 'Đăng Ký'}">
								</div>
							</div>
						</div>
					</c:if>
				</div>
				<!-- /.box-body -->

					<div class="col-md-6">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">Danh sách bài viết mới cập nhật</h3>
							</div>
							<div class="box-body">
								<c:if test="${empty sessionScope.rssChannel }">
								Chưa cập nhật...
								</c:if>
								<c:if test="${sessionScope.rssChannel.items.size() == 0}">
								Chưa có bài viết nào mới từ kênh: ${sessionScope.rssChannel.title }
								</c:if>
								<c:if test="${sessionScope.rssChannel.items.size() > 0}">
								<script type="text/javascript">
									$(document).ready(function() {
										$('.checkAll').click(function() {
											$('.item').prop('checked', true);
										});
									});
								</script>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<th style="width: 10px">No</th>
											<th>Title</th>
											<th><a href="#" class="checkAll">All</a></th>
										</tr>
										<c:forEach var="rss" items="${sessionScope.rssChannel.items }" varStatus="item">
											<tr>
												<td>${item.count }</td>
												<td>${rss.title }</td>
												<td>
													<input type="checkbox" class="item" name="listItems" value="${item.index }">
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:if>
							</div>
						</div>
					</div>
			</div>
		</form:form>
	</section>
</div>