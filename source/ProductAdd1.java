import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ProductAdd1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	catch(Exception ex){System.out.println("Error");}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		PreparedStatement st=con.prepareStatement("insert into product values(?,?,?)");
		st.setString(1,req.getParameter("pcode"));
		st.setString(2,req.getParameter("pname"));
		st.setInt(3,new Integer(req.getParameter("amount")).intValue());
		st.execute();
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='#e2e0d2'>");
		out.println("<br><br>");
		out.println("<font size=5><center>ABC Company Welcomes U</center></font><hr color=green>");
		out.println("<center><font size=5><b>");
		out.println("<center><img src='http://localhost:8080/dms/images/stars.gif'>&nbsp;Congratulations!!!! &nbsp;<img src='http://localhost:8080/dms/images/stars.gif'><br><br>You have sucessfully added a component</center>");
		out.println("<br><br><a href = 'http://localhost:8080/dms/AdminSign.html' target=_top>");
		out.println("<img src='http://localhost:8080/dms/images/back_1.gif' width=40></a>");
		out.println("<a href = 'http://localhost:8080/dms/home.html' target='_top'><br><br>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif' width=40></a>");
		out.println("</body></html>");
		st.close();
		con.close();
		}
		catch(Exception ex){out.println(ex);}
	}
}