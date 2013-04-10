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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	img {max-width: 100%;}

	.rating {
    float:left;
}

/* :not(:checked) is a filter, so that browsers that don’t support :checked don’t 
   follow these rules. Every browser that supports :checked also supports :not(), so
   it doesn’t make the test unnecessarily selective */
.rating:not(:checked) > input {
    position:absolute;
    top:-9999px;
    clip:rect(0,0,0,0);
}

.rating:not(:checked) > label {
    float:right;
    width:1em;
    padding:0 .1em;
    overflow:hidden;
    white-space:nowrap;
    cursor:pointer;
    font-size:200%;
    line-height:1.2;
    color:#ddd;
    text-shadow:1px 1px #bbb, 2px 2px #666, .1em .1em .2em rgba(0,0,0,.5);
}

.rating:not(:checked) > label:before {
    content: '';
}

.rating > input:checked ~ label {
    color: #f70;
    text-shadow:1px 1px #c60, 2px 2px #940, .1em .1em .2em rgba(0,0,0,.5);
}

.rating:not(:checked) > label:hover,
.rating:not(:checked) > label:hover ~ label {
    color: gold;
    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

.rating > input:checked + label:hover,
.rating > input:checked + label:hover ~ label,
.rating > input:checked ~ label:hover,
.rating > input:checked ~ label:hover ~ label,
.rating > label:hover ~ input:checked ~ label {
    color: #ea0;
    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

.rating > label:active {
    position:relative;
    top:2px;
    left:2px;
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
	                <li><a href="addPhoto.jsp">Upload Photo</a></li>
	                <li><a href="#">Contact</a></li>
	            </ul>    	
	        </div> <!-- end of templatemo_menu -->

	    
	    	<div class="cleaner"></div>
	    </div> <!-- end of templatemo_header -->
	</div>
	
	<form id="form1" action="/addCommentServlet" method="post">
	<div id="templatemo_content_wrapper">
		
		<div id="templatemo_content">
			<div class="content_section">
				<div style="height:40px">Photo Owner: 
					<a href="/retrieveUserPhotosServlet"><c:out value="${photo.nickName}"/></a>
				</div>
			</div>

	        <div class="content_section">
	        	<div style="padding-top:20px;">
	        		<!-- <img src="images/test.jpg"/> -->
	        		<img src="${photo.url_big}"/>
	        	</div>
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
	                <h2>Comments</h2>
	                <c:forEach var="comment" items="${commentList}">
						<c:out value="${comment.nickName}"/>:
						<c:out value="${comment.description}"/>(
						<c:out value="${comment.rating}"/>)
						<br />
					</c:forEach>    
	       		<br />
					
					<fieldset class="rating">
				    	<legend>Please rate and comment:</legend>
				    	<input type="radio" id="star5" name="rating" value="5" /><label for="star5">5</label>
				    	<input type="radio" id="star4" name="rating" value="4" /><label for="star4">4</label>
				    	<input type="radio" id="star3" name="rating" value="3" checked="checked"/><label for="star3">3</label>
				    	<input type="radio" id="star2" name="rating" value="2" /><label for="star2">2</label>
				    	<input type="radio" id="star1" name="rating" value="1" /><label for="star1">1</label>
					</fieldset>
				<br />
					<textarea name="comment" id="comment" rows="4" cols="30"></textarea><br />
				<br />
				
						<div class="button_01">
							<a href="javascript:;" onclick="document.getElementById('form1').submit();">Submit</a>
							<input type="hidden" value="Submit" />
						</div>
					</form>
					<!--PUT COMMENTS HERE-->
	            </div>           
        	</div>
        
	        <div class="cleaner_h30"></div>
	        <div class="sidebar_section"></div>

		</div> <!-- end of sidebar -->

		<div class="cleaner"></div>  
		<div>
			<div class="desc">Description: ${photo.description}</div>
			<div class="desc" id="desc">Rating: ${photo.rating} / ${photo.commentedTimes}</div>
			<div class="desc">Tags:${photo.tag1} ${photo.tag2} ${photo.tag3} ${photo.tag4} ${photo.tag5}</div>
		</div>
		</div><div id="templatemo_content_wrapper_bottom"></div> <!-- end of content_wrapper -->
		<div id="templatemo_footer">
            Copyright ECE1779 Group 4 | <a href="http://www.utoronto.ca">University of Toronto</a> | 
        </div> <!-- end of templatemo_footer -->
	</div>
</body>
</html>