import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class DisSalDet extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	HttpSession hs=req.getSession(true);
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from dsales");
		while(rs.next())
		{
			a=rs.getString(1);
		}
		if(a==null)
			a="DS1";
		else
		{
			int l=a.length();
			String l1=a.substring(2,l);
			Integer i=new Integer(l1);
			int n=i.intValue()+1;
			a=a.substring(0,2)+Integer.toString(n);
		}
		out.println("<html><title>Distributor's Sales Entry </title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:15pt;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='E9FFB9' text='maroon'>");
		out.println("<center><h1><font color='blue'>Distributor's Sales Entry Form </font></h1>");
		out.println("<hr size=3 color='maroon'>");
		out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/DisSalDet1'>");
		out.println("<table><tr><td>Invoice Number</td><td>:</td><td>	<INPUT TYPE='text' NAME='dscode' value='"+a+"'></td></tr>");
		Calendar c=Calendar.getInstance();
		String d=Integer.toString(c.get(Calendar.DATE))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.YEAR));
		out.println("<tr><td>Date </td><td>:</td><td><INPUT TYPE='text' NAME='date' value='"+d+"'></td></tr>");
		out.println("<tr><td>Distributor's Code </td><td>:</td><td><INPUT TYPE='text' NAME='dcode' value='"+(String)hs.getValue("code")+"'></td></tr>");
		out.println("<tr><td>Product Code </td><td>:</td><td><select name='pcode'>");
		rs=st.executeQuery("select pcode from product");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</select></td></tr>");
		out.println("<tr><td>Quantity </td><td>:</td><td><INPUT TYPE='text' NAME='quantity'></td></tr>");
		out.println("</table>");
		out.println("<INPUT TYPE='submit' value='Submit'>&nbsp;&nbsp;&nbsp;<INPUT TYPE='reset' value='Clear'>");
		out.println("</center></FORM>");
		out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href='http://localhost:8080/dms/home.html'>");
		out.println("<image src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table></center>");
		out.println("</body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}