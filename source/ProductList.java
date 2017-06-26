import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductList extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
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
		out.println("<center><h2><font color='9900cc'>Product List of Success & Co</font></h2>");
		out.println("<hr>");
		out.println("<body>");
		ResultSet rs=st.executeQuery("select * from product");
		out.println("<table border=2><tr><th>Product Code </th> <th>Product Name</th><th>Amount</th></tr>");
		while(rs.next())
		{
			out.println("<tr align=center><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>");
		}
		out.println("</table>");
		out.println("<hr width='760' align='left'><table width='760' cellpadding='15'>");
		out.println("<tr><td> <p><font face='Verdana, Arial, Helvetica, sans-serif' size='1'>|<a href='http://localhost:8080/dms/home.html'>Home</a>|<a href='http://localhost:8080/dms/profile.html'>About us</a>|");
		out.println("<a href='http://localhost:8080/servlet/ProductList'>Product Catalogue</a> | ");
		out.println("<a href='http://localhost:8080/dms/contact.html'>Contact Us</a> |</font></p>");
		out.println("<p><font face='Verdana, Arial, Helvetica, sans-serif'size='1' color='#003399'>Copyright (c). Success & Co., Ltd. All rights reserved.</font></p></td></tr>");
		out.println("</table>");
		out.println("</center></body></html>");
	}
	catch(Exception ex){out.println(ex);}
}
}