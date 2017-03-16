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

public class insertServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public insertServlet() {
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
		String table=request.getParameter("table");
//		System.out.print(table);
//		String s=form.substring(form.length()-1, form.length());
		DBManager myDB = new DBManager();
		
		try {
			
			HttpSession session = request.getSession(true);
		//	System.out.print("......................");
			String usertype = session.getAttribute("usertype").toString();
		//	System.out.print("usertype..............."+usertype);
			if (usertype.equals("Branch")) {
				myDB.getConn("branch", "branch");

			if (table.equals("branch")) {
				String branchno = request.getParameter("branchno");
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String postcode = request.getParameter("postcode");
				String telno1 = request.getParameter("telno1");
				String telno2 = request.getParameter("telno2");
				String telno3 = request.getParameter("telno3");
				String manager = request.getParameter("manager");				
				
				String sql;
				sql="insert into branch values('" + branchno + "','"
						+ street + "','" + city + "','" + postcode + "','"
						+ telno1+"'";
				if(telno2.equals(""))
					sql+=",null";
				else
					sql+=",'"+telno2+"'";
				if(telno3.equals(""))
					sql+=",null";
				else
					sql+=",'"+telno3+"'";
				if(manager.equals(""))
					sql+=",null";
				else
					sql+=",'"+manager+"'";
				sql+=");";
				myDB.update(sql);
				

			}
			if(table.equals("staff")){
				String staffno = request.getParameter("staffno");
				String name = request.getParameter("name");
				String position = request.getParameter("position");
				String sex = request.getParameter("sex");
				String dob = request.getParameter("dob");
				String salary = request.getParameter("salary");
				String branchno = request.getParameter("branchno");
				String supervisor = request.getParameter("supervisor");
				String ad = request.getParameter("ad");

				String sql;
				sql="insert into staff values('" + staffno + "','"
						+ name + "','" + position + "','" + sex + "','"
						+ dob+"','"+salary+"','"+branchno+"'";
				if(supervisor.equals(""))
					sql+=",null";
				else
					sql+=",'"+supervisor+"'";
				if(ad.equals(""))
					sql+=",null";
				else
					sql+=",'"+ad+"'";
				
				sql+=");";
				myDB.update(sql);
				
			}
			if(table.equals("lease")){
				String leaseno = request.getParameter("leaseno");
				String propertyno = request.getParameter("propertyno");
				String deposit = request.getParameter("deposit");
				String sd = request.getParameter("sd");
				String clientno = request.getParameter("clientno");
				String method = request.getParameter("method");
				String duration = request.getParameter("duration");
				String fd = request.getParameter("fd");

				String sql;
				sql="insert into lease values('" + leaseno + "','"
						+ clientno + "','" + propertyno + "','" + method + "','"
						+ deposit+"','"+duration+"','"+sd+"','"+fd+"');";
				
				myDB.update(sql);
				
			}
			if(table.equals("advert")){
				String propertyno = request.getParameter("propertyno");
				String ad = request.getParameter("ad");
				String newspapername = request.getParameter("newspapername");
				String cost = request.getParameter("cost");
				
				String sql="insert into advert values('" + propertyno + "','"
						+ newspapername + "','" + ad + "','" + cost +"');";
								
				String sql_1="select * from newspaper where newspapername='"+newspapername+"';";
				ResultSet rs = myDB.query(sql_1);
				if(rs.next()){			
				myDB.update(sql);
				}
				else
				{
					String sql_3="insert into newspaper(newspapername) values('"+newspapername+"');";
					myDB.update(sql_3);
					myDB.update(sql);
				}
	
			}
			myDB.closeStmt();
			myDB.closeConn();
			}
			else if(usertype.equals("Staff"))
			{
				myDB.getConn("staff", "staff");
			//	System.out.print("Hi..............................."+table);
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
				//	System.out.print("Hi...............................");
					String sql;
					sql="insert into propertyforrent values('" + propertyno + "','"
							+ street + "','" + city + "','" + postcode + "','"
							+ type+"','"
						    + rooms + "','"+ rent + "','"+ ownerno + "'";
					if(staffno.equals(""))
						sql+=",null";
					else
						sql+=",'"+staffno+"'";
					sql+=",'"+branchno+"');";
            //        System.out.print(sql);
			//		System.out.print("Hi...............................");
					myDB.update(sql);
					out.print("Insert data success!");

				}
				if (table.equals("client")) {
				
				}
				myDB.closeStmt();
				myDB.closeConn();
				
			}
			else
			{
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
			}
			else{
				System.out.print(e+"\n");
				out.print("Other Error");
			}
		//	out.println(e);
		//	System.out.print(e.getMessage().indexOf("违反了非空约束")+"...............");
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
