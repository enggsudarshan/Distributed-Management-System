import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DisSalRepInp extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dis");
		Statement st=con.createStatement();
		out.println("<html><title>Distributor Product Report </title><style>");
		out.println("<!-- .plain { BORDER : 2px solid blue; FONT-WEIGHT: Bold; font-color:blue; FONT-FAMILY: verdana; Background-color:#CCCCCC;}-->");
		out.println("input{font-size:13pt;color:blue;}");
		out.println("body{font-size:15pt;color:maroon;}");
		out.println("table{font-size:15pt;color:maroon;}");
		out.println("</style>");
		out.println("<center><h1><font color='blue'>Distributor Product Report </font></h1>");
		out.println("<hr size=6 color='maroon'>");
		out.println("<body bgcolor='E9FFB9'>");
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
		out.println("<FORM METHOD=post ACTION='http://localhost:8080/servlet/DisSalRepOut'>");
		out.println("<table cellspacing=15>");
		out.println("<tr><td>From : </td><td><INPUT TYPE='text' NAME='fdate' size=9 onFocus='javascript:blur()'></td><td><input type='button' class=plain value='Choose Date' onClick='newWindow(this.form.fdate)'></td></tr>");
		out.println("<tr><td>To : </td><td><INPUT TYPE='text' NAME='tdate' size=9 onFocus='javascript:blur()'></td><td><input type='button' class=plain value='Choose Date' onClick='newWindow(this.form.tdate)'></td></tr>");
		out.println("</centre><tr><td colspan=2>Product Code : </td> <td> <select name='pcode' width=10>");
		ResultSet rs=st.executeQuery("select pcode from product");
		while(rs.next())
		{
			String t=rs.getString(1);
			out.println("<option value='"+t+"'>"+t);
		}
		out.println("</select></td></tr>");
		out.println("<tr><td colspan=3 align=center><input type='submit' value='Click'></td></tr></table>");
		out.println("</form>");
		out.println("<center><table cellspacing=25><tr><td><a href='http://localhost:8080/dms/distributor.html'>");
		out.println("<img src='http://localhost:8080/dms/images//back_1.gif'></a>");
		out.println("</td><td><a href = 'http://localhost:8080/dms/home.html'>");
		out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a></td></tr></table>");
		out.println("</center></body></html>");
		}catch(Exception e){out.println(e);}
	}
}