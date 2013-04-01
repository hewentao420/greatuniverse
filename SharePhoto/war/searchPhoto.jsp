<head>
<script type="text/javascript" src="script/ajaxRequest.js"></script>
<script type="text/javascript">
function getAjaxJson()
{  
  httpGet("searchPhotoServlet", "GET", "msg=123"); 
}
</script>
</head>
<body>
<strong>AJAX JSON serialization example:</strong>
<hr>
<input type="button" value="Retrieve" onclick="getAjaxJson()">
<hr>
<div id="showout"></div>
</body>