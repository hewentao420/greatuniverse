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

var contentHeight = 800;
var pageHeight = document.documentElement.clientHeight;
var scrollPosition;
var n = 10;
var xmlhttp;

function putImages(){
	
	if (xmlhttp.readyState==4) 
	  {
		  if(xmlhttp.responseText){
			 var resp = xmlhttp.responseText.replace("\r\n", ""); 
			 var files = resp.split(";");
			  var j = 0;
			  for(i=0; i<files.length; i++){
				  if(files[i] != ""){
					 document.getElementById("container").innerHTML += //use files[i] to wrie the inner html
					document.getElementById("container").innerHTML += '<br />';
				  }
			  }
		  }
	  }
}
		
		
function scroll(){
	//Change this accordingly to get image using action and scroll
	if(navigator.appName == "Microsoft Internet Explorer")
		scrollPosition = document.documentElement.scrollTop;
	else
		scrollPosition = window.pageYOffset;		
	
	if((contentHeight - pageHeight - scrollPosition) < 500){
				
		if(window.XMLHttpRequest)
			xmlhttp = new XMLHttpRequest();
		else
			if(window.ActiveXObject)
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			else
				alert ("Bummer! Your browser does not support XMLHTTP!");		  
		  
		var url="getImages.php?n="+n;
		
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
		
		n += 9;
		xmlhttp.onreadystatechange=putImages;		
		contentHeight += 800;		
	}
}
</script>
<style type="text/css">
	img {max-width: 100%;}

#templatmeo_sidebar_zero {
	float: right;
	width: 0px;
	padding: 0;
	background: url(images/templatemo_sidebar.jpg) repeat-y; 
}

#container{
	margin: 0 auto;
	width:920px;
	border:1px solid #333;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	font-family:Verdana, Geneva, sans-serif;
	text-align:left;
}

img{
	border:10px solid #444;
	-moz-border-radius: 5px;
	-webkit-border-radius: 10px;
	margin: 15px;
}

img:hover{
	border-color:#555;
	-moz-box-shadow: 0px 0px 15px #111;
	-webkit-box-shadow: 0px 0px 15px #111;
}

.star {
background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAKCAQAAADI+WwIAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAJiS0dEAP+Hj8y/AAAACXBIWXMAAAsTAAALEwEAmpwYAAAACXZwQWcAAAALAAAACgCF+qVAAAAAhUlEQVQI103NMQqCABgF4C+Fxg7QHDQ0JkFnaKqlKJBu4Np9hEDoBq2doCYP0B4UBKLYUKZvex+P/+9N/dJ3FXl9S9CopbF9U1pOkAgb3irVanOMlGqldeBoo9CmspMFOFmp/hzLmtuXzvrWvpwJFVJvLLqci8Qmzl8OhzBwcMdD6ilXfwAd9B9f78yTCQAAAABJRU5ErkJggg==);  
  }
 
  .unstar {
background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAKCAQAAADI+WwIAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAJiS0dEAP+Hj8y/AAAACXBIWXMAAAsTAAALEwEAmpwYAAAACXZwQWcAAAALAAAACgCF+qVAAAAAo0lEQVQI1z2NMQrCQBRE32YxGFDwBGJnYZkll0ilpDISLO28k4lFwN7CTsTKSisbK8ELCJFI/BZJdn41j/kzSqiVuZ3r1yzeAAanoajpcOwsW2dxf+0yWGe6CaVzNmgY4VHyQKAiVsIlUlvc9otK4lXugNnJjKqlkpi86X6exIZfNztZBGhKUgr4hBZ3A3UX4ye/CYdeWHch7MOzJ9R3jFLt8we7izGyoi32iQAAAABJRU5ErkJggg==);
  }

  .rating {
    float: left;
    width: 11px;
    height: 10px;
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
	        	<h2>Photos of David</h2>
	        	<div style="padding-top:20px;">
	        		<div id="container">
	        			<p>
							<table>
								<tr>
									<th><a href="photoView.jsp"><img src="images/Achievements.jpg" /></a></th>
									<th>
										Name: David David David David<br/>
										Description: Genius Genius Genius Genius Genius<br/>
										Rating:<br/>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
									</th>
								</tr>
							</table>
							</p>
						<br />
						<p>
							<table>
								<tr>
									<th><a href="photoView.jsp"><img src="images/Bw.jpg" /></a></th>
									<th>
										Name: David David David David<br/>
										Description: Genius Genius Genius Genius Genius<br/>
										Rating:<br/>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating unstar"></span>
									</th>
								</tr>
							</table>
							</p>
						<br />
						<p>
							<table>
								<tr>
									<th><a href="photoView.jsp"><img src="images/Camera.jpg" /></a></th>
									<th>
										Name: David David David David<br/>
										Description: Genius Genius Genius Genius Genius<br/>
										Rating:<br/>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
									</th>
								</tr>
							</table>
							</p>
						<br />
						<p>
							<table>
								<tr>
									<th><a href="photoView.jsp"><img src="images/Cat-Dog.jpg" /></a></th>
									<th>
										Name: David David David David<br/>
										Description: Genius Genius Genius Genius Genius<br/>
										Rating:<br/>
										<span class="rating star"></span>
										<span class="rating star"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
									</th>
								</tr>
							</table>
							</p>
						<br />
						<p>
							<table>
								<tr>
									<th><a href="photoView.jsp"><img src="images/CREATIV.jpg" /></a></th>
									<th>
										Name: David David David David<br/>
										Description: Genius Genius Genius Genius Genius<br/>
										Rating:<br/>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
										<span class="rating unstar"></span>
									</th>
								</tr>
							</table>
							</p>

					    <hr />
					</div>
	        	</div>
			</div>
			<div class="content_section">
				
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
	       		          
        	</div>
        
	        <div class="cleaner_h30"></div>
	        <div class="sidebar_section"></div>

		</div> <!-- end of sidebar -->
		<div id="templatmeo_sidebar_zero">
		</div>

		<div class="cleaner"></div>  
		</div><div id="templatemo_content_wrapper_bottom"></div> <!-- end of content_wrapper -->
		<div id="templatemo_footer">
            Copyright ECE1779 Group 4 | <a href="http://www.utoronto.ca">University of Toronto</a> | 
        </div> <!-- end of templatemo_footer -->
	</div>
</body>
</html>