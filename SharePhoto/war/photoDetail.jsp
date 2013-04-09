<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
key: <c:out value="${key}"/> <br />
nick name: <c:out value="${nickName}"/> <br />
time: <c:out value="${time}"/> <br />
aperture: <c:out value="${aperture}"/> <br />
iso: <c:out value="${iso}"/> <br />
shutter speed: <c:out value="${shutter_speed}"/> <br />


<c:forEach var="comment" items="${comments}">
	Comments - nick name: <c:out value="${comment.nickName}"/>
	<br />
	Comments - description: <c:out value="${comment.description}"/>
	<br />
</c:forEach>

</body>
</html>