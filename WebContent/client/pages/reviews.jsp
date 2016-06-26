<%@page import="com.news.cd.entities.Post"%>
<%@page import="com.news.cd.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Main Content -->
<div class="main-content">
	<c:if test="${not empty requestScope.category }">
	<div class="cat-content">
		<section class="content-list">
			<header>
				<h1 class="title">${requestScope.category.name }</h1>
			</header>
			
			<c:if test="${not empty requestScope.message }">
			<p style="margin-top: 10px;">${requestScope.message }</p>
			</c:if>
			
			<c:forEach var="post" items="${requestScope.posts }">
			<article>
				<figure>
					<a 
						href="view?u=${post.link }">
						<img src="${fn:contains(post.avatarUrl, 'http') ? '' : pageContext.request.contextPath}${post.avatarUrl}" 
						alt="${post.title }" />
					</a>
				</figure>
				<header>
					<h2>
						<a  href="view?u=${post.link }" title="${post.title }">${post.title }</a>
					</h2>
					<p class="meta">
						<a>
						<c:if test="${empty post.author }">
							${post.rssChannel.generator }
						</c:if>
						<c:if test="${not empty post.author }">
							${post.author.firstName } ${post.author.lastName }
						</c:if>
						</a> //
						<fmt:formatDate value="${post.publishedDate}" var="formattedDate"
					type="date" pattern="dd/MM/yyyy HH:mm:ss" />
						<time class="friendly">${formattedDate }</time>
					</p>
					<p class="summary">${post.desc }</p>
				</header>
			</article>
			</c:forEach>
		</section>
	</div>
	<div class="pager">
		${requestScope.pagination }
	</div>
	</c:if>
</div>
<!-- / Content -->