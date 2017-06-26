import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductDel extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String type=req.getParameter("type");
	String file=null;
	String button=null;String title=null;
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>");
		if(type.equals("1"))
		{
			out.println("Product Delete");
			file="http://localhost:8080/servlet/ProductDel1";
			button="Delete";
			title="Deletion of Product";
		}
		else
		{
			out.println("Product Modify");
			file="http://localhost:8080/servlet/ProductMod";
			button="Modify";
			title="Product Modification";
		}
		out.println("</title><style>");	
		out.println("input{font-size:13pt;color:blue;}");
		out.println("table{font-size:15pt;color:maroon;}");
		out.println("</style>");
		out.println("<center><h1><font color='9900cc'>"+title+"</font></h1>");
		out.println("<hr size=6 color='maroon'>");
		out.println("<body bgcolor='#e2e0d2'>");
		out.println("<FORM METHOD=post ACTION='"+file+"'><font face='timesnewroman' size=10>");
		out.println("<table cellspacing=10><tr><td>Product Code</td><td>: </td><td><select name=pcode>");
		ResultSet rs=st.executeQuery("select pcode from product");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</table>");
		out.println("<INPUT TYPE='submit' value='"+button+"'>&nbsp;&nbsp;&nbsp<INPUT TYPE='reset' value='Cancel'>");
		out.println("</FORM>");
		out.println(" <table><tr><td><br><br><a href='http://localhost:8080/dms/AdminSign.html' target='_top'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td><a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
		out.println("</center></body></html>");
		rs.close();st.close();
		con.close();
	}catch(Exception ex){out.println(ex);}
}
}