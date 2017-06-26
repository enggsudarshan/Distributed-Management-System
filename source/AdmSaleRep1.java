import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class AdmSaleRep1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String code=req.getParameter("code");
	String report=req.getParameter("report");
	ResultSet rs=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>Sales Report Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:12pt;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#e2e0d2' text='maroon'>");
		if(report.equals("1"))
		{
			out.println("<center><h2><font color='9900cc'><u>Distributor  Sale's Report</u></font></h2>");
			rs=st.executeQuery("select dscode,ddate,quantity from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
		}
		else
		{
			out.println("<center><h2><font color='9900cc'><u>Sales Rep Sale's Report</u> </font></h2>");
			rs=st.executeQuery("select rscode,rsdate,quantity from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
		}
		if(!rs.next())
		{
			out.println("<font size=5>Sorry No data found <br><br> Please try again <br><br>");
		}
		else
		{
			rs.close();
			int i=0;
			if(report.equals("1"))
					rs=st.executeQuery("select dscode,ddate,quantity from dsales where ddate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
			else if(report.equals("2"))
					rs=st.executeQuery("select rscode,rsdate,quantity from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
			out.println("<br><table border bordercolor=sliver><tr align=center><th>Sl.No.</th><th>Inv. No.</th><th>Date</th><th>Quantity</th></tr>");
				while(rs.next())
				{
					out.println("<tr align=center><td>"+ ++i +"</td><td>"+rs.getString(1)+"</td><td>");
					String t=rs.getString(2);
					String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
					out.println(d+"</td><td>"+rs.getInt(3)+"</td></tr>");
				}
				out.println("</table><br>");
		}
		out.println("<table><tr><td><a href='http://localhost:8080/dms/AdminSign.html'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' ></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}