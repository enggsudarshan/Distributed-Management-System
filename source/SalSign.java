import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalSign extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement s=con.createStatement();
		Statement st1=con.createStatement();
		ResultSet rs=s.executeQuery("select * from login where userid='"+req.getParameter("userid").toUpperCase()+"'");
		if(rs.next())
		{
			res.sendRedirect("http://localhost:8080/dms/salsigninvalid.html");
		}
		else 
		{
			HttpSession hs=req.getSession(true);
			String code=(String)hs.getValue("code");
			String a = req.getParameter("userid").toUpperCase();
			String p=req.getParameter("passwd");
			s.close();
			s=con.createStatement();
			s.executeUpdate("update rep set userid='"+a+"' , password='"+p+"' where scode='"+code+"'");
			s.close();
			st1.executeUpdate("update login set userid='"+req.getParameter("userid").toUpperCase()+"' , passwd='"+req.getParameter("passwd")+"'where code='"+code+"'");
			out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
			out.println("<style>");
			out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}");
			out.println("table{font-size:15pt;color:maroon;}</style>");
			out.println("</HEAD>");
			out.println("<body bgcolor='E9FFB9'>");
			out.println("<br><br>");
			out.println("<font size=10><center>ABC Company Welcomes U </centre><font><hr color=green>");
			out.println("<pre><center><font size=5><b>");
			out.println("Congratulations !!! <br><br>Login Id and Password are changed sucessfully");
			out.println("<br><br><table border=0 cellspacing=10><tr><td>Sales Representative Code </td><td>:</td><td>"+code+"</td></tr>");
			out.println("<tr><td>Login ID </td><td>:</td><td>"+req.getParameter("userid")+"</td></tr>");
			out.println("<tr><td>Password </td><td>:</td><td>"+req.getParameter("passwd")+"</td></tr>");
			out.println("<tr><td colspan=3> <h2>Please remember it forever </h2></td></tr></table>");
			out.println("<a href = 'http://localhost:8080/dms/home.html'>");
			out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a>");
			out.println("</body></html>");
			con.close();
		}
	}
		catch(Exception ex){out.println(ex);}
	}
}