import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class SalProdView extends HttpServlet
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
		ResultSet rs=st.executeQuery("select * from product");
		out.println("<html><title>Distributor's Sales Entry </title> <style>");	
		out.println("input{font-size:12pt;color:blue;};table{font-size:1em;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#eeeee3' text='maroon'>");
		out.println("<center><h1><font color='blue'>ABC Company's Product List </font></h1>");
		out.println("<img src='http://localhost:8080/dms/images/flashing_line.gif'>");
		out.println("<font size=4><center><table border=1><tr align=center><th>Product Code</th><th>Product Name</th><th>Amount</th></tr>");
		while(rs.next())
		{
			out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>");
		}
		out.println("</table><br><table><tr><td><a href='http://localhost:8080/dms/salentryframe.html' target=_top><img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' width='40'></a></td></tr></table></center>");
		out.println("</body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}}