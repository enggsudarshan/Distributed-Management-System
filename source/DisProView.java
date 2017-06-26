import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisProView extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from product");
		out.println("<html><title>Distributor's Sales Entry </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{font-size:1em;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='E9FFB9' text='maroon'>");
		out.println("<center><h1><font color='blue'>ABC Company's Product List </font></h1>");
		out.println("<hr size=3 color='maroon'>");
		out.println("<font size=4><center><table border=1><tr align=center><th>Product Code</th><th>Product Name</th><th>Amount</th></tr>");
		while(rs.next())
		{
			out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>");
		}
		out.println("<font><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif'></a></td></tr></table></center>");
		out.println("</body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}}