import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisCompRep extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	out.println("<html><title>Report Page</title><style>");	
	out.println("input{font-size:15pt;color:blue;}");
	out.println("body{font-size:15pt;color:maroon;}");
	out.println("table{font-size:15pt;color:maroon;}");
	out.println("</style>");
	out.println("<center><h1><font color='blue'>Distributor's Report Page</font></h1>");
	out.println("<hr size=6 color='maroon'>");
	out.println("<body bgcolor='E9FFB9'>");
	out.println("<center><form action='http://localhost:8080/servlet/DisSRRep' method='post'>");
	out.println("<table cellspacing=25><tr align=center><td><input type='submit' name='salesrep' value='Sales Rep's Report'></td></tr></form>");
	out.println("<tr align=center><td><form action='http://localhost:8080/servlet/DisComp' method='post'>");
	out.println("<input type='submit' name='d1' value='DistrictWiseReport'></td></tr>");
	out.println("<tr align=center><td><input type='submit' name='d1' value='DistributorWiseReport'></td></tr></table></form>");
	out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
	out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
	out.println("</td><td><a href = 'http://localhost:8080/dms/home.html'>");
	out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
	out.println("</center></body></html>");
}	}