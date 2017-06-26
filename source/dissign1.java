import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class dissign extends HttpServlet
{
	String t1,t2,t3,t4,t5,t6,47,t8,t9,t10;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		ResultSet rs=null;
		ResultSet rs1=null;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String d=req.getParameter("t1");String d3=null;
		String d1=req.getParameter("t10");
		String d2 =req.getParameter("t11");
		int i=0;
		try { 		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	}
		catch(Exception ex){System.out.println(ex);}
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='E9FFB9'>");
		out.println("<br><br>");
		out.println("<font size=10><centre>ABC Company Welcomes U</centre><font><hr color=green>");
		out.println("<pre><center><font size=5><b>");
		try
		{
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement s=con.createStatement();
		rs=s.executeQuery("select * from disdetails where dcode='"+d.toUpperCase()+"';");
		while(rs.next())
		{
			d3=rs.getString(1);
			if(d.equals(d3))
				i=1;
		}
		rs1=s.executeQuery("select district,area from disdetails where district ='"+d1+"' and '"+d2+"';");
		rs1.next();
		if(i==1)
		{
					out.println("Distributor Code is Existing");
					out.println("Please try another one");
					out.println("<a href = 'http://localhost:8080/dissal.html'>");
					out.println(" <img src='g:/servlet/project/arrow.gif'></a>");
		}
		else if(d2.equals(rs1.getString(2)))
		{
			out.println("Area is Existing");
			out.println("Please try another one");
			out.println("<a href = 'http://localhost:8080/dissal.html'>");
			out.println(" <img src='g:/servlet/project/arrow.gif'></a>");
		}
		else
		{
		PreparedStatement st=con.prepareStatement("insert into disdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement st1=con.prepareStatement("insert into login values(?,?,?)");
		st.setString(1,req.getParameter("t1").toUpperCase());
		st.setString(2,req.getParameter("t2"));
		st.setString(3,req.getParameter("t3"));
		st.setString(4,req.getParameter("t4"));
		st.setString(5,req.getParameter("t5"));
		st.setString(6,req.getParameter("t6"));
		st.setInt(7,new Integer(req.getParameter("t7")).intValue());
		st.setString(8,req.getParameter("t8"));
		st.setString(9,req.getParameter("t9"));
		st.setString(10,req.getParameter("t10"));
		st.setString(11,req.getParameter("t11"));
		st.setInt(12,new Integer(req.getParameter("t12")).intValue());
		st.setString(13,req.getParameter("t13"));
		st.setInt(14,new Integer(req.getParameter("t14")).intValue());
		st1.setString(1,req.getParameter("t1").toUpperCase());
		st1.setString(2,req.getParameter("t2"));
		st1.setString(3,new String("D"));
		st.execute();
		st1.execute();
		out.println("Your UserName is:"+req.getParameter("t1"));
		out.println("Your Password is :"+req.getParameter("t2"));
		out.println("<h2>Don,t forget it in future !</h2>");
		out.println("<a href = 'http://localhost:8080/home.html'>");
		out.println(" <img src='g:/servlet//project//home1.gif'></a>");
		out.println("</body></html>");
		}
		}
		catch(Exception ex){out.println(ex);}
		
	}
}