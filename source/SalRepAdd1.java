import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalRepAdd1 extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String a=null;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	}
		catch(Exception ex){System.out.println("Error");}
		try
		{
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			PreparedStatement st=con.prepareStatement("insert into login values(?,?,?,?)");
			PreparedStatement st1=con.prepareStatement("insert into rep values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1,req.getParameter("scode"));
			st.setString(2,req.getParameter("scode"));
			st.setString(3,req.getParameter("scode"));
			st.setString(4,"S");
			st1.setString(1,req.getParameter("scode"));
			st1.setString(2,req.getParameter("scode"));
			st1.setString(3,req.getParameter("scode"));
			st1.setString(4,req.getParameter("name"));
			st1.setString(5,req.getParameter("dob"));
			st1.setString(6,req.getParameter("qual"));
			st1.setString(7,req.getParameter("add"));
			st1.setString(8,req.getParameter("add1"));
			st1.setString(9,req.getParameter("city"));
			st1.setString(10,req.getParameter("state"));
			st1.setInt(11,new Integer(req.getParameter("pin")).intValue());
			st1.setInt(12,new Integer(req.getParameter("phone")).intValue());
			st1.setString(13,req.getParameter("email"));
			st1.setString(14,req.getParameter("dcode"));
			st.execute();
			st1.execute();
			out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
			out.println("<style>");
			out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
			out.println("</style>");
			out.println("</HEAD>");
			out.println("<body bgcolor='#e2e0d2'>");
			out.println("<br><br>");
			out.println("<font size=10><centre>ABC Company Welcomes U</centre><font><hr color=green>");
			out.println("<pre><center><font size=5><b>");
			out.println(" Sales Rep ID Sucessfully created !!!!!!!!!");
			out.println("<a href = 'http://localhost:8080/dms/home.html'>");
			out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a>");
			out.println("</body></html>");
		}
		catch(Exception ex){out.println(ex);}
	}
}