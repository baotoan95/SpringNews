<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Content -->
<div class="breadcrumbs column">
	<sec:authentication property="principal" var="principal"/>
	<p>
		<a href="${pageContext.request.contextPath }/home.html">Home.</a> \\ 
		<a href="${pageContext.request.contextPath }/contact.html">Liên hệ.</a>
	</p>
</div>
<!-- Main Content -->
<div class="main-content">
	<!-- Single -->
	<div class="column-two-third single">
		<div id="map">
			<iframe src="https://www.google.com/maps/embed?pb=
			!1m18!1m12!1m3!1d3918.2594284124057!2d106.79000611425704!3d10.
			867862160472322!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752760f0341
			375%3A0x9a7a40afa5275c55!2zTMOgbmcgxJDhuqFpIEjhu41jIFRo4bunIMSQ4bupYw!5e0!3m2!1svi!2s!4v1461621151782"
			width="620" height="350" style="border:0" ></iframe>
		</div>
		<div class="outerwide">
			<h5 class="line">
				<span>Location.</span>
			</h5>
			<p>Nếu bạn có bất cứ thắc mắc hoặc khiếu nại gì, vui lòng liên hệ với chúng tôi. 
			Mọi thứ sẽ được giải đáp trong thời gian ngắn nhất, cảm ơn!</p>

			<div class="contact-info">
				<p class="man">
					<i class="icon-location"></i>Làng Đại Học Thủ Đức<br />
					QL1A, Linh Trung, Thủ Đức, Hồ Chí Minh, Việt Nam<br />
				</p>
				<p class="phone">
					<i class="icon-phone"></i> Phone: 73 443 11 23. <br />Fax: 73 443
					11 23.
				</p>
				<p class="envelop">
					<i class="icon-mail"></i>Email: <a href="#">support@springnews.com</a> <br />
					Web: <a href="${pageContext.request.contextPath }/">SpringNews.com</a>
				</p>
			</div>
		</div>
		<div class="contact-form">
			<div class="alertMessage">${requestScope.message }</div>
			<form:form action="#" method="post" id="contactForm" name="contactForm" modelAttribute="contact">
				<div class="form">
					<label>Name*</label> 
					<div class="input">
						<form:errors cssClass="error" path="name"></form:errors>
						<span class="name"></span>
						<form:input type="text" class="name" name="name" id="name" path="name" value="${user.firstName } ${user.lastName }"/>
					</div>
				</div>
				<div class="form">
					<label>Email*</label> 
					<div class="input">
						<form:errors cssClass="error" path="email"></form:errors>
						<span class="email"></span> 
						<form:input type="text" class="name" name="email" id="email" path="email" value="${user.email }"/>
					</div>
				</div>
				<div class="form">
					<label>Message*</label> 
					<form:errors cssClass="error" path="content"></form:errors>
					<form:textarea name="message" rows="10" cols="80" path="content"/>
				</div>
				<div class="form2">
					<input type="submit" class="send" value="Send Message"/>
				</div>

			</form:form>
		</div>
	</div>
	<!-- /Single -->
</div>
<!-- /Main Content -->
<!-- / Content -->