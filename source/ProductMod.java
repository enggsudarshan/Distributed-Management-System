import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductMod extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String t=req.getParameter("t1");
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){System.out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from product where pcode='"+req.getParameter("pcode").toUpperCase()+"'");
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("table{font-style:Bold;font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='#e2e0d2''>");
		out.println("<br><br>");
		out.println("<font size=5 color='maroon'><center>Product Modification Details</center><font><hr color=green>");
		out.println("<center><font size=5><b>");
		if(!rs.next())
		{
				out.println("<h3><center><font size=5><b>The given Product ID is not found");
				out.println("<br><br>Please,Enter the valid Product Id !</font>");
		}
		else
		{
			out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/ProductMod1'><font face='timesnewroman' size=10>");
			out.println("<table cellspacing=10><tr><td>Product Code</td><td>: </td><td><INPUT TYPE='text' NAME='pcode' value='"+req.getParameter("pcode")+"'></td></tr>");
			out.println("<tr><td>Product Name </td><td>: </td><td><INPUT TYPE='text' NAME='pname'></td></tr>");
			out.println("<tr><td>Amount</td><td>: </td><td><INPUT TYPE='text' NAME='amount'></td></tr>");
			out.println("</table>");
			out.println("<INPUT TYPE='submit' value='Modify'>&nbsp;&nbsp;&nbsp<INPUT TYPE='reset' value='Cancel'></form>");
		}
		out.println(" <table><tr><td><br><br><a href='http://localhost:8080/dms/AdminSign.html' target='_top'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td><a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
		out.println("</body></html>");
		st.close();
		con.close();
		}
		catch(Exception ex){out.println(ex);}
	}
}