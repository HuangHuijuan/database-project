package db;
import org.postgresql.*;

import exception.ExceptionHandler.MyError;

import java.sql.*;

public class DBManager{
	 private Statement stmt = null;     
	 ResultSet rs = null;     
	 private Connection conn = null;     
	 private String url="jdbc:postgresql://localhost:5432/DreamHome";  
	 
	 //构造函数     
	 public DBManager() { }     
	 
	 //根据dsn参数，加载驱动程序，建立连接     
	 public void getConn(String uid, String pwd) throws Exception{
		 try
		 {                   
			 Class.forName( "org.postgresql.Driver" ).newInstance();           
			 conn = DriverManager.getConnection(url, uid, pwd); 
			 System.err.println("DB connecting...");
	     }catch (Exception ex)
	     {              
		     System.err.println(ex.getMessage());     
		     throw new Exception(ex.getMessage());
		 }
	 }
	 //执行查询类的SQL语句，有返回集     
	 public ResultSet query(String sql) throws SQLException     
	 {          
		 rs = null;         
		 try         
		 {         
			 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);             
			 rs = stmt.executeQuery(sql);
			 System.err.println("Doing query...");
		  } 
		 catch(SQLException ex)        
	     {              
			  System.err.println(ex.getMessage());    
			  throw new SQLException(ex.getMessage());
		 }          
		 return rs;     
     }  
	 //执行更新类的SQL语句，无返回集     
	 public void update(String sql) throws Exception     
	 {          
		 stmt = null;         
		 rs=null;         
		 try         
		 {         
			 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);             
			 stmt.executeQuery(sql);             
			 stmt.close();             
			 conn.close();         
		 }          
		 catch(SQLException ex)         
		 {              
			 System.err.println("aq.executeQuery: " + ex.getMessage()); 
			 if(MyError.getErrorInfo(ex.getMessage()).equals(""))
					;
			 else 
				 throw new SQLException(ex.getMessage());
		 }     
	}
	 //关闭对象
	 public void closeStmt() throws SQLException     
	 { 
	        try
	        {   
	        	stmt.close();   
	        }         
	        catch(SQLException ex)         
	        {              
	        	System.err.println("aq.closeStmt: " + ex.getMessage());      
	        	throw new SQLException(ex.getMessage());
	        }     
	 }
	 public void closeConn() throws SQLException     
	 {          
		 try
		 {   
			 conn.close();  
		 }         
		 catch(SQLException ex)         
		 {              
			 System.err.println("aq.closeConn: " + ex.getMessage());      
			 throw new SQLException(ex.getMessage());
		 }     
	 } 
}