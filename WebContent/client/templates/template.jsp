<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="SpringNews">
<meta name="author" content="BTIT95">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<link rel="shortcut icon" href='<c:url value="/resources/client/client/img/sms-4.ico"></c:url>' />
<script type="text/javascript" src="<c:url value='/resources/client/js/jquery.js'/>"></script>
<!-- STYLES -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/superfish.css'/>" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/fontello/fontello.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/flexslider.css'/>" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/ui.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/base.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/style.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/960.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/client/css/devices/1000.css'/>" 
	media="only screen and (min-width: 768px) and (max-width: 1000px)" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/devices/767.css'/>"
	media="only screen and (min-width: 480px) and (max-width: 767px)" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/client/css/devices/479.css'/>"
	media="only screen and (min-width: 200px) and (max-width: 479px)" />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather+Sans:400,300,700,800'
	rel='stylesheet' type='text/css'>
<!--[if lt IE 9]> <script type="text/javascript" src="js/customM.js"></script> <![endif]-->
<!-- Google analytics -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-77904743-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<!-- Body Wrapper -->
<body>
	<!-- Facebook -->
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6&appId=610454519104006";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	<!-- Comment -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('.fb-comments, fb-like').attr('data-href', window.location.href);
		});
	</script>
	<!-- Facebook -->
	<div class="body-wrapper">
		<div class="controller">
			<div class="controller2">
				<tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="topside" />
				<!-- Content -->
				<section id="content">
					<div class="container">
						<tiles:insertAttribute name="body" />
						<tiles:insertAttribute name="rightside" />
					</div>
				</section>
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>

	<!-- SCRIPTS -->
	<script type="text/javascript" src="<c:url value='/resources/client/js/easing.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/1.8.2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/ui.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/carouFredSel.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/superfish.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/customM.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/flexslider-min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/jtwt.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/jflickrfeed.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/client/js/mobilemenu.js'/>"></script>

	<!--[if lt IE 9]> <script type="text/javascript" src="js/html5.js"></script> <![endif]-->
	<script type="text/javascript" src="<c:url value='/resources/client/js/mypassion.js'/>"></script>
</body>
</html>