import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductView extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>Product's View</title><style>");	
		out.println("input{font-size:13pt;color:blue;}");
		out.println("table{font-size:14pt;color:maroon;}");
		out.println("</style>");
		out.println("<center><h2><font color='9900cc'>Product View</font></h2>");
		out.println("<hr size=6 color='maroon'>");
		out.println("<body bgcolor='#e2e0d2'>");
		ResultSet rs=st.executeQuery("select * from product");
		out.println("<table border=2><tr><th>Product Code </th> <th>Product Name</th><th>Amount</th></tr>");
		while(rs.next())
		{
			out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>");
		}
		out.println("</table>");
		out.println(" <table><tr><td><br><br><a href='http://localhost:8080/dms/AdminSign.html' target='_top'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td><a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
		out.println("</center></body></html>");
	}
	catch(Exception ex){out.println(ex);}
}
}