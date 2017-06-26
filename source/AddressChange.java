import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class AddressChange extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			HttpSession hs=req.getSession(true);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			PreparedStatement ps=con.prepareStatement("insert into addch values(?,?,?,?,?,?,?)");
			ps.setString(1,(String)hs.getValue("code"));
			ps.setString(2,req.getParameter("add"));
			ps.setString(3,req.getParameter("add1"));
			ps.setString(4,req.getParameter("city"));
			ps.setString(5,req.getParameter("state"));
			ps.setInt(6,new Integer(req.getParameter("pin")).intValue());
			ps.setString(7,"y");
			ps.execute();
			out.println("<HTML><HEAD><TITLE>Address change</TITLE>");
			out.println("<style>");
			out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}");
			out.println("table{font-size:15pt;color:maroon;}</style>");
			out.println("</HEAD>");
			out.println("<body bgcolor='E9FFB9'>");
			out.println("<br><br>");
			out.println("<font size=10><center>ABC Company Welcomes U </centre><font><hr color=green>");
			out.println("<pre><center><font size=5><b>");
			out.println("Modification of address will be done by Admin as soon as possible <br>");
			out.println("Confirmation will be sent u");
			out.println("<a href = 'http://localhost:8080/dms/home.html'>");
			out.println(" <img src='http://localhost:8080/dms/images/home1.gif' width=40></a>");
			out.println("</pre>");
			out.println("</body></html>");
			con.close();
		}
		catch(Exception ex){out.println(ex);}
	}
}


