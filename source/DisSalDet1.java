import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class DisSalDet1 extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement s=con.createStatement();
		ResultSet rs=s.executeQuery("select ddistrict from disdetails where dcode ='"+req.getParameter("dcode")+"'");
		rs.next();
		PreparedStatement st=con.prepareStatement("insert into dsales values(?,?,?,?,?,?)");
		st.setString(1,req.getParameter("dscode"));
		st.setString(2,req.getParameter("date"));
		st.setString(3,req.getParameter("dcode"));
		st.setString(4,req.getParameter("pcode"));
		st.setInt(5,new Integer(req.getParameter("quantity")).intValue());
		st.setString(6,rs.getString(1));
		st.execute();
		out.println("<html><head><TITLE> ABC Company </TITLE>");
		out.println("<body bgcolor='E9FFB9' text='maroon'><center>");
		out.println("<h2><img src='http://localhost:8080/dms/images/rose1.gif' width=75><font size=5> ABC Company Welcomes U <img src='http://localhost:8080/dms/images/rose1.gif' width=75></h2><hr color=maroon size=5>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='E9FFB9'>");
		out.println("<br><br>");
		out.println("<pre><h2><center>Your Sales has been sucessfully inserted</h2>");
		out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif'></a></td></tr></table></center>");
		out.println("</pre></centre></body></html>");
		st.close();
		con.close();
	}catch(Exception ex)
	{
	out.println("Excep: " +ex);
	}
}
}