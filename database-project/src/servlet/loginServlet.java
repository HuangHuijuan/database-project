package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.postgresql.*;

import userbean.UserID;

import db.DBManager;
import exception.ExceptionHandler.MyError;

import java.sql.*;


public class loginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginServlet() {
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
		UserID user = new UserID();
		
		
		String sql,username,pwd1,pwd2,name,position,usertype;
		
		try{
		  username=request.getParameter("username").trim();
	      pwd1=request.getParameter("password").trim();
	      usertype=request.getParameter("usertype").trim();
	      if(usertype.equals("Branch"))
	      {
				if (username.equals("B1") && pwd1.equals("1234")) {
					request.getSession().setAttribute("usertype", usertype);
					out.write("success");
					out.flush();
				//	response.sendRedirect(request.getContextPath()
					//		+ "/home.jsp");
				}
				else{
					System.out.print("Wrong username or wrong password!");
					out.write("Wrong username or wrong password!");
					out.flush();
					
				}
	      }
	      else 
          {
	    	
				myDB.getConn("postgres", "950327");
				sql = "select password from password where staffNo='" + username
						+ "'";
				ResultSet rs = myDB.query(sql);
				if (rs.next()) {
					pwd2 = rs.getString(1);
					if (pwd1.equals(pwd2)) {
						sql = "select name,position from staff where staffNo='"
								+ username + "'";
						ResultSet rs1 = myDB.query(sql);
						if (rs1.next()) {
							name = rs1.getString(1);
							position = rs1.getString(2);
							user.setStaffNo(username);
							user.setName(name);
							user.setPosition(position);
							request.getSession().setAttribute("usertype", usertype);
							request.getSession().setAttribute("user", user);
						}
						rs1.close();
						
						System.out.print("success");
						out.write("success");
						out.flush();
					//	response.sendRedirect(request.getContextPath()
					//			+ "/home.jsp");
					} else {
						System.out.print("Wrong Password!");
						out.write("Wrong Password!");
						out.flush();
					}
					rs.close();
					myDB.closeStmt();
					myDB.closeConn();
				} else {
					System.out.print("StaffNo doesn't exist!");
					out.write("StaffNo doesn't exist!");
					out.flush();
					
				}
			}
		}
		catch(Exception e){
			out.print(MyError.getErrorInfo(e.getMessage()));
		}
	}

	private UserID UserID(String sno, String name, String position) {
		// TODO Auto-generated method stub
		return null;
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
