package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBManager;
import exception.ExceptionHandler.MyError;

public class updateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public updateServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	    String result;
    
		try {
			HttpSession session = request.getSession(true);
			 String usertype = session.getAttribute("usertype").toString();
			 
			if (usertype.equals("Staff")) {
				myDB.getConn("staff", "staff");
				if (table.equals("property")) {
					 String propertyno=request.getParameter("propertyno");
                     sql="select * from propertyforrent where propertyno='"+propertyno+"';";
                     ResultSet rs = myDB.query(sql);
                     if(rs.next()){
            	        String staffno;
				        if(rs.getString(9)==null)
            		       staffno="" ;
				        else 
					    staffno=rs.getString(9);
					    result = "<table class=\"table\"><tr><td>PropertyNo:</td><td><input style=\"display:none;\" id=\"propertyno_1\" type=\"text\" value=\""
							+ rs.getString(1) +"\">"+ rs.getString(1)+"</td>" + "<td>Street:</td><td><input id=\"street\" type=\"text\" value=\""
							+ rs.getString(2) +"\"></td></tr>" + "<tr><td>City:</td><td><input id=\"city\" type=\"text\" value=\""
							+ rs.getString(3) +"\"></td>" + "<td>Postcode:</td><td><input id=\"postcode\" type=\"text\" value=\""
							+ rs.getString(4) +"\"></td></tr>" + "<tr><td>Type:</td><td><input id=\"type\" type=\"text\" value=\""
							+ rs.getString(5) +"\"></td>" + "<td>Rooms:</td><td> <input id=\"rooms\" type=\"text\" value=\""
							+ rs.getString(6) +"\"></td></tr>" + "<tr><td>Rent:</td><td><input id=\"rent\" type=\"text\" value=\""
							+ rs.getString(7) +"\"></td>" + "<td>OwnerNo:</td><td><input id=\"ownerno\" type=\"text\" value=\""
							+ rs.getString(8) +"\"></td></tr>" + "<tr><td>StaffNo:</td><td><input id=\"staffno\" type=\"text\" value=\""
							+ staffno +"\"></td>" + "<td>BranchNo:</td><td><input id=\"branchno\" type=\"text\" value=\""
							+ rs.getString(10) +"\"></td></tr>" +"<tr><td colspan=\"2\"><br><br><br><input id=\"btn1\" type=\"submit\" value=\"Cancel\" onclick=\"click1_1()\"></td>"
							+"<td colspan=\"2\"><br><br><br><input id=\"btn2\" type=\"submit\" value=\"OK\" onclick=\"click1_4()\"></td></tr></table>";
							
							
					    out.write(result);
				        out.flush();
					    out.close();
					    //System.out.print(rs.getString(1));
                   }
				}
				if (table.equals("client")) {
					 String clientno=request.getParameter("clientno");
					 
                     sql="select * from client where clientno='"+clientno+"';";
                     ResultSet rs = myDB.query(sql);
                     if(rs.next()){            	    
					    result = "<table class=\"table\"><tr><td>ClientNo:</td><td><input style=\"display:none;\" id=\"clientno_1\" type=\"text\" value=\""
							+ rs.getString(1) +"\">"+ rs.getString(1)+"</td>" + "<td>Name:</td><td><input id=\"name\" type=\"text\" value=\""
							+ rs.getString(2) +"\"></td></tr>" + "<tr><td>TelNo:</td><td><input id=\"telno\" type=\"text\" value=\""
							+ rs.getString(3) +"\"></td>" + "<td>Preference:</td><td><input id=\"pref\" type=\"text\" value=\""
							+ rs.getString(4) +"\"></td></tr>" + "<tr><td>MaxRentCanAfford:</td><td><input id=\"max\" type=\"text\" value=\""
							+ rs.getString(5) +"\"></td><td></td></tr>" +"<tr><td colspan=\"2\"><br><br><br><input id=\"btn1\" type=\"submit\" value=\"Cancel\" onclick=\"click2_1()\"></td>"
							+"<td colspan=\"2\"><br><br><br><input id=\"btn2\" type=\"submit\" value=\"OK\" onclick=\"click2_4()\"></td></tr></table>";
							
							
					    out.write(result);
				        out.flush();
					    out.close();
				}
                     
                     
			   }
			}
		    else if (usertype.equals("Branch")) {
			    myDB.getConn("branch", "branch");
		    }
			else{
				out.print("Login first!");
			}
			myDB.closeStmt();
			myDB.closeConn();
			
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
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
