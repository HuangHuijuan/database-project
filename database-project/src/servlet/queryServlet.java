package servlet;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBManager;
import exception.ExceptionHandler.MyError;

public class queryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public queryServlet() {
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

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		DBManager myDB = new DBManager();
	    String fvalue=request.getParameter("fvalue");
	    String sfield=request.getParameter("select_field");
	    String table=request.getParameter("table");
	   
	    String sql;	
 
		try {
			 HttpSession session = request.getSession(true);
			 String usertype = session.getAttribute("usertype").toString();
			 
			if (usertype.equals("Staff")) {
				myDB.getConn("staff", "staff");
				if (table.equals("propertyforrent")) {
					sql = "select * from propertyforrent where " + sfield
							+ "='" + fvalue + "';";
					ResultSet rs = myDB.query(sql);
					if (rs.next()) {
						String staffno;		
						String result = "<table class=\"table_1\"><tr><th>propertyNo</th><th>street</th><th>city</th><th>postcode</th><th>type</th><th>rooms</th><th>rent</th><th>ownerNo</th><th>staffNo</th><th>BranchNo</th><th></th></tr>";
						int count = 0;
						do {
							if(rs.getString(9)==null)
			            		  staffno="" ;
							else 
								staffno=rs.getString(9);
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
									+ staffno
									+ "</td>"
									+ "<td>"
									+ rs.getString(10)
									+ "</td>"
									+ "<td><input type=\"radio\" name=\"propertyno\" id=\"propertyno\" value=\""
									+ rs.getString(1) + "\"></td></tr>";
							count++;

						} while (rs.next());

						result = result
								+ "</table><p>Total: "+count+" record</p><table class='tbtn'><tr><td><input id=\"btn2\" type=\"submit\" value=\"Update\" onclick=\"click1_3()\"></td>"
								+ "<td><input id=\"btn1\" type=\"submit\" value=\"Delete\" onclick=\"click1_2()\"></td></tr></table>";

						out.write(result);
						out.flush();
						out.close();
						// System.out.print(rs.getString(1));
					} else {
						out.write("No relative record!");
					}
					
                  
				}
				if(table.equals("client")){
					sql = "select * from client where " + sfield
							+ "='" + fvalue + "';";
					ResultSet rs = myDB.query(sql);
					if (rs.next()) {
						String result = "<table class=\"table_2\"><tr><th>clientNo</th><th>name</th><th style=\"width:180px\">telno</th><th>preference</th><th>maxRentCanAfford</th><th></th></tr>";
						int count = 0;
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
									+ "<td><input type=\"radio\" name=\"clientno\" id=\"clientno\" value=\""
									+ rs.getString(1) + "\"></td></tr>";
							count++;

						} while (rs.next());

						result = result
								+ "</table><p>Total: "+count+" record</p><table class='tbtn'><tr><td><input id=\"btn2\" type=\"submit\" value=\"Update\" onclick=\"click2_3()\"></td>"
								+ "<td><input id=\"btn1\" type=\"submit\" value=\"Delete\" onclick=\"click2_2()\"></td></tr></table>";

						out.write(result);
						out.flush();
						out.close();
					}
				}
				if(table.equals("lease")){
					
				}
				if(table.equals("viewing")){
					
				}
         
				myDB.closeStmt();
				myDB.closeConn();
			} else if (usertype.equals("Branch")) {
				myDB.getConn("branch", "branch");
                if(table.equals("branch")){
					
				}
				if(table.equals("staff")){
					
				}
				if(table.equals("lease")){
					
				}
                if(table.equals("advert")){
					
				}
			}else{
				out.print("Login first!.");
			}

		} catch (Exception e) {
			if( e instanceof SQLException){
				System.out.print("Error: "+e.getMessage());
				out.print(MyError.getErrorInfo(e.getMessage()));
				}
				else if(e instanceof NullPointerException)
				{
					out.print("Login first!");
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
