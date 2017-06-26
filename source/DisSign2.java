import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class DisSign2 extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		HttpSession hs=req.getSession(true);
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String a=null;int amt1=0;String t=null;
		int j=0;String d[]=new String[15];
		out.println("<HTML><HEAD><TITLE>Sign Up Page</TITLE>");
		out.println("<style>");
		out.println("pre{min-width:40%;max-width:40%;min-height:30%;max-height:50%;border:10px solid maroon}input{font-size:15pt;color:blue}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body bgcolor='E9FFB9'>");
		out.println("<br><br>");
		out.println("<font size=10><centEr>ABC Company Welcomes U</center><font><hr color=green>");
		out.println("<pre><center><font size=5><b>");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:dis");
			Connection con1=DriverManager.getConnection("jdbc:odbc:bank");
			Statement st=con.createStatement();
			ResultSet 	rs=st.executeQuery("select * from disdetails where ddistrict ='"+req.getParameter("ddistrict")+"' and dcity ='"+req.getParameter("dcity")+"' and area ='"+req.getParameter("darea")+"'");
			if(rs.next())
			{
				out.println("Already Existing Area");
				out.println("Please try another one");
				out.println("<a href='http://localhost:8080/dms/disareacredit.html'>");
				out.println("<image src='http://localhost:8080/dms/images/Arrow.gif'></a><br>");
			}
			else
			{
			rs.close();st.close();
			st=con1.createStatement();
			rs=st.executeQuery("select * from credit1 where creditno='"+req.getParameter("ccno")+"' and bank='"+req.getParameter("bname")+"'");
			int flag=0;
			if(!rs.next())
			{
				out.println("<h3><center><font size=5><b>The given Credit Number is not found");
				out.println("<br><br>Please,Enter the valid Credit Card Number !</font>");
				out.println("</b>");
				out.println("<a href='http://localhost:8080/dms/disareacredit.html'>");
				out.println("<image src='http://localhost:8080/dms/images/Arrow.gif'></a><br>");
				flag=2;
			}
			else
			{
				amt1=rs.getInt(3);
				if(req.getParameter("ccno").equals(rs.getString(1)))
					if(amt1<10000)
						flag=1;
			}
			if(flag==1)
			{
				out.println("<h3><center><font size=5><b>Sorry Your Credit Amount is less than the Required amount");
				out.println("<h3>Please increase your credit amount and try again</h3>");
				out.println("<a href = 'http://localhost:8080/dms/home.html'>");
				out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a>");
			}
			else if (flag==0)
			{
				 st=con.createStatement();
				 rs=st.executeQuery("select * from disdetails");
				while(rs.next())
				{
					a=rs.getString(1);
				}
				if(a==null)
					a="d1";
				else
				{
					int l=a.length();
					String l1=a.substring(1,l);
					Integer i=new Integer(l1);
					int n=i.intValue()+1;
					a=a.substring(0,1)+Integer.toString(n);
				}
				PreparedStatement pst=con.prepareStatement("insert into disdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				PreparedStatement pst1=con.prepareStatement("insert into login values(?,?,?,?)");
				pst.setString(1,a);
				pst.setString(2,(String)hs.getValue("login"));
				pst.setString(3,(String)hs.getValue("pass"));;
				pst.setString(4,(String)hs.getValue("name"));
				pst.setString(5,(String)hs.getValue("add1"));
				pst.setString(6,(String)hs.getValue("add2"));
				pst.setString(7,(String)hs.getValue("city"));
				pst.setString(8,(String)hs.getValue("state"));
				//pst.setInt(9,new Integer((String)hs.getValue("pin")).intValue());
				pst.setInt(9,Integer.parseInt((String)hs.getValue("pin")));
				pst.setString(10,(String)hs.getValue("email"));
                                //pst.setInt(11,new Integer((String)hs.getValue("rphone")).intValue());
                                pst.setInt(11,Integer.parseInt((String)hs.getValue("rphone")));
                               pst.setInt(12,Integer.parseInt((String)hs.getValue("ophone")));
                            //pst.setInt(12,new Integer((String)hs.getValue("ophone")).intValue());
				pst.setString(13,req.getParameter("dstate"));
				pst.setString(14,req.getParameter("ddistrict"));
				pst.setString(15,req.getParameter("dcity"));
				pst.setString(16,req.getParameter("darea"));
				/*pst.setString(13,(String)hs.getValue("dstate"));
				pst.setString(14,(String)hs.getValue("ddistrict"));
				pst.setString(15,(String)hs.getValue("dcity"));
				pst.setString(16,(String)hs.getValue("darea"));
			pst.setString(2,d[0]);
			pst.setString(3,d[1]);
			pst.setString(4,d[2]);
			pst.setString(5,d[3]);
			pst.setString(6,d[4]);
			pst.setString(7,d[5]);
			pst.setString(8,d[6]);
			pst.setInt(9,new Integer(d[7]).intValue());
			pst.setString(10,d[8]);
			pst.setInt(11,new Integer(d[9]).intValue());
			pst.setInt(12,new Integer(d[10]).intValue());
			pst.setString(13,d[11]);
			pst.setString(14,d[12]);
			pst.setString(15,d[13]);
			pst.setString(16,d[14]);*/
				pst.setString(17,req.getParameter("ccno"));
				pst.setInt(18,new Integer(10000).intValue());		
				pst1.setString(1,((String)hs.getValue("login")).toUpperCase());
				pst1.setString(2,(String)hs.getValue("pass"));
				pst1.setString(3,a);
				pst1.setString(4,new String("d"));
				pst.execute();
				pst1.execute();
				rs.close();st.close();con.close();
				st=con1.createStatement();
				int am=amt1-10000;
				st.executeUpdate("update credit1 set amount= "+am+" where creditno='"+req.getParameter("ccno")+"'");
				out.println("<style>table{font-size:15pt;color:maroon;}</style>");
				out.println("<center><img src='http://localhost:8080/dms/images/stars.gif'>&nbsp;Congratulations!!!! &nbsp;<img src='http://localhost:8080/dms/images/stars.gif'>");
				out.println("<br><br><table border=0 cellspacing=10><tr><td>Distributor Code</td><td>:</td><td>"+a+"</td></tr>");
				out.println("<tr><td>Login ID </td><td>:</td><td>"+(String)hs.getValue("login")+"</td></tr>");
				out.println("<tr><td>Password </td><td>:</td><td>"+(String)hs.getValue("pass")+"</td></tr>");
				out.println("<tr><td colspan=3> <h2>Please remember it forever </h2></td></tr></table>");
				out.println("<a href = 'http://localhost:8080/dms/home.html'>");
				out.println(" <img src='http://localhost:8080/dms/images/home1.gif'></a>");
				out.println("</body></html>");
				st.close();con.close();con1.close();}
		}
		}
		catch(Exception ex){out.println(ex);}
	}}
