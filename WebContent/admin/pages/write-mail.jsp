<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-9">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">Soạn Thư Mới</h3>
			
		</div>
		<!-- /.box-header -->
		<form:form action="send-mail.html" method="post" modelAttribute="contact" id="mail-form" >
			<div class="box-body">
			<c:if test="${not empty message}">
			<p class="message">(${message })</p>
			</c:if>
				<div class="form-group">
					<form:input class="form-control" placeholder="To:"
						path="email" />
				</div>
				<div class="form-group">
					<form:input class="form-control" placeholder="Subject:" value="Reply: ${contact.name }" path="name" />
				</div>
				<div class="form-group">
					<form:textarea id="compose-textarea" class="form-control" style="height: 300px;" path="content" />
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<div class="pull-right">
					<button class="btn btn-default draft">
						<i class="fa fa-pencil"></i> Nháp
					</button>
					<button type="submit" class="btn btn-primary">
						<i class="fa fa-envelope-o"></i> Gửi
					</button>
				</div>
				<button class="btn btn-default" onclick="window.history.back()">
					<i class="fa fa-times"></i> Huỷ
				</button>
				
			</div>
			<!-- /.box-footer -->
		</form:form>
	</div>
	<!-- /. box -->
</div>

<script type="text/javascript">
			$(document).ready(function() {
				$(".draft").click(function(e) {
					e.preventDefault();
					$("#mail-form").attr("action", "draft-mail.html");
					$("#mail-form").submit();
				});
				
				/* hide message in top */
				$('.form-control').focus(function(){
					$('.message').remove();
				}); 
				
			});
		</script>