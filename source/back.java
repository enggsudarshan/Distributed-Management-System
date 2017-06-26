import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class back extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	int n=Integer.parseInt(req.getParameter("hid"));
	out.println("<script language='JavaScript'>");
	for(int i=1;i<=n;i++)
	{
		out.println("history.back()");
	}
	out.println("</script>");
}
}