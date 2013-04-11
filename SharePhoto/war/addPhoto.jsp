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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="style.css" rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Google Maps Multiple Markers</title>
<script src="http://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript">
	
</script>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;
	}

	function integersOnly(myfield, e) {
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;
		else
			return true;
		keychar = String.fromCharCode(key);

		// control keys
		if ((key == null) || (key == 0) || (key == 8) || (key == 9)
				|| (key == 13) || (key == 27))
			return true;

		// numbers
		else if ((("0123456789").indexOf(keychar) > -1))
			return true;
		// decimal point jump
		else
			return false;
	}

	function clearField(field) {
		document.getElementsByName(field)[0].value = "";
	}
</script>
<style type="text/css">
#map {
	width: 800px;
	height: 400px;
	float: left;
}
</style>
<title>Add a Photo</title>
</head>
<body>
	<div id="templatemo_header_wrapper">

		<div id="templatemo_header">

			<div id="site_title">
				<h1>
					<a href="map.jsp" target="_parent"> PhotoTravel <span>photography
							sharing</span>
					</a>
				</h1>
			</div>

			<div id="templatemo_menu">
				<ul>
					<li><a href="map.jsp" class="current">Home</a></li>
					<%
						UserService userService = UserServiceFactory.getUserService();
						User user = userService.getCurrentUser();
						if (user != null) {
							session.setAttribute("user", user);
					%>
					<li><a
						href="<%=userService.createLogoutURL(request.getRequestURI())%>">Logout</a></li>
					<%
						} else {
					%>
					<li><a
						href="<%=userService.createLoginURL(request.getRequestURI())%>">Login</a></li>
					<%
						}
					%>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<!-- end of templatemo_menu -->



			<div class="cleaner"></div>
		</div>
		<!-- end of templatemo_header -->
	</div>

	<form id="form1" action="/addPhotoServlet" method="post"
		enctype="multipart/form-data">
		<div id="templatemo_content_wrapper">

			<div id="templatemo_content">

				<div class="content_section">
					<h2>Add a Photo</h2>

					<table>
						<tbody>
							<tr>
								<td>Title:</td>
								<td><input type="text" id="formTitle" name="title"></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><textarea id="formDescription" rows="4" cols="50"
										name="description"></textarea></td>
							</tr>
							<tr>
								<td>Weather:</td>
								<td><select name="weather">
										<option value="sunny">Sunny</option>
										<option value="rainy">Rainy</option>
										<option value="cloudy">Cloudy</option>
										<option value="snowy">Snowy</option>
								</select></td>
							</tr>
							<tr>
								<td>Time:</td>
								<td><select name="time">
										<option value="morning">Morning</option>
										<option value="noon">Noon</option>
										<option value="afternoon">Afternoon</option>
										<option value="evening">Evening</option>
										<option value="night">Night</option>
								</select></td>
							</tr>
							<tr>
								<td>Location:</td>
								<td>
									<div id="map"></div> <input id="latitude" type="hidden"
									name="latitude"> <input id="longitude" type="hidden"
										name="longitude">
								</td>
							</tr>
							<tr>
								<td>Aperture:</td>
								<td><input type="text" name="aperture"
									onkeypress="return integersOnly(this, event);"
									onkeydown="clearField('reduceNumber')" maxlength="5"></td>
							</tr>
							<tr>
								<td>ISO:</td>
								<td><input type="text" name="iso"
									onkeypress="return integersOnly(this, event);"
									onkeydown="clearField('reduceNumber')" maxlength="5"></td>
							</tr>
							<tr>
								<td>Shutter speed:</td>
								<td><input type="text" name="shutter_speed"
									onkeypress="return integersOnly(this, event);"
									onkeydown="clearField('reduceNumber')" maxlength="5"></td>
							</tr>
							<tr>
								<td>Image File:</td>
								<td><input id="formFile" type="file" name="file"></td>
							</tr>
							<tr>
								<td>Tags:</td>
								<td><span id="tags_span"></span> <input id="tag_field"
									type="text"></input>
									<button id="add_tag_button">Add Tag</button> <input
									type="hidden" id="tags" name="tags" value="" /></td>
							</tr>
							<tr>
								<td><br></td>
								<td>
									<div class="button_01">
										<a id="submitForm" href="javascript:;">Submit</a> <input
											type="hidden" value="Submit" />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="cleaner"></div>
				</div>
				<div class="cleaner_h40"></div>
			</div>

			<div id="templatmeo_sidebar">

				<div class="sidebar_section">
					<div class="sidebar_section_content"></div>
				</div>

				<div class="cleaner_h30"></div>
				<div class="sidebar_section"></div>

			</div>
			<!-- end of sidebar -->

			<div class="cleaner"></div>
		</div>
		<div id="templatemo_content_wrapper_bottom"></div>
		<!-- end of content_wrapper -->
		<div id="templatemo_footer">
			Copyright ECE1779 Group 4 | <a href="http://www.utoronto.ca">University
				of Toronto</a> |
		</div>
		<!-- end of templatemo_footer -->

	</form>
</body>
<script type="text/javascript">
	var tagsStr = "";
	var marker = new google.maps.Marker();
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 2,
		center : new google.maps.LatLng(-0, 0),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	$(function() {
		google.maps.event.addListener(map, 'click', function(event) {
			var latlng = event.latLng;
			$("#latitude").val(latlng.lat());
			$("#longitude").val(latlng.lng());
			marker.setMap(null);
			marker = new google.maps.Marker({
				position : event.latLng,
				map : map
			});
		});

		$("#add_tag_button").click(function() {
			if (tagsStr == "") {
				tagsStr += ($("#tag_field").val());
			} else {
				tagsStr += ("," + $("#tag_field").val());
			}
			var tags = tagsStr.split(",");
			var tagsHtml = ""
			for ( var i = 0; i < tags.length; i++) {
				tagsHtml += ("<span>" + tags[i] + "</span> ");
			}
			$("#tags_span").html(tagsHtml);
			$("#tags").val(tagsStr);
			return false;
		});
	});

	$(document).ready(function() {
		
		$("#submitForm").click(function(check) {
			var title = $("#formTitle").val();
			var description = $("#formDescription").val();
			var latitude = $("#latitude").val();
			var longtitue = $("#longtitue").val();
			var filepath = $("#formFile").val();
			var fileArr=filepath.split("\\"); 
			var fileTArr=fileArr[fileArr.length-1].toLowerCase().split("."); 
			var filetype=fileTArr[fileTArr.length-1]; 

			if (title == "" || description == "" || latitude == "") {
				alert("You must input a title, description and set a location before submit!");
				if (filepath == "") {
					alert("You haven't upload an image file!");
				}else if(filetype!="jpg"){ 
					alert("The picture you upload should be a JPG file!");
				}
			}else if(filepath != "" && filetype!="jpg"){
				alert("The picture you upload should be a JPG file!");
			}else {
				$("#form1").submit();
			}
		});
		
	});
</script>
</html>