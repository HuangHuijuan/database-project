<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="userbean.UserID"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <jsp:useBean id="myDB" scope="session" class="db.DBManager" />
<html>
<head>
<title>Modify My Info</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="./css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Questrial'
	rel='stylesheet' type='text/css'>
<link href="./css/popuo-box.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/move-top.js"></script>
<script type="text/javascript" src="./js/easing.js"></script>
</head>

<body>
<div style="width:700px;height:450px;text-align:center;margin-top:80px;margin-left:auto;margin-right:auto;background-color:white">
	<form action="info.jsp">
		<%!String sql, sno, name, position, sex, dob, branchno, supervisor,
			assumedDate;%>
		<%
			sno = ((UserID) session.getAttribute("user")).getStaffNo();
			position = ((UserID) session.getAttribute("user")).getPosition();
		%>
		<table style="width:450px;height:300px;font-size:18pt;position:absolute;top:120px;left:450px"><tr><td>StaffNo:</td><td>
		<%=sno%></td></tr>
		<%
			try {
				myDB.getConn("postgres", "950327");
				sql = "select * from staff where staffNo='" + sno + "'";
				ResultSet rs = myDB.query(sql);
				if (rs.next()) {
					name = rs.getString(2);
					sex = rs.getString(4);
					dob = rs.getString(5);
					branchno = rs.getString(7);
					supervisor = rs.getString(8);
					assumedDate = rs.getString(9);
				}
				rs.close();
				myDB.closeStmt();
				myDB.closeConn();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		%>
		<tr><td>Name:</td><td> <input type="text" id="name" value="<%=name%>"></td></tr>
		<tr><td>Sex:</td><td> <input type="text" id="sex" value="<%=sex%>"></td></tr>
		<tr><td>Date Of Birth:</td><td> <input type="text" id="dob" value="<%=dob%>"></td></tr>
		<tr><td>BranchNo:</td><td>
		<%=branchno%></td></tr>
		<%
			if (position.equals("Assistant")) {
		%>
		<tr><td>Supervisor:</td><td>
		<%=supervisor%></td></tr>
		<%
			}
		%>
		<%
			if (position.equals("Manager")) {
		%>
		<tr><td>Assumed Date:</td><td>
		<%=assumedDate%></td></tr>
		<%
			}
		%>
		</table>
		<input type="submit" value="Cancel" style="width:120px;font-size:18pt;position:absolute;top:450px;left:450px">
	</form>
	
		<input type="submit" value="OK" onclick="check()" style="width:120px;font-size:18pt;position:absolute;top:450px;left:650px">
	
	</div>
	<script language="javascript">
	function check(){
   var name=document.getElementById("name").value;
   var sex=document.getElementById("sex").value;
   var dob=document.getElementById("dob").value;
   if(name==""){
    alert("Please enter your name!");
    return false;
   }
      
   if(sex==""){
    alert("Please enter your sex!");
    return false;
   }
    
   if(dob==""){
   alert("Please enter your birthday!");  
   return false;
   }
   
   var xmlhttp;
			
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

		}

		xmlhttp.open("POST", "servlet/modifyServlet", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
		xmlhttp.onreadystatechange = function() {
		//alert(xmlhttp.readyState+" " + xmlhttp.status);
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {		
			var data = xmlhttp.responseText;
			if(data=="Modify successfully!"){
			         alert(data);
                     window.location.href="info.jsp";
            }else{ 
				alert( data);
				}
			}
		};

		xmlhttp.send("name=" + name
				+ "&&sex=" + sex + "&&dob=" + dob );
	}
          
	</script>
</body>
</html>
