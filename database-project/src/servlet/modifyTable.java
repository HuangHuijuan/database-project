package servlet;

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

public class modifyTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public modifyTable() {
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
		PrintWriter out = response.getWriter();
		DBManager myDB = new DBManager();
		String sql;
		String table=request.getParameter("table");
	    HttpSession session = request.getSession(true);
		String usertype = session.getAttribute("usertype").toString();
	//	System.out.print("hi...............");
		try {
			if (usertype.equals("Branch")) {
				myDB.getConn("branch", "branch");
				if (table.equals("branch")) {
				
				}
				if (table.equals("staff")) {
					
				}
				if (table.equals("lease")) {
					
				}
				if (table.equals("advert")) {
					
				}
				myDB.closeStmt();
				myDB.closeConn();
			}
			if (usertype.equals("Staff")) {
				myDB.getConn("staff", "staff");
                if (table.equals("property")) {
                	String propertyno=request.getParameter("propertyno");
        			String street=request.getParameter("street");
        			String city=request.getParameter("city");
        			String postcode=request.getParameter("postcode");
        			String type=request.getParameter("type");
        			String rooms=request.getParameter("rooms");
        			String rent=request.getParameter("rent");
        			String ownerno=request.getParameter("ownerno");
        			String staffno=request.getParameter("staffno");
        			String branchno=request.getParameter("branchno");
        			
        			sql = "update propertyforrent set propertyno='" + propertyno
        					+ "',street='" + street + "',city='" + city + "',postcode='"
        					+ postcode + "',type='" + type + "',rooms='" + rooms
        					+ "',rent='" + rent + "',ownerno='" + ownerno + "',staffno=";
        			if(staffno.equals(""))
						sql+="null,";
					else
						sql+="'"+staffno+"',";
					sql+="branchno='"+branchno+ "' where propertyno='" + propertyno + "';";
					System.out.print(sql);
        			myDB.update(sql);
        			out.write("Modify successfully!");
				}
                if (table.equals("client")) {
                	String clientno=request.getParameter("clientno");
        			String name=request.getParameter("name");
        			String telno=request.getParameter("telno");
        			String pref=request.getParameter("pref");
        			String max=request.getParameter("max");
        			
        			sql = "update client set name='" + name
        					+ "',telno='" + telno + "',preftype='" + pref + "',maxrent='"
        					+ max + "' where clientno='" + clientno + "';";
				//	System.out.print(sql);
        			myDB.update(sql);
        			out.write("Modify successfully!");
					
				}
                if (table.equals("lease")) {
					
				}
                if (table.equals("viewing")) {
					
				}
			myDB.closeStmt();
			myDB.closeConn();
			}
			else
			{
				out.write("Login first!");
			}
		}
		catch(Exception e){
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
