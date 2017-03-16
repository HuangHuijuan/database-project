package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import exception.ExceptionHandler.MyError;

public class registerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public registerServlet() {
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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		DBManager myDB = new DBManager();
		String sno,pwd,confirmpwd,sql;
		
		sno=request.getParameter("username");
		pwd=request.getParameter("pwd");
		confirmpwd=request.getParameter("pwd_1");
		
		try{
			  myDB.getConn("postgres","950327");
		      sql="select staffNo from staff where staffNo='"+sno+"'";
		      ResultSet rs=myDB.query(sql);
		      if(rs.next()){
		    	  sql="select staffNo from password where staffNo='"+sno+"'";
			      ResultSet rs1=myDB.query(sql);
				if (rs1.next()) {
					out.write("The account has already existed!");
					
				} else {
					sql = "insert into password values('" + sno + "','" + pwd
							+ "')";
					myDB.update(sql);
					out.write("Register successfully!");
					
					
				}
		      }
		      else
		      {
		    	  out.write("StaffNo doesn't exist!");
		    	 
		      }
		      rs.close();
			  myDB.closeStmt();
			  myDB.closeConn();
		}
		catch(Exception e){
			out.print(MyError.getErrorInfo(e.getMessage()));
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
