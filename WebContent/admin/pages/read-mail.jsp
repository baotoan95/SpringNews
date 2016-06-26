<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-9">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">Nội Dung Thư</h3>
			<div class="box-tools pull-right">
				<a href="#" class="btn btn-box-tool" data-toggle="tooltip"
					title="Previous"><i class="fa fa-chevron-left"></i></a> <a href="#"
					class="btn btn-box-tool" data-toggle="tooltip" title="Next"><i
					class="fa fa-chevron-right"></i></a>
			</div>
		</div>
		<!-- /.box-header -->
		<div class="box-body no-padding">
			<div class="mailbox-read-info">
				<h3>${contact.name }</h3>
				<fmt:formatDate value="${contact.submitDate}" var="formattedDate"
					type="date" pattern="dd/MM/yyyy HH:mm:ss" />
				<h5>
					Người gửi: ${contact.email } <span class="mailbox-read-time pull-right">
						${formattedDate }</span>
				</h5>
			</div>
			<!-- /.mailbox-read-info -->
			<div class="mailbox-controls with-border text-center">
				
			</div>
			<!-- /.mailbox-controls -->
			<div class="mailbox-read-message">
				<p>${contact.content }</p>

			</div>
			<!-- /.mailbox-read-message -->
		</div>
		<!-- box-footer -->
		<div class="box-footer">
			<div class="pull-right">
				<a class="btn btn-default" href="write-mail.html?mail=${contact.contactId }">
					<i class="fa fa-reply"></i> Reply
				</a>
<!-- 				<button class="btn btn-default"> -->
<!-- 					<i class="fa fa-share"></i> Forward -->
<!-- 				</button> -->
			</div>
			<a class="btn btn-default" href="delete-a-mail.html?mail=${contact.contactId }">
				<i class="fa fa-trash-o"></i> Delete
			</a>
			<button class="btn btn-default" onclick="window.print();">
				<i class="fa fa-print"></i> Print
			</button>
		</div>
		<!-- /.box-footer -->
	</div>
	<!-- /. box -->
</div>