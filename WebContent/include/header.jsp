<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>PF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
      
     <header class="navbar navbar-inverse navbar-fixed-top">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
         	    <span class="sr-only">Toggle navigation</span>
        	    <span class="icon-bar"></span>
        	    <span class="icon-bar"></span>
        	    <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Application Computer Database</a>
          <a class="navbar-brand" href="Dashboard?language=fr">fr |</a>
          <a class="navbar-brand" href="Dashboard?search=${pageWrapper.search}&lignes=${pageWrapper.nbOfLines}&column=${pageWrapper.sort.column}&order=${pageWrapper.sort.order}&language=en">en</a>
	</header>
	</body>
      