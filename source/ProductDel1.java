import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductDel1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String t=req.getParameter("pcode");
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){System.out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from product where pcode='"+t.toUpperCase()+"'");
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("table{font-style:Bold;font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='#e2e0d2''>");
		out.println("<br><br>");
		out.println("<font size=5 color='maroon'><center>ABC Company Welcomes U</center><font><hr color=green>");
		out.println("<center><font size=5><b>");
		if(!rs.next())
		{
				out.println("<h3><center><font size=5><b>The given Product ID is not found");
				out.println("<br><br>Please,Enter the valid Product Id !</font>");
				out.println("<form action='http://localhost:8080/servlet/productdel' method='get'>");
				out.println(" <br><br><input type=image src='http://localhost:8080/images/arrow.gif'>");
		}
		else
		{
				PreparedStatement st1=con.prepareStatement("delete * from product where pcode='"+t.toUpperCase()+"'");
				st1.execute();
				out.println("<h3><center><font size=5><b>You have sucessfully deleted the item");
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