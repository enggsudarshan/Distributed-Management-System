import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class SalRepAdd extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	String a=null;
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from rep");
		while(rs.next())
		{
			a=rs.getString(1);
		}
		if(a==null)
			a="re1";
		else
		{
			int l=a.length();
			String l1=a.substring(2,l);
			Integer i=new Integer(l1);
			int n=i.intValue()+1;
			a=a.substring(0,2)+Integer.toString(n);
		}
		out.println("<html><title>Rep's Registration Page</title><style>");	
		out.println("input{font-size:13pt;color:blue;}");
		out.println("table{font-size:15pt;color:maroon;}");
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
		out.println("<center><h1><font color='blue'>New Sales Representative's Register Page </font></h1>");
		out.println("<hr size=6 color='maroon'>");
		out.println("<body bgcolor='#e2e0d2'>");
		out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/SalRepAdd1'><font face='timesnewroman' size=10>");
		out.println("<table border=0><tr><td>Sales Rep Code : </td><td> Name :</td><td>Date of Birth :</td></tr>");
		out.println("<tr><td><INPUT TYPE='text' NAME='scode' value="+a+"></td><td><INPUT TYPE='text' NAME='name'></td><td><INPUT TYPE='text' NAME='dob' size=10'>");
		out.println("<input type='button' value='Choose Date' onClick='newWindow(this.form.dob)'>");
		out.println("</td></tr>");
		out.println("<tr><td>Qualification : </td><td>Address :</td><td></td></tr>");
		out.println("<tr><td><INPUT TYPE='text' NAME='qual'></td><td><INPUT TYPE='text' NAME='add'></td><td><INPUT TYPE='text' NAME='add1'></td></tr>");
		out.println("<tr><td>City : </td><td>State :</td><td>Pincode:</td></tr>");
		out.println("<tr><td><INPUT TYPE='text' NAME='city'></td><td><INPUT TYPE='text' NAME='state'></td><td><INPUT TYPE='text' NAME='pin'></td></tr>");
		out.println("<tr><td>Phone : </td><td>E-Mail ID :</td><td>Dcode:</td></tr>");
		out.println("<tr><td><INPUT TYPE='text' NAME='phone'></td><td><INPUT TYPE='text' NAME='email'></td><td><select name='dcode'>");
		rs=st.executeQuery("select dcode from disdetails");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</select></td></tr>");
		out.println("</table>");
		out.println("<INPUT TYPE='submit' value='Login' name='dis'>&nbsp;&nbsp;&nbsp<INPUT TYPE='reset' value='Clear'>");
		out.println("</center></FORM>");
		out.println("</font></body></html>");
		st.close();
		con.close();
	}catch(Exception ex){out.println(ex);}
}
}