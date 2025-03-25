package com.rolebasedapp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show")
public class RegistrationServlet extends HttpServlet {
    

    
  
	

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // fo tking data fo user 
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneno = request.getParameter("phoneno");
        String role = request.getParameter("role");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
             

            // SQL Insert Query with PreparedStatement
          String sql = "INSERT INTO user_reg (name, email, password, phoneno,role) VALUES (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, phoneno);
            pstmt.setString(5, role);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h3>User registered successfully!</h3>");
            } else {
                out.println("<h3>Failed to register user.</h3>");
            }

        } catch (ClassNotFoundException e) {
            out.println("<h3>JDBC Driver Not Found!</h3>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<h3>Database Connection Failed!</h3>");
            e.printStackTrace(out);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(out);
            }
        }
    }
}
