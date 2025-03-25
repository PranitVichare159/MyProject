package com.rolebasedapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Welcome.html")
public class Login extends HttpServlet {
	
	public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        //Taking input from user
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        
        
        //JDBC
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            
            //sql query
            
            String sql ="select * from user_reg where name = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            String name1= req.getParameter("uname");
            String pass= req.getParameter("passwd");

            
            while(rs.next()) {
            	if(name1.equals(rs.getString(1))&& pass.equals(rs.getString(3))){
            	HttpSession session = req.getSession();
            	session.setAttribute("name", name);
            	 res.sendRedirect("Welcome.html");
            	break;
            	}
            	
            
            
            else {
            	 res.sendRedirect("Registration.html");
            	out.println("<h2>Login Faile! Invalid Username or Password.....</h2>");
            	out.println("<a href ='index.html'>Try Again.....");
            	
            }
        }
        }
            catch(Exception e) {
            	out.println("<h2>Error: "+ e.getMessage()+"</h2>");
            	e.printStackTrace();
            	}
            finally {
            	try {
            		if(rs != null)rs.close();
            		if(pstmt != null)pstmt.close();
            		if(conn != null)conn.close();
            		
            	}
            	catch (Exception e){
            		e.printStackTrace();
            	}
            		
            	}
            
            
        }
}
	

            


	


