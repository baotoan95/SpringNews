<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- fullCalendar 2.2.5-->
    <link href="<c:url value='/resources/admin/plugins/fullcalendar/fullcalendar.min.css'/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/resources/admin/plugins/fullcalendar/fullcalendar.print.css'/>" rel="stylesheet" type="text/css" media='print' />
    <!-- Theme style -->
    <link href="<c:url value='/resources/admin/dist/css/AdminLTE.min.css'/>" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
    <link href="<c:url value='/resources/admin/dist/css/skins/_all-skins.min.css'/>" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="<c:url value='/resources/admin/plugins/iCheck/flat/blue.css'/>" rel="stylesheet" type="text/css" />
	<!-- jQuery 2.1.4 -->
    <script src="<c:url value='/resources/admin/plugins/jQuery/jQuery-2.1.4.min.js'/>" type="text/javascript"></script>
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
		<div class="content-wrapper">
			<section class="content">
				<div class="row">
					<!-- Menu mail -->
					<tiles:insertAttribute name="menu"></tiles:insertAttribute>
					<tiles:insertAttribute name="content"></tiles:insertAttribute>
				</div>
			</section>
		</div>
		<!-- footer -->
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>

		<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>

	</div>
	<!-- ./wrapper -->

    <!-- Bootstrap 3.3.2 JS -->
    <script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="<c:url value='/resources/admin/plugins/slimScroll/jquery.slimscroll.min.js'/>" type="text/javascript"></script>
    <!-- FastClick -->
    <script src="<c:url value='/resources/admin/plugins/fastclick/fastclick.min.js'/>" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="<c:url value='/resources/admin/dist/js/app.min.js'/>" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="<c:url value='/resources/admin/plugins/iCheck/icheck.min.js'/>" type="text/javascript"></script>
    <!-- Page Script -->
    <script>
      $(function () {
        //Enable iCheck plugin for checkboxes
        //iCheck for checkbox and radio inputs
        $('.mailbox-messages input[type="checkbox"]').iCheck({
          checkboxClass: 'icheckbox_flat-blue',
          radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $(".checkbox-toggle").click(function () {
          var clicks = $(this).data('clicks');
          if (clicks) {
            //Uncheck all checkboxes
            $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
            $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
          } else {
            //Check all checkboxes
            $(".mailbox-messages input[type='checkbox']").iCheck("check");
            $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
          }
          $(this).data("clicks", !clicks);
        });

        //Handle starring for glyphicon and font awesome
        $(".mailbox-star").click(function (e) {
          e.preventDefault();
          //detect type
          var $this = $(this).find("a > i");
          var glyph = $this.hasClass("glyphicon");
          var fa = $this.hasClass("fa");

          //Switch states
          if (glyph) {
            $this.toggleClass("glyphicon-star");
            $this.toggleClass("glyphicon-star-empty");
          }

          if (fa) {
            $this.toggleClass("fa-star");
            $this.toggleClass("fa-star-o");
          }
        });
      });
    </script>
    <!-- AdminLTE for demo purposes -->
    <script src="<c:url value='/resources/admin/dist/js/demo.js'/>" type="text/javascript"></script>
</body>
</html>