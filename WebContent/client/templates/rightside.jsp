<%@page import="com.news.cd.listeners.SessionCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Left Sidebar -->
<div class="column-one-third">
	<div class="sidebar">
		<h5 class="line">
			<span>Lượng truy cập</span>
		</h5>
		<ul class="social">
			<li>
				<h5>Trực Tuyến</h5>
				<span>${sessionScope.USER_ACTIVE }<br /> 
				<i>user(s)</i></span>
			</li>
			<li>
				<h5>Hôm Qua</h5>
				<span>${sessionScope.STATISTIC_YESTERDAY }<br /> <i>user(s)</i></span>
			</li>
			<li>
				<h5>Tổng</h5> 
				<span>${sessionScope.STATISTIC_TOTAL }<br/><i>user(s)</i></span>
			</li>
		</ul>
	</div>

	<div class="sidebar">
		<div id="tabs">
			<ul>
				<li><a href="#tabs1">Gần Đây</a></li>
				<li><a href="#tabs2">Phổ Biến</a></li>
			</ul>
			<div id="tabs1">
				<ul>
					<c:forEach items="${requestScope.listLatest }" var="post">
						<fmt:formatDate value="${post.publishedDate}" var="formattedDate"
					type="date" pattern="dd/MM/yyyy HH:mm:ss" />
						<li>
							<a href="view?u=${post.link }" class="title">${post.title }</a><br/>
							<span class="meta">
							<a>
								<c:if test="${empty post.author }">
									${post.rssChannel.generator }
								</c:if>
								<c:if test="${not empty post.author }">
									${post.author.firstName } ${post.author.lastName }
								</c:if>
							</a> // ${formattedDate }
							</span> 
							<span class="rating"><span style="width: 70%;"></span></span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="tabs2">
				<ul>
					<c:forEach items="${requestScope.listPopular }" var="post">
						<fmt:formatDate value="${post.publishedDate}" var="formattedDate"
					type="date" pattern="dd/MM/yyyy HH:mm:ss" />
						<li>
							<a href="view?u=${post.link }" class="title">${post.title }</a><br/>
							<a>
							<c:if test="${empty post.author }">
								${post.rssChannel.generator }
							</c:if>
							<c:if test="${not empty post.author }">
								${post.author.firstName } ${post.author.lastName }
							</c:if>
							</a> //
							<span class="meta">${formattedDate }</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div class="sidebar">
		<h5 class="line">
			<span>Ads Spot.</span>
		</h5>
		<ul class="ads125">
			<li><a href="#"><img
					src="<c:url value='/resources/client/images/banner/3.png'/>"
					alt="MyPassion" /></a></li>
			<li><a href="#"><img
					src="<c:url value='/resources/client/images/banner/1.png'/>"
					alt="MyPassion" /></a></li>
			<li><a href="#"><img
					src="<c:url value='/resources/client/images/banner/2.png'/>"
					alt="MyPassion" /></a></li>
			<li><a href="#"><img
					src="<c:url value='/resources/client/images/banner/4.png'/>"
					alt="MyPassion" /></a></li>
		</ul>
	</div>

	<div class="sidebar">
		<h5 class="line">
			<span>Facebook.</span>
		</h5>
		<div class="fb-page"
			data-href="https://www.facebook.com/IISpringNews/"
			data-tabs="timeline" data-height="300" data-small-header="false"
			data-adapt-container-width="true" data-hide-cover="true"
			tabs="false" data-tabs="false"
			scrolling="no"
			style="overflow: hidden;"
			data-show-facepile="true">
			<div class="fb-xfbml-parse-ignore">
				<blockquote cite="https://www.facebook.com/IISpringNews/">
					<a href="https://www.facebook.com/IISpringNews/">SpringNews</a>
				</blockquote>
			</div>
		</div>
	</div>
</div>
<!-- /Left Sidebar -->