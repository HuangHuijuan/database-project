package servlet;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import exception.ExceptionHandler.MyError;
import userbean.UserID;

public class modifyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public modifyServlet() {
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
		String sql,sno,name,sex,dob;
		
		sno=((UserID)request.getSession().getAttribute("user")).getStaffNo();
		name=request.getParameter("name");
		sex=request.getParameter("sex");
		dob=request.getParameter("dob");
		
		try {
			myDB.getConn("postgres", "950327");
			sql = "update staff set name='" + name + "',sex='" + sex + "',dob='"+dob+"' where staffno='"+sno+"';";
			myDB.update(sql);
			out.print("Modify successfully!");
			

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
