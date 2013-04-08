<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Google Maps Multiple Markers</title>
<script src="http://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript"></script>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<style type="text/css">
#map {
	width: 500px;
	height: 400px;
	float: left;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<form action="/addPhotoServlet" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>Title:</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><textarea rows="4" cols="50" name="description"></textarea></td>
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
					<td><input type="text" name="aperture"></td>
				</tr>
				<tr>
					<td>ISO:</td>
					<td><input type="text" name="aperture"></td>
				</tr>
				<tr>
					<td>Shutter speed:</td>
					<td><input type="text" name="shutter_speed"></td>
				</tr>
				<tr>
					<td>Image File:</td>
					<td><input type="file" name="file"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
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
	});
</script>
</html>