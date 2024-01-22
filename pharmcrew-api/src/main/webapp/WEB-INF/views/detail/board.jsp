<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- load MUI -->
<link rel="stylesheet" href="<c:url value='/plugin/bootstrap/3.3.5/css/bootstrap.min.css'/>" >
<link rel="stylesheet" href="<c:url value='/plugin/ajax/libs/summernote/0.8.6/summernote.csss'/>" >

<script src="<c:url value='/plugin/bootstrap/3.3.5/js/bootstrap.jss'/>"></script> 
<script src="<c:url value='/plugin/ajax/libs/jquery/2.1.4/jquery.jss'/>"></script> 
<script src="<c:url value='/plugin/ajax/libs/summernote/0.8.6/summernote.jss'/>"></script>
<style>
img { max-width:100%; height:auto; }
</style>
</head>
<body>
	<div class="mui-container">
		<p>${detail}</p>
	</div>
</body>
</html>