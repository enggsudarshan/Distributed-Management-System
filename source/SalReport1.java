import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalReport1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	HttpSession hs=req.getSession(true);
	String code=(String)hs.getValue("code");
	String report=req.getParameter("report");
	ResultSet rs=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>Representative Sales Report Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:12pt;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#eeeee3' text='maroon'>");
		out.println("<center><h2><font color='blue'>Sales Rep Sale's Report Details </font></h2>");
		out.println("<img src='http://localhost:8080/dms/images/flashing_line.gif'>");
		if(report.equals("1"))
			rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
		else
		{
			rs=st.executeQuery("select dcode from rep where scode='"+code+"'");
			rs.next();
			code=rs.getString(1);
			rs.close();
			rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
		}
		if(!rs.next())
		{
			out.println("<font size=5>Sorry No data found <br><br> Please try again ");
		}
		else
		{
			rs.close();
			if(report.equals("1"))
						rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
			else
			{
						rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
						out.println("<h4>Area Wise Report </h4>");
			}
			out.println("<table border=1><tr align=center><th>Inv. No.</th><th>Date</th><th>Quantity</th><th>Reason</th></tr>");
			//out.println("<table border=1><th><td>Inv. No.</td><td>Date</td><td>Quantity</td><td>Reason</td></th>");
			while(rs.next())
			{
				out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>");
				String t=rs.getString(2);
				String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
				out.println(d+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
			}
			out.println("</table><br>");
		}
		out.println("<table><tr><td><input type=image src='http://localhost:8080/dms/images//back_1.gif' onClick='javaScript:history.back()'>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' width='40'></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}