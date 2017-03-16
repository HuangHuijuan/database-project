package servlet;

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBManager;
import exception.ExceptionHandler.MyError;

public class transactionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public transactionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		DBManager myDB = new DBManager();		
	    HttpSession session = request.getSession(true);
	    String sql;	
	    
	    try{
	    	String usertype = session.getAttribute("usertype").toString();
	    	String option=request.getParameter("option");
	    if(usertype.equals("Branch"))
	    {
	    	myDB.getConn("branch", "branch");
	    	if(option.equals("1"))
	    	{
	    		String branchno = request.getParameter("branchno");
	    		sql="select * from branch where branchno='"+branchno+"';";
	    		ResultSet rs = myDB.query(sql);
	    		if(rs.next())
	    		{
	    			String telno2=rs.getString(6);
	    			String telno3=rs.getString(7);
	    			String manager=rs.getString(8);
	    			if(telno2==null)
	    				telno2="";
	    			if(telno3==null)
	    				telno3="";
	    			if(manager==null)
	    				manager="";
	    			
	    			String result="<table class=\"bdetail\"><tr><th>branchno</th><th>street</th><th>city</th><th>postcode</th><th>telno1</th><th>telno2</th><th>telno3</th><th>manager</th></tr>"
	    		                   +"<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"
	    		                   +telno2+"</td><td>"+telno3+"</td><td>"+manager+"</td></tr></table>";
	    			out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("2"))
	    	{
	    		String city = request.getParameter("city");
	    		sql="select count(*) from branch where city='"+city+"';";
	    		ResultSet rs=myDB.query(sql);
	    		if(rs.next())
	    		{
	    			out.write(rs.getString(1));
	    			
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("3"))
	    	{
	    		String branchno = request.getParameter("branchno");
	    		sql="select name,position,salary from staff where branchno='"+branchno+"' order by name;";
	    		ResultSet rs=myDB.query(sql);
	    		if(rs.next())
	    		{
	    			String result="<table class=\"bdetail\"><tr><th>name</th><th>position</th><th>salary</th></tr>";
	    			do{
	    			result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td></tr>";
	    			}while(rs.next());
	    			result+="</table>";
	    			out.print(result);
	
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("4"))
	    	{
	    		sql="select count(*),sum(salary) from staff;";
	    		ResultSet rs=myDB.query(sql);
	    		if(rs.next())
	    		{
						String result = "<table class=\"bdetail\"><tr><th style=\"width:280px\">Total number of staff</th><th style=\"width:280px\">Sum of salary</th></tr>"
								+ "<tr><td>"+ rs.getString(1)+"</td><td>"
								
								+ rs.getString(2) + "</td></tr></table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("5"))
	    	{
	    		String city = request.getParameter("city");
	    		sql="select position,count(*) from staff s,branch b where s.branchno=b.branchno and b.city='"+city+"' group by position;";
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
						String result = "<table class=\"bdetail\"><tr><th>position</th><th>numOfStaff</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("6"))
	    	{
	    		sql="select branchno,manager from branch order by street;";
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
						String result = "<table class=\"bdetail\"><tr><th>branchno</th><th>manager</th></tr>";
					do{
						if(rs.getString(2)!=null)
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("7"))
	    	{
	    		String supervisor = request.getParameter("supervisor");
	    		sql="select name from staff where supervisor='"+supervisor+"';";
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
						String result = "<table><tr>";
					do{
						
						result+="<td width=\"150px\"><h5>"+rs.getString(1)+"</h5></td>";
					}while(rs.next());
					result+="</tr></table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("8"))
	    	{
	    		String city = request.getParameter("city");
	    		sql="select propertyno,street,city,postcode,type,rent from propertyforrent where city='"+city+"' order by rent;";
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>propertyno</th><th>street</th><th>city</th><th>postcode</th><th>type</th><th>rent</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("9"))
	    	{
	    		String name = request.getParameter("name");
	    		sql="select * from propertyforrent p,staff s where p.staffno=s.staffno and s.name='"+name+"';";
	    		ResultSet rs=myDB.query(sql);
	    		String result = "<table class=\"bdetail\"><tr><th>propertyNo</th><th>street</th><th>city</th><th>postcode</th><th>type</th><th>rooms</th><th>rent</th><th>ownerNo</th><th>staffNo</th><th>BranchNo</th></tr>";
	    		if(rs.next())
	    		{
	    			do {
						
						result = result
								+ "<tr><td>"
								+ rs.getString(1)
								+ "</td>"
								+ "<td>"
								+ rs.getString(2)
								+ "</td>"
								+ "<td>"
								+ rs.getString(3)
								+ "</td>"
								+ "<td>"
								+ rs.getString(4)
								+ "</td>"
								+ "<td>"
								+ rs.getString(5)
								+ "</td>"
								+ "<td>"
								+ rs.getString(6)
								+ "</td>"
								+ "<td>"
								+ rs.getString(7)
								+ "</td>"
								+ "<td>"
								+ rs.getString(8)
								+ "</td>"
								+ "<td>"
								+ rs.getString(9)
								+ "</td>"
								+ "<td>"
								+ rs.getString(10)
								+"</td></tr>";
						

					} while (rs.next());

					result = result
							+ "</table>";

					out.write(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("10"))
	    	{
	    		String branchno = request.getParameter("branchno");
	    		sql="select staffno,count(propertyno) from propertyforrent where branchno='"+branchno+"' group by staffno;";
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>staffno</th><th>numOfProperties</th></tr>";
					do{
						if(rs.getString(1)!=null)
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("11"))
	    	{
	    		String branchno = request.getParameter("branchno");
	    		sql="select * from propertyforrent p,businessowner b where p.ownerno=b.ownerno and branchno='"+branchno+"';";
	    		ResultSet rs=myDB.query(sql);
	    		
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>propertyNo</th><th>street</th><th>city</th><th>postcode</th><th>type</th><th>rooms</th><th>rent</th><th>ownerNo</th><th>staffNo</th><th>BranchNo</th></tr>";
	    			do {
						
						result = result
								+ "<tr><td>"
								+ rs.getString(1)
								+ "</td>"
								+ "<td>"
								+ rs.getString(2)
								+ "</td>"
								+ "<td>"
								+ rs.getString(3)
								+ "</td>"
								+ "<td>"
								+ rs.getString(4)
								+ "</td>"
								+ "<td>"
								+ rs.getString(5)
								+ "</td>"
								+ "<td>"
								+ rs.getString(6)
								+ "</td>"
								+ "<td>"
								+ rs.getString(7)
								+ "</td>"
								+ "<td>"
								+ rs.getString(8)
								+ "</td>"
								+ "<td>"
								+ rs.getString(9)
								+ "</td>"
								+ "<td>"
								+ rs.getString(10)
								+"</td></tr>";
						

					} while (rs.next());

					result = result
							+ "</table>";

					out.write(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("12"))
	    	{
	    		sql="select branchno,sum(case when type='Flat' then 1 else 0 end) as flat,"
	    			+"sum(case when type='House' then 1 else 0 end) as house "
	    			+"from propertyforrent "
	    			+"group by branchno "
                    +"order by branchno;";

	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>branchNo</th><th>numOfFlat</th><th>numOfHouse</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	
	    	if(option.equals("13"))
	    	{
	    		sql="select o.* from privateowner o, propertyforrent p "
	    		    +"where o.ownerno=p.ownerno "
	    			+"group by o.ownerno "
	    			+"having count(propertyno)>1;";

	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>ownerNo</th><th>name</th><th style=\"width:300px\">address</th><th style=\"width:200px\">telNo</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("14"))
	    	{
	    		String city =request.getParameter("city");
	    		sql="select * from propertyforrent where type='Flat' and rooms>2 and rent<=350 and city='"+city+"';";

	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>propertyNo</th><th>street</th><th>city</th><th>postcode</th><th>type</th><th>rooms</th><th>rent</th><th>ownerNo</th><th>staffNo</th><th>BranchNo</th></tr>";
	    			do {
						
						result = result
								+ "<tr><td>"
								+ rs.getString(1)
								+ "</td>"
								+ "<td>"
								+ rs.getString(2)
								+ "</td>"
								+ "<td>"
								+ rs.getString(3)
								+ "</td>"
								+ "<td>"
								+ rs.getString(4)
								+ "</td>"
								+ "<td>"
								+ rs.getString(5)
								+ "</td>"
								+ "<td>"
								+ rs.getString(6)
								+ "</td>"
								+ "<td>"
								+ rs.getString(7)
								+ "</td>"
								+ "<td>"
								+ rs.getString(8)
								+ "</td>"
								+ "<td>"
								+ rs.getString(9)
								+ "</td>"
								+ "<td>"
								+ rs.getString(10)
								+"</td></tr>";
						

					} while (rs.next());

					result = result
							+ "</table>";

					out.write(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("15"))
	    	{
	    		String branchno =request.getParameter("branchno");
	    		sql="select c.clientno,name,telno,preftype "
                    +"from client c,registration r "
                    +"where c.clientno=r.clientno and branchno='"+branchno+"';";
          //      System.out.print(sql);
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>clientNo</th><th>name</th><th style=\"width:200px\">telNo</th><th>preference</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("16"))
	    	{
	    		sql="select propertyno "
	    			+"from advert "
	    			+"group by propertyno "
	    			+"having count(*)>(select avg(adtime) from(select propertyno,count(*) as adtime "
	    			+"from advert group by propertyno) as a);";
          //      System.out.print(sql);
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table><tr><td style=\"width:90px;font-size:18pt\">Result :</td>";
					do{
						result+="<td style=\"width:120px;font-size:18pt\">"+rs.getString(1)+"</td>";
					}while(rs.next());
					result+="</tr></table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("17"))
	    	{
	    		sql="select count(*) from lease l,branch b, propertyforrent p "
	    		+"where l.propertyno=p.propertyno and p.branchno=b.branchno and l.duration<'1 year' and b.city='London';";
          //      System.out.print(sql);
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table><tr><td style=\"width:90px;font-size:18pt\">Result :</td>";
					do{
						result+="<td style=\"width:120px;font-size:18pt\">"+rs.getString(1)+"</td>";
					}while(rs.next());
					result+="</tr></table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	if(option.equals("18"))
	    	{
	    		sql="select branchno,round(sum(rent)/30,2) as dailyrental "
                    +"from propertyforrent group by branchno;";
          //      System.out.print(sql);
	    		ResultSet rs=myDB.query(sql);
	    		//System.out.print(sql);
	    		if(rs.next())
	    		{
	    			String result = "<table class=\"bdetail\"><tr><th>branchNo</th><th>dailyRental</th></tr>";
					do{
						result+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>";
					}while(rs.next());
					result+="</table>";
						out.print(result);
	    		}
	    		else
	    		    out.print("No relative data!");
	    	}
	    	out.flush();
			out.close();
	    	myDB.closeStmt();
			myDB.closeConn();
	    }
	    else if(usertype.equals("Staff"))
	    {
	    	myDB.getConn("staff", "staff");
	    }
	    else
	    {
	    	out.print("Login first!");
	    }
	    }
	    catch(Exception e)
	    {
	    	if( e instanceof SQLException){
				System.out.print("Error: "+e.getMessage());
				out.print(MyError.getErrorInfo(e.getMessage()));
				}
				else if(e instanceof NullPointerException)
				{
					out.print("Login first!");
					System.out.print(e);
				}
				else{
					System.out.print(e+"\n");
					out.print("Other Error");
				}
	    }
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
