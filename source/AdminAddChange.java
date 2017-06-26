import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AdminAddChange extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select code,add1,add2,city,state,pincode from addch");
		out.println("<html><title>Address Change Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:12pt;color:maroon;font-weight:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#e2e0d2' text='maroon'><center>");
		out.println("<h1><u>Address Modification Page</u></h1>");
		out.println("<form name='Address' method=post action='http://localhost:8080/servlet/AdminAddChange1'>");
		out.println("<br><table border width='100%'><tr align=center><th>Sl.No.</th><th>Code</th><th>Address</th><th>City</th><th>State</th><th>Pincode</th><th>Modify<br>(Yes/No)</th></tr>");
		int i=0;
		while(rs.next())
		{
			String t=rs.getString(1);
		//	out.println("<tr><td>"+ ++i +"</td><td>"+t+"</td><td>"+rs.getString(2)+"<br>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td><input type='checkbox' name='"+t+"'>Yes<input type='checkbox' name='"+t+"' value='n'>No</td></tr>");
			out.println("<tr align=center><td>"+ ++i +"</td><td>"+t+"</td><td>"+rs.getString(2)+"<br>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getInt(6)+"</td><td><input type='checkbox' name='"+t+"'></td></tr>");
		}
		out.println("</table>");
		out.println("<br><input type=submit value='Change'>");
		out.println("</form><br>");
		out.println("<table><tr><td><a href='http://localhost:8080/dms/AdminSign.html'><img src='http://localhost:8080/dms/images/back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' ></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}