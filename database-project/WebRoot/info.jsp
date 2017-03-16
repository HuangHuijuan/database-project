<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="userbean.UserID" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <jsp:useBean id="myDB" scope="session" class="db.DBManager" />
<html>
<head>
<title>Branch Query</title>
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
  
  <body>
    <div style="width:700px;height:450px;margin-top:80px;margin-left:auto;margin-right:auto;background-color:white">
  <form name="form4" method="post" action="modify.jsp">

 <table style="width:350px;height:270px;margin:auto;font-size:18pt;position:absolute;top:120px;left:500px">
  <%!String sql,sno,name,position,sex,dob,branchno,supervisor,assumedDate; %>
    <%
        sno=((UserID)session.getAttribute("user")).getStaffNo();
        position=((UserID)session.getAttribute("user")).getPosition();
     %>
     <tr><td>StaffNo:</td><td> <%=sno %></td></tr>
     <%
     	try {
     		   myDB.getConn("postgres", "950327");
     		   sql = "select * from staff where staffNo='" + sno
     				+ "'";
     		ResultSet rs = myDB.query(sql);
     		if(rs.next())
     		{
     		  name=rs.getString(2);
     		  sex=rs.getString(4);
     		  dob=rs.getString(5);
     		  branchno=rs.getString(7);
     		  supervisor=rs.getString(8);
     		  assumedDate=rs.getString(9);
     		}
     		rs.close();
			myDB.closeStmt();
			myDB.closeConn();
     	} catch (Exception e) {
     		System.out.println(e.getMessage());
     	}
     %>
     <tr><td>Name:</td><td> <%=name %></td></tr>
     <tr><td>Sex: </td><td><%=sex %></td></tr>
     <tr><td>Date Of Birth:</td><td> <%=dob %></td></tr>
     <tr><td>BranchNo:</td><td> <%=branchno %></td></tr>
     <%if(position.equals("Assistant")){ %>
     <tr><td>Supervisor: </td><td><%=supervisor %></td></tr>
     <%} %>
     <%if(position.equals("Manager")){ %>
     <tr><td>Assumed Date:</td><td> <%=assumedDate %></td></tr>
     <%} %>
     </table>
     <input type="submit" value="Modify" style="width:120px;font-size:18pt;position:absolute;top:420px;left:480px">
     </form>
     <form name="form5" action="home.jsp " >
     <input type="submit" value="Back" style="width:120px;font-size:18pt;position:absolute;top:420px;left:680px">
     </form>
     </div>
  </body>
</html>
