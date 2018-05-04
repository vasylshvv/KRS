<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.11.4.custom/jquery-ui.css">
	<script type="text/javascript"></script>
	<script src="jquery/jquery-1.12.0.min.js"></script>
	<script src="jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="scripts/testUpload.js"></script>
	
</head>
<body>
	<div>
		<form action="uploadFile.html" enctype="multipart/form-data" method="post" accept-charset="utf-8" id="form_test">
			<input type="text" name="param">
			<input type="file" name="file">
			
			<input type="button" name="upload" value="Send" id="send">
		</form>
	</div>
</body>
</html>