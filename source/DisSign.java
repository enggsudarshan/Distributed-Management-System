import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class DisSign extends HttpServlet
{
	String d[]=new String[15];
	int j=0;
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		hs=req.getSession(true);
		ResultSet rs=null;
		ResultSet rs1=null;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try {
                         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                         }
		catch(Exception ex){System.out.println(ex);}
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}table{font-size:14pt;color:maroon}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='E9FFB9'>");
		out.println("<br><br>");
		out.println("<font size=10><center>ABC Company Welcomes U</center><font><hr color=green>");
		out.println("<pre><center><font size=5><b>");
		//out.println("<form name='credit' method='post' action='http://localhost:8080/servlet/DisSign2'>");
		hs.putValue("login",req.getParameter("login"));
		hs.putValue("pass",req.getParameter("pass"));
		hs.putValue("name",req.getParameter("name"));
		hs.putValue("add1",req.getParameter("add1"));
		hs.putValue("add2",req.getParameter("add2"));
		hs.putValue("city",req.getParameter("city"));
		hs.putValue("state",req.getParameter("state"));
		hs.putValue("pin",req.getParameter("pin"));
		hs.putValue("email",req.getParameter("email"));
		hs.putValue("rphone",req.getParameter("rphone"));
		hs.putValue("ophone",req.getParameter("ophone"));
		try
		{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
                PreparedStatement p=con.prepareStatement("select * from login where userid=?");
                p.setString(0,req.getParameter("login"));
                ResultSet rr=p.executeQuery();
                /*Statement s=con.createStatement();
                rs=s.executeQuery("select * from login where userid='"+req.getParameter("login").toUpperCase()+"'");*/
                if(rr.next())
		{
			res.sendRedirect("http://localhost:8080/dms/dislogininvalid.html");
		}
		else 
		{
			res.sendRedirect("http://localhost:8080/dms/tos.html");
		}
			/*rs1=s.executeQuery("select * from disdetails where ddistrict ='"+req.getParameter("ddistrict")+"' and dcity ='"+req.getParameter("dcity")+"' and area ='"+req.getParameter("darea")+"'");
			if(rs1.next())
			{
				out.println("Already Existing Area");
				out.println("Please try another one");
				out.println("<a href='http://localhost:8080/dissalnew.html'>");
				out.println("<image src='http://localhost:8080/images/Arrow.gif'></a><br>");
			}
		else
		{
			out.println("<font size=5><table cellspacing=3><tr><td colspan=3 align=center><b><u>Credit Card Details</u></b></td></tr><tr><td>Credit Card No.</td><td> :</td><td><Input type=text name='ccno'></td></tr>");
			out.println("<tr><td>Bank Name</td><td> :</td><td><Input type=text name='bname'></td></tr>");
			out.println(" <tr><td>Amount</td><td> :</td><td><Input type=text name='amount'></td></tr></table>");
			out.println("</font><input type='Submit' value='Login'> <input type='reset' value='Cancel'>");
			out.println("</form>");
		}*/
                p.close();
		con.close();
		}
		catch(Exception ex){out.println(ex);}
	}	
}
