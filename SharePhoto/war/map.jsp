<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="style.css" rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Google Maps Multiple Markers</title>
<script src="http://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript"></script>
<script type="text/javascript" src="script/ajaxRequest.js"></script>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="js/infobox.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
	function clearText(field)
	{
	    if (field.defaultValue == field.value) field.value = '';
	    else if (field.value == '') field.value = field.defaultValue;
	}
</script>
<style type="text/css">
#main {
	height: 800px;
	width: 1200px;
	margin-left: auto;
	margin-right: auto;
}

#map {
	width: 670px;
	height: 800px;
	float: left;
}

#filters_box {
	width: 200px;
	float: right;
}

.select_weather {
	color: red;
}

.select_time {
	color: red;
}
</style>
</head>
<body>
	<div id="templatemo_header_wrapper">

		<div id="templatemo_header">
	    
	   		<div id="site_title">
	            <h1><a href="map.jsp" target="_parent">
	            	PhotoTravel
	                <span>photography sharing</span>
	            </a></h1>
	        </div>
	        
	        <div id="templatemo_menu">
	            <ul>
	                <li><a href="map.jsp" class="current">Home</a></li>
	                <li><a href="#">Link1</a></li>
	                <li><a href="#">Contact</a></li>
	            </ul>    	
	        </div> <!-- end of templatemo_menu -->
	        
	        <div id="search_box">
	            <form action="#" method="get">
	                <input type="text" value="Enter a keyword to search photos" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
	            </form>
	        </div>
	    
	    	<div class="cleaner"></div>
	    </div> <!-- end of templatemo_header -->
	</div>
	
	<div id="templatemo_content_wrapper">
		
		<div id="templatemo_content">
			<div class="content_section">
				<div style="height:40px">
				</div>
			</div>

	        <div class="content_section">
	        	<h2>World Map</h2>
	        	<div id="map"></div>
				<div class="cleaner"></div>
			</div>
			<div class="cleaner_h40"></div>
		</div>
		
		<div id="templatmeo_sidebar">
    
    		<div class="sidebar_section">

    			<div class="sidebar_section_content">
					<div style="height:50px">
					<%
						UserService userService = UserServiceFactory.getUserService();
						User user = userService.getCurrentUser();
						if (user != null) {
							session.setAttribute("user", user);
					%>
					<p style="float:right">
						Hello, ${fn:escapeXml(user.nickname)}! 
					</p>
					<br>
					<div class="button_01" style="padding-left:150px">
						<a href="<%=userService.createLogoutURL(request.getRequestURI())%>">Logout</a>
					</div>
					<!-- <a class="button" href="<%=userService.createLogoutURL(request.getRequestURI())%>">Logout</a> -->
					<%
						} else {
					%>
					<p>
						<div class="button_01" style="padding-left:150px">
							<a href="<%=userService.createLoginURL(request.getRequestURI())%>">Login</a>
						</div>
					</p>
					<%
						}
					%>
					</div>
    			</div>
	       		<div class="sidebar_section_content">
	                <h2>Filters</h2>
					<div id="weathers" style="padding-left:70px">
						<h3>Weather</h3>
						<input class="weather" type="radio" name="weather" value="Sunny" >Sunny<br>
						<input class="weather" type="radio" name="weather" value="Rainy" >Rainy<br>
						<input class="weather" type="radio" name="weather" value="Cloudy" >Cloudy<br>
						<input class="weather" type="radio" name="weather" value="Snowy" >Snowy<br>
					</div>
					<div id="time" style="padding-left:70px">
						<h3 >Time</h3>
						<input class="time" type="radio" name="time" value="Morning" >Morning<br>
						<input class="time" type="radio" name="time" value="Noon" >Noon<br>
						<input class="time" type="radio" name="time" value="Afternoon" >Afternoon<br>
						<input class="time" type="radio" name="time" value="Evening" >Evening<br>
						<input class="time" type="radio" name="time" value="Night" >Night<br>
					</div>
					<!-- <div id="keyword_box" style="padding-left:70px">
						<h3>Keyword</h3>
						<div>
							<input id="keyword" type="text">
							
						</div>
					</div>
					<div style="padding-left:70px">
						<button id="update_ui">Search</button>
					</div>
 -->
	            </div>           
        	</div>
        
	        <div class="cleaner_h30"></div>
	        <div class="sidebar_section"></div>

		</div> <!-- end of sidebar -->

		<div class="cleaner"></div>  
		</div><div id="templatemo_content_wrapper_bottom"></div> <!-- end of content_wrapper -->
		<div id="templatemo_footer">
            Copyright ECE1779 Group 4 | <a href="http://www.utoronto.ca">University of Toronto</a> | 
        </div> <!-- end of templatemo_footer -->
	</div>
	<script type="text/javascript">
		//search params
		var weather = "sunny";
		var time = "morning";
		var keyword = "hi";
		var north_east_lat = 90;
		var north_east_lng = 180;
		var south_west_lat = -90;
		var south_west_lng = -180;

		//points of markers
		var pictures = new Array();

		//the map
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 2,
			center : new google.maps.LatLng(-0, 0),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});

		//objects on the map
		var infowindows = new Array();
		var infowindowsOpen = new Array();
		var markers = new Array();

		//key method used to update UI based on the pictures
		function update_ui() {
			if (markers && markers.length != 0) {
				for ( var i = 0; i < markers.length; ++i) {
					infowindows[i].close();
					markers[i].setMap(null);
					infowindowsOpen = false;
				}
			}

			for ( var i = 0; i < pictures.length; i++) {
				var box_content = '<div id=window_'+i+'>'
						+ pictures[i].title
						+ '<div><div style="text-align:center"><a href="/retrievePhotoDetailServlet?key=123"><img src="'+pictures[i].url_small+'" style="max-width:98px;border:1px solid;"></a></div>'
				var myOptions = {
					content : box_content,
					disableAutoPan : false,
					pixelOffset : new google.maps.Size(0, 0),
					zIndex : null,
					boxStyle : {
						width:"110px",
						background : "#FFF",
						border : "solid 1px",
						padding : "1px 3px 3px 3px"
					},
					closeBoxURL : "http://www.google.com/intl/en_us/mapfiles/close.gif",
					infoBoxClearance : new google.maps.Size(1, 1),
					isHidden : false,
					pane : "floatPane",
					enableEventPropagation : false
				};

				infowindows[i] = new InfoBox(myOptions);

				//create markers
				markers[i] = new google.maps.Marker({
					position : new google.maps.LatLng(pictures[i].latitude,
							pictures[i].longitude),
					map : map
				});

				//open their infowindows to show the pictures
				infowindows[i].open(map, markers[i]);
				infowindowsOpen[i] = true;

				//when a marker is clicked, open a new infowindow if the window is not open
				google.maps.event.addListener(markers[i], 'click', (function(
						markers, i) {
					return function() {
						if (!infowindowsOpen[i])
							infowindows[i].open(map, markers[i]);
						infowindowsOpen[i] = true;
					}
				})(markers, i));

				//when close a infowindow, mark its window is close
				google.maps.event.addListener(infowindows[i], 'closeclick',
						(function(infowindows, i) {
							return function() {
								infowindowsOpen[i] = false;
							}
						})(infowindows, i));
			}
		}

		//ajax controll
		var xmlhttp = null;
		function getHttpRequest() {
			try {
				xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
				} catch (e1) {
					xmlhttp = new XMLHttpRequest();
				}

			}
		}

		//start ajax request
		function httpGet(url, method, data) {
			getHttpRequest();
			xmlhttp.open(method, url + "?" + data, true);
			xmlhttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlhttp.onreadystatechange = callback;
			xmlhttp.send(null);
		}

		//when response back, start this callback function 
		function callback() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					var xmlDoc = xmlhttp.responseText;

					//jason data from server
					var data = eval(xmlDoc);
					pictures = new Array();
					for ( var j = 0; j < data.length; j++) {
						pictures = data;
					}

					//update the object
					update_ui();
				} else {
					alert("no data");
				}
			}
		}

		//main function, call to send ajax request and update the map
		function getPhotos() {

			//update the map bounds first
			north_east_lat = map.getBounds().getNorthEast().lat();
			north_east_lng = map.getBounds().getNorthEast().lng();
			south_west_lat = map.getBounds().getSouthWest().lat();
			south_west_lng = map.getBounds().getSouthWest().lng();

			keyword = $("#keyword").val();

			var url = 'searchPhotoServlet';
			var params = 'weather=' + weather + '&time=' + time + '&lat1='
					+ south_west_lat + '&lat2=' + north_east_lat + '&lng1='
					+ south_west_lng + '&lng2=' + north_east_lng + '&keyword='
					+ keyword;
			httpGet(url, "GET", params);
		}

		$(function() {
			$("#update_ui").click(function() {
				getPhotos();
			});

			$(".weather").click(function(e) {
				$(".select_weather").toggleClass("select_weather");
				if (this == e.target) {
					$(this).addClass("select_weather");
					weather = $(this).html();
				}
				getPhotos();
			});

			$(".time").click(function(e) {
				$(".select_time").toggleClass("select_time");
				if (this == e.target) {
					$(this).addClass("select_time");
					time = $(this).html();
				}
				getPhotos();
			});

			//wait some time to init the map. Fix the bug that map.getBounds returns null
			window.setTimeout(getPhotos, 200);
		});
	</script>
</body>
</html>