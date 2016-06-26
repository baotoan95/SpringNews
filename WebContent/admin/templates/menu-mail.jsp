<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-md-3">
	<div class="box box-solid">
		<div class="box-header with-border">
			<h3 class="box-title">Folders</h3>
			<div class="box-tools">
				<button class="btn btn-box-tool" data-widget="collapse">
					<i class="fa fa-minus"></i>
				</button>
			</div>
		</div>
		<div class="box-body no-padding">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="contact.html?repo=inbox&p=1"><i class="fa fa-inbox"></i>Hộp thư đến<span class="label label-warning pull-right">${sessionScope.totalInbox }</span></a></li>
				<li><a href="contact.html?repo=sent&p=1"><i class="fa fa-envelope-o"></i> Thư đã gửi</a></li>
				<li><a href="contact.html?repo=draft&p=1"><i class="fa fa-file-text-o"></i> Thư nháp</a></li>
				<li><a href="contact.html?repo=trash&p=1"><i class="fa fa-trash-o"></i> Thư đã xoá</a></li>
			</ul>
		</div>
		<!-- /.box-body -->
	</div>
</div>