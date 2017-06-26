import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalDetail extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;String dcode=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from rsales");
		while(rs.next())
		{
			a=rs.getString(1);
		}
		if(a==null)
			a="RS1";
		else
		{
			int l=a.length();
			String l1=a.substring(2,l);
			Integer i=new Integer(l1);
			int n=i.intValue()+1;
			a=a.substring(0,2)+Integer.toString(n);
		}
		HttpSession hs =req.getSession(true);
		String rcode=(String)hs.getValue("code");
		rs.close();
		rs=st.executeQuery("select dcode from rep where scode='"+rcode+"'");
		if(rs.next())
			dcode=rs.getString(1);
		out.println("<html><title>Representative Sales Entry Form</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:15pt;color:maroon;font-style:bold}");
		out.println("</style>");
		out.println("<body bgcolor='#eeeee3' text='maroon'>");
		out.println("<center><h1><font color='blue'>Sales Representative Sale's Entry Form </font></h1>");
		out.println("<img src='http://localhost:8080/dms/images/flashing_line.gif'>");
		out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/SalDetail1' target='_top'>");
		out.println("<table><tr><td>Invoice Number</td><td>:</td><td>	<INPUT TYPE='text' NAME='rscode' value="+a+"></td></tr>");
		Calendar c=Calendar.getInstance();
		String d=Integer.toString(c.get(Calendar.DATE))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.YEAR));
		out.println("<tr><td>Date </td><td>:</td><td><INPUT TYPE='text' NAME='rsdate' value='"+d+"'></td></tr>");
		out.println("<tr><td>Sales Rep Code </td><td>:</td><td><INPUT TYPE='text' NAME='rcode' value='"+(String)hs.getValue("code")+"'></td></tr>");
		out.println("<tr><td>Distributor's Code </td><td>:</td><td><INPUT TYPE='text' NAME='dcode' value='"+dcode+"'></td></tr>");
		out.println("<tr><td>Product Code </td><td>:</td><td><select name='pcode'>");
		rs.close();
		rs=st.executeQuery("select pcode from product");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</select></td></tr>");
		out.println("<tr><td>Quantity </td><td>:</td><td><INPUT TYPE='text' NAME='quan'></td></tr>");
		out.println("<tr><td>Reason </td><td>:</td><td><INPUT TYPE='text' NAME='reason'></td></tr></table>");
		out.println("<INPUT TYPE='submit' value='Submit'>&nbsp;&nbsp;&nbsp;<INPUT TYPE='reset' value='Clear'>");
		out.println("</FORM>");
		out.println("<a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif' width='40'></a></td></tr></table></center>");
		out.println("</center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}