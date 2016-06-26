<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="encoding">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.4 -->
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
<!-- Font Awesome Icons -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- jvectormap -->
<link href="<c:url value='/resources/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css'/>" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="<c:url value='/resources/admin/dist/css/AdminLTE.min.css'/>" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link href="<c:url value='/resources/admin/dist/css/skins/_all-skins.min.css'/>" rel="stylesheet" type="text/css" />
<!-- jQuery 2.1.4 -->
<script src="<c:url value='/resources/admin/plugins/jQuery/jquery.min.js'/>" type="text/javascript"></script>
<!-- Ckeditor -->
<script src="<c:url value='/resources/ckeditor/ckeditor.js'/>" type="text/javascript"></script>


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- header -->
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<!-- leftside -->
		<tiles:insertAttribute name="sidebar"></tiles:insertAttribute>
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
		<!-- footer -->
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		
		<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
		
	</div>
	<!-- ./wrapper -->

	<!-- Bootstrap 3.3.2 JS -->
	<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
	<!-- FastClick -->
	<script src="<c:url value='/resources/admin/plugins/fastclick/fastclick.min.js'/>" type="text/javascript"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value='/resources/admin/dist/js/app.min.js'/>" type="text/javascript"></script>
	<!-- Sparkline -->
	<script src="<c:url value='/resources/admin/plugins/sparkline/jquery.sparkline.min.js'/>" type="text/javascript"></script>
	<!-- jvectormap -->
	<script src="<c:url value='/resources/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>" type="text/javascript"></script>
	<!-- SlimScroll 1.3.0 -->
	<script src="<c:url value='/resources/admin/plugins/slimScroll/jquery.slimscroll.min.js'/>" type="text/javascript"></script>
	<!-- ChartJS 1.0.1 -->
	<script src="<c:url value='/resources/admin/plugins/chartjs/Chart.min.js'/>" type="text/javascript"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<%-- 	<script src="<c:url value='/resources/admin/dist/js/pages/dashboard2.js'/>" type="text/javascript"></script> --%>
	<!-- AdminLTE for demo purposes -->
	<script src="<c:url value='/resources/admin/dist/js/demo.js'/>" type="text/javascript"></script>
</body>
</html>
