import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalDetail1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		PreparedStatement ps=con.prepareStatement("insert into rsales values(?,?,?,?,?,?,?)");
		ps.setString(1,req.getParameter("rscode"));
		ps.setString(2,req.getParameter("rsdate"));
		ps.setString(3,req.getParameter("rcode"));
		ps.setString(4,req.getParameter("dcode"));
		ps.setString(5,req.getParameter("pcode"));
		ps.setInt(6,new Integer(req.getParameter("quan")).intValue());
		ps.setString(7,req.getParameter("reason"));
		ps.execute();
		out.println("<html><title>Representative Sales Entry Form</title> <style>");	
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon;font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("<center><h1><font color='blue'>ABC Company Sales Acceptance Page </font></h1>");
		out.println("<body bgcolor='#eeeee3' text='maroon'><pre>");
		out.println("<br><br>Your Sale has been successfully added to ur Distributor<br><br><br>");
		out.println("</pre>");
		out.println("<table><tr><td><a href='http://localhost:8080/dms/salentryframe.html' target=_top><img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' width='40'></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		ps.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}