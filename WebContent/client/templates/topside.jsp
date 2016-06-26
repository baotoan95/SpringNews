<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Slider -->
<section id="slider">
	<div class="container">
		<c:if test="${not empty requestScope.indexShowOn.listPopular }">
		<div class="main-slider">
			<div class="badg">
				<p>
					<a>Phổ Biến</a>
				</p>
			</div>
			<div class="flexslider">
				<ul class="slides">
					<c:forEach var="post" items="${requestScope.indexShowOn.listPopular }">
					<li>
						<img src="${fn:contains(post.avatarUrl, 'http') ? '' : pageContext.request.contextPath}${post.avatarUrl}" alt="MyPassion" />
						<p class="flex-caption">
							<a href="view?u=${post.link }">${post.title }</a> 
							${post.desc }
						</p>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		</c:if>

		<c:forEach var="post" items="${requestScope.indexShowOn.listLatest }">
		<div class="slider3">
			<a href="view?u=${post.link }">
				<img src="${fn:contains(post.avatarUrl, 'http') ? '' : pageContext.request.contextPath}${post.avatarUrl}" alt="MyPassion" />
			</a>
			<p class="caption">
				<a href="view?u=${post.link }">${post.title }</a>
			</p>
		</div>
		</c:forEach>
	</div>
</section>
<!-- / Slider -->