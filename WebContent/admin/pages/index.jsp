<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<script type="text/javascript">
		(function(w,d,s,g,js,fs){
		  g=w.gapi||(w.gapi={});g.analytics={q:[],ready:function(f){this.q.push(f);}};
		  js=d.createElement(s);fs=d.getElementsByTagName(s)[0];
		  js.src='https://apis.google.com/js/platform.js';
		  fs.parentNode.insertBefore(js,fs);js.onload=function(){g.load('analytics');};
		}(window,document,'script'));
	</script>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Dashboard <small>Version 2.0</small>
		</h1>
	</section>
	
	<!-- Current date -->
	<% Calendar calendar = Calendar.getInstance(); %>
	<c:set var="currentMonth" value="<%= calendar.get(Calendar.MONTH) + 1 %>"></c:set>
	<c:set var="currentYear" value="<%= calendar.get(Calendar.YEAR) %>"></c:set>

	<!-- Main content -->
	<section class="content" id="content">
		<div id="embed-api-auth-container"></div>
		<div id="chart-container"></div>
		<div id="view-selector-container"></div>
		
		<h3>Thống kê lượng truy cập tháng 
			<select name="month" id="month">
				<c:forEach var="i" begin="1" end="12" step="1">
				<option value="${i }" ${i == currentMonth ? "selected" : ""}> ${i } </option>
				</c:forEach>
			</select>
			năm
			<select name="year" id="year">
				<c:forEach var="i" begin="2015" end="2016" step="1">
				<option value="${i }" ${i == currentYear ? "selected" : ""}> ${i } </option>
				</c:forEach>
			</select>
		</h3>
		
		<!-- Chart statistic -->
		<canvas id='btChart' height='300' style='border:1px solid'>
			This browser is not support HTML5
		</canvas>
		
		<h4 id="totalStatistic">Tổng lượng truy cập: ${totalVisits } lượt</h4>
		
		<script lang="javascript" src="<c:url value='/resources/admin/dist/js/chart.js'/>"></script>
		<script type="text/javascript">
			var arr = new Array();
			var maxValue = parseInt("${maxValue}");
			<c:forEach var="data" items="${data}" varStatus="status">
				var i = parseInt("${status.index}");
				arr[i] = "D ${status.count}" + ", ${data}";
			</c:forEach>
			init(maxValue, arr, 5);
		</script>
		<!-- End chart statistic -->
	</section>
	<!-- /.content -->
	
	<script type="text/javascript">
			$("#month").change(function() {
				submitForm();
			});
			$("#year").change(function() {
				submitForm();
			});

			function submitForm() {
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/admin/statistic",
				data: {
					month : $("#month").val(),
					year : $("#year").val()
				},
				success : function(res) {
					var arr = new Array();
					res.data.forEach(function(value, index) {
						arr[index] = "D " + (index + 1) + ", " + value; 
					});
					document.getElementById('btChart').getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
					init(res.maxValue, arr, 5);
					$('#totalStatistic').text('Tổng lượt truy cập: ' + res.totalStatistic + ' lượt');
				},
				error: function(err) {
					alert(err);
				}
			});
			}
		</script>
	
	<script>

		gapi.analytics.ready(function() {
		
		  /**
		   * Authorize the user immediately if the user has already granted access.
		   * If no access has been created, render an authorize button inside the
		   * element with the ID "embed-api-auth-container".
		   */
		  gapi.analytics.auth.authorize({
		    container: 'embed-api-auth-container',
		    clientid: 'UA-77904743-1'
		  });
		
		
		  /**
		   * Create a new ViewSelector instance to be rendered inside of an
		   * element with the id "view-selector-container".
		   */
		  var viewSelector = new gapi.analytics.ViewSelector({
		    container: 'view-selector-container'
		  });
		
		  // Render the view selector to the page.
		  viewSelector.execute();
		
		
		  /**
		   * Create a new DataChart instance with the given query parameters
		   * and Google chart options. It will be rendered inside an element
		   * with the id "chart-container".
		   */
		  var dataChart = new gapi.analytics.googleCharts.DataChart({
		    query: {
		      metrics: 'ga:sessions',
		      dimensions: 'ga:date',
		      'start-date': '30daysAgo',
		      'end-date': 'yesterday'
		    },
		    chart: {
		      container: 'chart-container',
		      type: 'LINE',
		      options: {
		        width: '100%'
		      }
		    }
		  });
		
		
		  /**
		   * Render the dataChart on the page whenever a new view is selected.
		   */
		  viewSelector.on('change', function(ids) {
		    dataChart.set({query: {ids: ids}}).execute();
		  });
		
		});
	</script>
</div>
<!-- /.content-wrapper -->