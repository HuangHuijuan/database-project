<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Home Page</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="./css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='http://fonts.googleapis.com/css?family=Questrial'
	rel='stylesheet' type='text/css'>
<link href="./css/popuo-box.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/move-top.js"></script>
<script type="text/javascript" src="./js/easing.js"></script>
</head>
  
  <body style="background-color:#F77268;">
 
  	    

  <jsp:useBean id="user" class="userbean.UserID" scope="session"/><br>
  <h3> Welcome! 
  
    <%
       String usertype=session.getAttribute("usertype").toString();
       if(usertype.equals("Staff")){ %>
   <jsp:getProperty name="user" property="name"/></h3>
   <br>
   <div style="text-align:center;width:400px;margin-top:100px;margin-left:auto;margin-right:auto">
  <a class="know" href="info.jsp" style="width:400px;">My Information</a><br>
  <a class="know" href="insert_s.html" style="width:400px;margin-top:30px">Data Insertion</a><br>

  <a class="know" href="query_s.html" style="width:400px;margin-top:30px">Data Update and Delete</a>  <br> 
   <a class="know" href="s_query.html" style="width:400px;margin-top:30px">Data Query</a>
  </div>
  <%} %>
   <%
       if(usertype.equals("Branch")){ %>
  <br>
   <div style="text-align:center;width:400px;margin-top:150px;margin-left:auto;margin-right:auto">
  <a class="know" href="insert_b.html" style="width:400px;">Data Insertion</a><br>
  <a class="know" href="query_b.html" style="width:400px;margin-top:40px">Data Update and Delete</a><br>
  <a class="know" href="b_query.html" style="width:400px;margin-top:40px">Data Query</a><br>
  </div>
   <%} %>
    
  
  </body>
</html>
