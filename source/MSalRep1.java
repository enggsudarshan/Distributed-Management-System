import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class MSalRep1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	HttpSession hs=req.getSession(true);
	String code=req.getParameter("code");
	String report=req.getParameter("report");
	ResultSet rs=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>Representative Sales Report Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:12pt;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#ffddbb' text='blue'>");
		out.println("<center><h2><font color='blue'>Sales Rep Sale's Details </font></h2>");
		out.println("<img src='http://localhost:8080/dms/images/redline.gif'>");
		if(report.equals("1"))
			rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
		else if(report.equals("2"))
			rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
		else
			rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"'");
		if(!rs.next())
		{
			out.println("<font size=5>Sorry No data found <br><br> Please try again <br><br>");
		}
		else
		{
			rs.close();
			if(report.equals("3"))
			{
				out.println("<font size=4><u>Product "+req.getParameter("pcode")+"'s Percentage Report <br></u></font>");
				rs=st.executeQuery("select sum(quantity) from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"'");
				rs.next();
				int tot=rs.getInt(1);
				rs.close();
				rs=st.executeQuery("select rcode,dcode,quantity from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"'");
				out.println("<br><table border bordercolor=sliver><tr align=center><th>Sl.No</th><th>Rep Code</th><th>Area Code</th><th>Quantity</th><th>Percentage</th></tr>");
				int i=0;float total=0.0f;
				while(rs.next())
				{
					out.println("<tr align=center><td>"+ ++i +"</td><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td><td>");
					int temp=rs.getInt(3);
					out.println(temp+"</td><td>"+(float)temp*100/tot+"%</td></tr>");
				}
				out.println("</table>");
				rs.close();
			}
			else
			{
				if(report.equals("1"))
			
					rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and rcode='"+code+"'");
				else if(report.equals("2"))
					rs=st.executeQuery("select rscode,rsdate,quantity,reason from rsales where rsdate between #"+req.getParameter("fdate")+"# and #"+req.getParameter("tdate")+"# and pcode='"+req.getParameter("pcode")+"' and dcode='"+code+"'");
				out.println("<br><table border bordercolor=sliver><tr align=center><th>Inv. No.</th><th>Date</th><th>Quantity</th><th>Reason</th></tr>");
				while(rs.next())
				{
					out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>");
					String t=rs.getString(2);
					String d=t.substring(8,10)+"/"+t.substring(5,7)+"/"+t.substring(0,4);
					out.println(d+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
				}
				out.println("</table><br>");
			}
		}
		out.println("<table><tr><td><input type=image src='http://localhost:8080/dms/images/back_1.gif' onClick='javaScript:history.back()'>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' ></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}