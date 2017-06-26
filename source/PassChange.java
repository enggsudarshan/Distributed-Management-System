import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class PassChange extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement s=con.createStatement();
		Statement st1=con.createStatement();
		HttpSession hs=req.getSession(true);
		String user=(String)hs.getValue("user");
		String code=(String)hs.getValue("code");
		if((user.toLowerCase()).equals(req.getParameter("passwd")))
			res.sendRedirect("http://localhost:8080/dms/passchangeinvalid.html");
		else
		{	 
			if(code.substring(0,1).equals("r"))
					s.executeUpdate("update rep set password='"+req.getParameter("passwd")+"' where scode='"+code+"' and userid='"+user+"'");
			else
					s.executeUpdate("update disdetails set passwd='"+req.getParameter("passwd")+"' where dcode='"+code+"' and userid='"+user+"'");
			s.close();
			st1.executeUpdate("update login set passwd='"+req.getParameter("passwd")+"'where code='"+code+"' and userid='"+user+"'");
			out.println("<HTML><HEAD><TITLE>Password change</TITLE>");
			out.println("<style>");
			out.println("{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}");
			out.println("table{font-size:15pt;color:maroon;}</style>");
			out.println("</HEAD>");
			out.println("<body>");
			out.println("<br><br>");
			out.println("<font size=10><center>ABC Company Welcomes U </centre><font><hr color=green size=3>");
			out.println("<center><font size=5>");
			out.println("Congratulations !!! <br><br>Your Password is changed sucessfully<br><br>");
			out.println("<table cellspacing=10><tr><td>New Password </td><td>:</td><td>"+req.getParameter("passwd")+"</td></tr>");
			out.println("<tr><td colspan=3> <h2>Please remember it forever </h2></td></tr></table>");
			out.println("<a href = 'http://localhost:8080/dms/home.html'>");
			out.println(" <img src='http://localhost:8080/dms/images/home1.gif' width=40></a></b>");
			out.println("</body></html>");
			con.close();
		}
	}
		catch(Exception ex){out.println(ex);}
	}
}