<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Main Content -->
<div class="main-content">
	<sec:authentication property="principal" var="principal"/>
	<!-- Single -->
	<div class="column-two-third single">
		<h6 class="title">${requestScope.post.title }</h6>
		<span class="meta">${requestScope.post.publishedDate } \\ <a
			href="#">${requestScope.post.author.firstName } ${requestScope.post.author.lastName }</a> \\ Lượt xem: ${requestScope.post.views }
		</span>
		<p>${requestScope.post.content }</p>
		<ul class="sharebox">
			<li><div class="fb-like" data-href="" data-layout="standard" data-action="like" data-show-faces="false" data-share="true"></div></li>
		</ul>
		<div class="authorbox">
			<img src="<c:url value='${requestScope.post.author.avatarUrl}'/>" alt="MyPassion" />
			<h6>${requestScope.post.author.firstName }  ${requestScope.post.author.lastName }</h6>
			<p>${requestScope.post.author.desc }</p>
		</div>
		<div class="comments">
			<h5 class="line">
				<span>Comments.</span>
			</h5>
			<div class="fb-comments" data-href="" data-width="100%" data-numposts="5"></div>
		</div>
	</div>
	<!-- /Single -->

</div>
<!-- /Main Content -->
