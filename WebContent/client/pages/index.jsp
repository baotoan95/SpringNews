<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Main Content -->
<div class="main-content">
	<c:forEach items="${requestScope.indexShowOn.listShow }" var="cateShow">
		<!-- Popular News -->
		<div class="column-one-third">
			<h5 class="line">
				<span>${cateShow.name }</span>
			</h5>
			<div class="outertight">
				<ul class="block">
				<c:forEach items="${cateShow.listPost }" var="post">
					<li>
						<a href="view?u=${post.link }">
							<img src="${post.avatarUrl }" alt="MyPassion" class="alignleft" />
						</a>
						<p>
							<fmt:formatDate value="${post.publishedDate}" var="formattedDate"
					type="date" pattern="dd/MM/yyyy HH:mm:ss" />
							<span>${formattedDate }</span> 
							<a href="view?u=${post.link }" >${post.title }</a>
						</p> 
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<!-- /Popular News -->
	</c:forEach>
</div>