import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductMod1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String t=req.getParameter("pcode");
	String n=req.getParameter("pname");
	int p=Integer.parseInt(req.getParameter("amount"));
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){System.out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		st.executeUpdate("update product set  pname ='"+n+"', price = "+p+" where pcode ='"+t+"'");
		out.println("<HTML><HEAD><TITLE>Product Modify Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='#e2e0d2'>");
		out.println("<br><br>");
		out.println("<font size=5><center>ABC Company Welcomes U</center><font><hr color=green>");
		out.println("<center><font size=5><b>");
		out.println("<center><img src='http://localhost:8080/dms/images/stars.gif'>&nbsp;Congratulations!!!! &nbsp;<img src='http://localhost:8080/dms/images/stars.gif'><br><br>You have sucessfully updated a product </center>");
		out.println(" <table><tr><td><br><br><a href='http://localhost:8080/dms/AdminSign.html' target='_top'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td><a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
		out.println("</body></html>");
		st.close();
		con.close();
	}
	catch(Exception ex){out.println(ex);}
}}