import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class MSalRep extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String dcode=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=null;
		out.println("<html><title>Representative Sales Report Page</title> <style>");	
		out.println("input{font-size:12pt;color:blue;}table{font-size:15pt;color:maroon;font-style:bold}");
		out.println("<!-- .plain { BORDER-RIGHT: 2px solid; BORDER-TOP: 2px solid; FONT-SIZE: 12px; FONT-WEIGHT: Bold; BORDER-LEFT: 2px solid; BORDER-BOTTOM: 2px solid; FONT-FAMILY: verdana; Background-color:#CCCCCC;}-->");
		out.println("</style>");
		out.println("<script language='javascript'>");
		out.println("function y2k(number){ return (number < 1000) ? number + 1900 : number;}");
		out.println("var today = new Date();var day   = today.getDate();var month = today.getMonth();var year  = y2k(today.getYear());");
		out.println("var Display;");
		out.println("function padout(number) {return (number < 10) ? '0' + number : number;}");
		out.println("function restart() {Display.value = '' + padout(day) + '-' + padout(month - 0 + 1) + '-' + year;mywindow.close();}");
		out.println("function newWindow(disp) {Display = disp;");
		out.println("mywindow=open('calendar.html','myname','resizable=no,width=350,height=270');");
		out.println("mywindow.location.href = 'http://localhost:8080/dms/calendar.html';");
		out.println("if (mywindow.opener == null) mywindow.opener = self;}");
		out.println("function checkStartEndDates(startd,startm,starty,endd,endm,endy,startno) {if(parseInt(endy) < parseInt(starty)) {alert('Please enter an end year greater than or same as the start year');return false;}");
		out.println("else if(parseInt(starty) == parseInt(endy)) {if(parseInt(endm) < parseInt(startm)) {alert('Please enter an end month greater than or same as the start month');return false;}");
		out.println("else if(parseInt(startm) == parseInt(endm)) {	if(parseInt(endd) < parseInt(startd)) {alert('Please enter an end date greater than or same as the start date');return false;}");
		out.println("else return true;}else return true;}else return true;}");
		out.println("</script>");
		out.println("<body bgcolor='#ffddbb' text='maroon'>");
		out.println("<center><h2><font color='blue'>Sales Rep's Sales Details </font></h2>");
		out.println("<img src='http://localhost:8080/dms/images/redline.gif'>");
		out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/MSalRep1'>");
		if(req.getParameter("sales").equals("1"))
		{
			out.println("<center><font size=5>Sales Rep's Code  :  </font><select name='code'>");
			 rs=st.executeQuery("select scode from rep");
			while(rs.next())
			{
				String t=rs.getString(1);
				out.println("<option value='"+t+"'>"+t);
			}
			out.println("</select>");rs.close();
			out.println("<input type='hidden' value=1 name='report'>");
		}
		else if(req.getParameter("sales").equals("2"))
		{
			out.println("<center><font size=3>Distributor Code  :  </font><select name='code'>");
			 rs=st.executeQuery("select dcode from disdetails");
			while(rs.next())
			{
				String t=rs.getString(1);
				out.println("<option value='"+t+"'>"+t);
			}
			out.println("</select>");rs.close();
			out.println("<input type='hidden' value=2 name='report'>");
		}
		else
		{		out.println("<input type='hidden' value=3 name='report'>"); }
		out.println("<table cellspacing=10><tr><td>From </td><td>:</td><td>	<INPUT TYPE='text' NAME='fdate' size=9 onFocus='javascript:blur()'><input type='button' class=plain value='Choose Date' onClick='newWindow(this.form.fdate)'></td></tr>");
		out.println("<tr><td>To </td><td>:</td><td><INPUT TYPE='text' NAME='tdate' size=9 onFocus='javascript:blur()'><input type='button' class=plain value='Choose Date' onClick='newWindow(this.form.tdate)'></td></tr>");
		out.println("<tr><td>Product Code </td><td>:</td><td><select name='pcode'>");
		rs=st.executeQuery("select pcode from product");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</select></td></tr>");
		out.println("</table><br><INPUT TYPE='submit' value='  Report  ' size=10>");
		out.println("</FORM><br>");
		out.println("<table><tr><td><a href='http://localhost:8080/servlet/ManagerSign' target=_top><img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td>&nbsp;&nbsp;&nbsp;</td><td><a href='http://localhost:8080/dms/home.html' target=_top>");
		out.println("<image src='http://localhost:8080//dms//images//home1.gif'></a></td></tr></table></center>");
		out.println("</table></center></body></html>");
		st.close();
		con.close();
	}
	catch(Exception e){out.println(e);}
}
}