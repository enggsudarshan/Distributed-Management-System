import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisSalRepOut extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	HttpSession hs=req.getSession(true);
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and dcode ='"+(String)hs.getValue("code")+"'and pcode='"+req.getParameter("pcode")+"'");
		out.println("<html><title>Distributor's Sales Entry </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{font-size:1em;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='E9FFB9' text='maroon'>");
		out.println("<center><h1><font color='blue'>ABC Company's Distributor Sales Report </font></h1>");
		out.println("<hr size=3 color='maroon'>");
		out.println((String)hs.getValue("code"));
		if (!rs.next())
		{	
			out.println("<h3>Sorry No Product was sold on the particular Date !!! ");
			out.println("Try again </h3> ");
			out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
			out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
			out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
			out.println("<image src='http://localhost:8080//dms/images//home1.gif'></a></td></tr></table></center>");
			out.println("</body></html>");
		}
		else
		{
		out.println("<font size=4><center><table border=1><tr><th>Sl.No.</th><th>Invoice Number</th><th>Date</th><th>Quantity</th></tr>");
		rs.close();
		int i=0;
		rs=st.executeQuery("select dscode,ddate,quantity from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and dcode ='"+(String)hs.getValue("code")+"'and pcode='"+req.getParameter("pcode")+"'");
		while(rs.next())
		{

			out.println("<tr><td>"+ ++i +"</td><td>"+rs.getString(1)+"</td><td>");
			String t=rs.getString(2);
			String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
			out.println(d+"</td><td>"+rs.getInt(3)+"</td></tr>");
		}
		out.println("</table>");
		out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms/images//home1.gif'></a></td></tr></table></center>");
		out.println("</body></html>");
		rs.close();
		st.close();
		con.close();
	}}
	catch(Exception e){out.println(e);}
}}