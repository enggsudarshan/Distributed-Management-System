import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class FeedbackRep extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select fno,name,phone,fdate,enquiry from feedback");
		out.println("<html><title>Distributor's Sales Entry </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{font-size:1em;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#ffddbb' text='maroon'>");
		out.println("<center><h1><font color='blue'>Success & Co.,'s Feedback List </font></h1>");
		out.println("<img src='http://localhost:8080/dms/images/redline.gif'>");
		out.println("<font size=4><center><table border=1><tr align=center><th>Feedback No.</th><th>Name</th><th>Phone</th><th>Date<th>Enquiry</th></tr>");
		while(rs.next())
		{
			out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>");
			String t=rs.getString(4);
			String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
			out.println(d+"</td><td>"+rs.getString(5)+"</td></tr>");
		}
		out.println("</table><br><table><tr><td><form><image src='http://localhost:8080/dms/images//back_1.gif' onclick='history.back()'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif'></a></td></tr></table></center>");
		out.println("</body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}}