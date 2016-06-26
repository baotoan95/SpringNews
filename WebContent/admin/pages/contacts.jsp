<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-9">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${repoTitle}</h3>
			
			<!-- /.box-tools -->
		</div>
		<!-- /.box-header -->

		<script type="text/javascript">
			$(document).ready(function() {
				$(".del").click(function(e) {
					e.preventDefault();
					var contactArr = [];
					$('input[name="contactchbx"]:checked').each(function() {
						contactArr.push(this.value);
					});
					if(contactArr.length > 0) {
						if(!confirm('Are you sure to delete?')) {
							return;
						}
					} else {return;}
					$.ajax({						
						type: 'GET',
						url: '${pageContext.request.contextPath}/admin/delete-mail.html',
						data: {
							mails: contactArr
						},
						success: function(data) {
							if(data === 'success') {
								$('input[name="contactchbx"]:checked').parents("tr").remove();
							} else {
								alert('System error...');
							}
						}
					});
				});
			});
		</script>

		<div class="box-body no-padding">
			<c:if test="${requestScope.contacts.size() <= 0 }">
					<p style="padding: 10px;">Không có mail nào trong mục này !</p>
			</c:if>
		
		<c:if test="${requestScope.contacts.size() > 0 }">
			<div class="mailbox-controls">
				<!-- Check all button -->
				<button class="btn btn-default btn-sm checkbox-toggle">
					<i class="fa fa-square-o"></i>
				</button>
				
					<button class="btn btn-default btn-sm del">
						<i class="fa fa-trash-o"></i>
					</button>
				
				<!-- /.btn-group -->
				<button class="btn btn-default btn-sm" onclick="location.reload(true);">
					<i class="fa fa-refresh"></i>
				</button>
				<div class="pull-right">
					${requestScope.pagination }
				</div>
				<!-- /.pull-right -->
			</div>
			<div class="table-responsive mailbox-messages">
				<table class="table table-hover table-striped">
					<tbody>

						<c:forEach items="${requestScope.contacts }" var="ct">
						
							<c:set var="status" value="${ct.status.sttId}"/>
							<c:set var="fontWeight" value="${(status==3 || status==10) ? 'bold' : 'normal'}" />
							<tr style="font-weight: ${fontWeight}">
								<td class="mailbox-checkbox" style="width: 30px">
									<div class="icheckbox_flat-blue" aria-checked="false"
										aria-disabled="false" style="position: relative;">
										<input type="checkbox" style="position: absolute; opacity: 0;"
											name="contactchbx" value="${ct.contactId }">
									</div>
								</td>
								<td  class="mailbox-star" style="max-width: 10px"><a href="#"><i
										class="fa fa-star-o text-yellow"></i></a></td>
								<td class="mailbox-name" style="max-width: 100px">${ct.email }</td>
								<!-- Head of content ...  ... -->
								<c:set value="${ct.content}" var="content" />
								<td  class="mailbox-subject" style="max-width: 300px;"><a
									href="read-mail.html?mail=${ct.contactId}">${ct.name } -
										${ct.content }
								</a></td>

								<td class="mailbox-attachment"></td>
								<fmt:formatDate value="${ct.submitDate}" var="formattedDate"
									type="date" pattern="dd-MM-yyyy" />
								<td class="mailbox-date" style="width: 100px">${formattedDate }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<!-- /.table -->
			</div>
			</c:if>
			<!-- /.mail-box-messages -->
		</div>
		<!-- /.box-body -->
		<c:if test="${requestScope.contacts.size() > 0 }">
		<div class="box-footer no-padding">
			<div class="mailbox-controls">
				<!-- Check all button -->
				<button class="btn btn-default btn-sm checkbox-toggle">
					<i class="fa fa-square-o"></i>
				</button>
					<button class="btn btn-default btn-sm del">
						<i class="fa fa-trash-o"></i>
					</button>
				<!-- /.btn-group -->
				<button class="btn btn-default btn-sm" onclick="location.reload(true);">
					<i class="fa fa-refresh"></i>
				</button>
				<div class="pull-right">
					${requestScope.pagination }
				</div>
				<!-- /.pull-right -->
			</div>
		</div>
		</c:if>
		
	</div>
	<!-- /. box -->
</div>
<!-- /.col -->