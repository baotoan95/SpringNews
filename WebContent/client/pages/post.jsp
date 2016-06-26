<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="position: fixed; padding: 10px; z-index: 9999999; font-weight: bold; background: red; text-align: center;">
	<a style="color: yellow;" href="${pageContext.request.contextPath }/home.html">
		SpringNews
	</a>
</div>
<c:import url="${requestScope.url }"></c:import>