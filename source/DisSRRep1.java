import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisSRRep1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select rsdate,rcode,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+req.getParameter("code")+"'");
		out.println("<html><title>Distributor's Sales Entry Report </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{align:center;font-size:1em;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='E9FFB9' text='maroon'>");
		out.println("<h1><center> Sales Rep's Sale Details</h1>");
		out.println("<hr size=6 color='maroon'><center>");
		if (!rs.next())
		{	
			out.println("<h1>Sorry No Product was sold for the particular Date !!!<br><br> ");
			out.println("Try again </h1> ");
		}
		else
		{
			rs.close();
			rs=st.executeQuery("select rsdate,rcode,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+req.getParameter("code")+"'");
			out.println("<br><table border><tr align=center><th>Sl.No.</th><th>Date</th><th>Rep's Code</th><th>Quantity</th><th>Reason</th></tr>");
			int i=0;
			while(rs.next())
			{
					out.println("<tr align=center><td>"+ ++i +"</td><td>");
					String t=rs.getString(1);
					String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
					out.println(d+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
			}
			out.println("</table>");
		}
		out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms/images//home1.gif'></a></td></tr></table></center>");
		out.println("</center></body></html>");
		rs.close();
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}}