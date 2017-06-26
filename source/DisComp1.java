import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisComp1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String dis=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet r=st.executeQuery("select district from dsales where dcode='"+req.getParameter("code")+"'");
		if(r.next())
				dis=r.getString(1);
		String dc=req.getParameter("dcode1");
		ResultSet rs=null;
	
	if(dc.equals(null))
		rs=st.executeQuery("select * from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and district='"+dis+"'and pcode='"+req.getParameter("pcode")+"'");
	else
		rs=st.executeQuery("select * from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and dcode ='"+req.getParameter("dcode1")+"'and pcode='"+req.getParameter("pcode")+"'");
		out.println("<html><title>Distributor's Sales Entry Report </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{align:center;font-size:1em;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='E9FFB9' text='maroon'>");
		if (!rs.next())
		{	
			out.println("<center><h1> Distributor Comparison Page</h1>");
			out.println("<hr size=6 color='maroon'>");
			out.println("<h1>No Product was sold on the date !!!<br><br> ");
			out.println("Try again </h1> ");
			out.println("<table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
			out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
			out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
			out.println("<image src='http://localhost:8080//dms/images//home1.gif'></a></td></tr></table></center>");
			out.println("</body></html>");
		}
		else
		{
		rs.close();
		if(dc.equals(null))
		{
			out.println("<center><h2><font color='blue'>ABC Company's District-Wise Report </font></h2>");
			rs=st.executeQuery("select dscode,ddate,quantity from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and district='"+dis+"'and pcode='"+req.getParameter("pcode")+"'");
		}
		else
		{
			out.println("<center><h2><font color='blue'>ABC Company's Distributor Wise Report </font></h2>");
			rs=st.executeQuery("select dscode,ddate,quantity from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and dcode ='"+req.getParameter("dcode1")+"'and pcode='"+req.getParameter("pcode")+"'");
		}
		out.println("<hr size=6 color='maroon'>");
		out.println("<br><table border bordercolor=sliver><tr align=center><th>Sl.No.</th><th>Inv. No.</th><th>Date</th><th>Quantity</th></tr>");
		int i=0;
				while(rs.next())
				{
					out.println("<tr align=center><td>"+ ++i +"</td><td>"+rs.getString(1)+"</td><td>");
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