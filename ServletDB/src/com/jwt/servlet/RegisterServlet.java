package com.jwt.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class RegisterServlet extends HttpServlet {
public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
{
	
response.setContentType("text/html");
PrintWriter out = response.getWriter();
String n = request.getParameter("userName");
String p = request.getParameter("password");
String e = request.getParameter("email");
String c = request.getParameter("language");
Connection con=null;
try{
Class.forName("com.mysql.jdbc.Driver");

String connectionURL = "jdbc:mysql://localhost:3306/userdetails?autoReconnect=true&useSSL=false";
//here "userdetails" is the name of the database
con = DriverManager.getConnection(connectionURL, "root", "root");
//here "root","root" are the username and password respectively.
PreparedStatement ps = con.prepareStatement("insert into USERDETAILS values(?,?,?,?)");
ps.setString(1, n);
ps.setString(2, p);
ps.setString(3, e);
ps.setString(4, c);
int i = ps.executeUpdate();
if (i>0)
System.out.print("You are successfully registered...");
else
	System.out.print("failed");

HttpSession session = request.getSession(true); 
session.setAttribute("userName",n);
session.setAttribute("password",p);
session.setAttribute("email",e);
session.setAttribute("language",c);
response.sendRedirect("HomePage.jsp");

con.close();
out.close();
} catch (Exception e2) {
System.out.println(e2);
}
}
}