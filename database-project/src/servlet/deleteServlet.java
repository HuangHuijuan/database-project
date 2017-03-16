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

public class deleteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteServlet() {
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

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		DBManager myDB = new DBManager();
	    String table=request.getParameter("table");
	    String sql;
		
	    try {
	    	HttpSession session = request.getSession(true);
			 String usertype = session.getAttribute("usertype").toString();
			 
			if (usertype.equals("Staff")) {
				myDB.getConn("staff", "staff");
				if (table.equals("property")) {
					String propertyno = request.getParameter("propertyno");
					sql = "delete from propertyforrent where propertyno='"+propertyno+"';";
					delete(sql);
					out.write("Delete success!");
					out.flush();
					out.close();
				}
				if (table.equals("client")) {
					String clientno = request.getParameter("clientno");
					sql = "delete from client where clientno='"+clientno+"';";
					delete(sql);
					out.write("Delete success!");
					out.flush();
					out.close();
				}
			}
			else if (usertype.equals("Branch"))
			{
				myDB.getConn("branch", "branch");
			}
			else{
				out.print("Login first!");
			}
		} catch (Exception e) {
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
	
	public void delete(String sql) throws Exception{
		try{
			DBManager myDB = new DBManager();
			myDB.getConn("staff", "staff");
			myDB.update(sql);
			myDB.closeStmt();
			myDB.closeConn();
		}
		catch(Exception e){
			
			  throw new Exception(e.getMessage());
		}
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
