import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class MailLogin extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String user=req.getParameter("login").toUpperCase();
		String pass=req.getParameter("passwd");
		String code=req.getParameter("code");
		HttpSession hs=req.getSession(true);
		hs.putValue("user",user);
		hs.putValue("code",code);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from login where userid='"+user+"' and passwd='"+pass+"' and code='"+code+"'"); 
			out.println("<html><head><TITLE> ABC Company </TITLE>");
			out.println("<style>");
			out.println("body{min-width:25%;max-width:20%;min-height:80%;max-height:100%;border:6px solid maroon}table{font-size:14pt;color:maroon}input{font-size:14pt;color:blue}");
			out.println("</style></HEAD>");
			out.println("<body bgcolor='E9FFB9' text='maroon'><center>");
			out.println("<h2><img src='http://localhost:8080/dms/images/rose1.gif' width=75><font size=5> ABC Company Welcomes U <img src='http://localhost:8080/dms/images/rose1.gif' width=75></h2><hr color=maroon size=5>");
			if(!rs.next()) 
			{
				res.sendRedirect("http://localhost:8080/dms/mailoptionsinvalid.html");
			}
			else 
			{	
				out.println("<a href='http://localhost:8080/dms/passchange.html'>Change Password</a><br><br>");
				out.println("<a href='http://localhost:8080/dms/addresschange.html'>Change Address</a><br><br>");
			}
		}catch(Exception e){out.println(e);}
		out.println("<a href = 'http://localhost:8080/dms/home.html'>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif' width=40></a>");
}}