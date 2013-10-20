package lcijsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Invoice
 */
@WebServlet("/Invoice")
public class Invoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String datanamesInvoice[]={"billno","billdate","custid",
    		"countid1","qty1","rate1","amt1",
    		"countid2","qty2","rate2","amt2",
    		"countid3","qty3","rate3","amt3",
    		"countid4","qty4","rate4","amt4",
    		"countid5","qty5","rate5","amt5",
    		"total","vatamt","billamt"
    		};
	
	String datanamesAddTrader[]={"tradername","custtype","address",
    		"tel1","tel2","tel3","tin","emailid"
    		};
	
	String datanamesAddCount[]={"countname"};
	
    public Invoice() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Hashtable <String,String> Hashdata = new Hashtable<String,String>();
		String b,action;
		try
		{
		action=request.getParameter("action");
		lcijava.Invoice invoice= new lcijava.Invoice();
		lcijava.Inventory inventory= new lcijava.Inventory();
		lcijava.Voucher voucher= new lcijava.Voucher();
		lcijava.Cashbill cashbill= new lcijava.Cashbill();
		switch(action)
		{
		case "sell":
		{
			for(String a: datanamesInvoice)
				Hashdata.put(a,((b=request.getParameter(a))!=null?b:""));
				;
				response.setContentType("plain/text");
				PrintWriter pw = response.getWriter();
				String billno;
				if((billno=invoice.sell(Hashdata))!=null)
				pw.write(billno);
				break;
		}
		case "cashsell":
		{
			for(String a: datanamesInvoice)
				Hashdata.put(a,((b=request.getParameter(a))!=null?b:""));
				;
				response.setContentType("plain/text");
				PrintWriter pw = response.getWriter();
				pw.write(cashbill.sell(Hashdata));
				break;
		}
		case "bought":
		{
			for(String a: datanamesInvoice)
				Hashdata.put(a,((b=request.getParameter(a))!=null?b:""));
				;
				response.setContentType("plain/text");
				PrintWriter pw = response.getWriter();
				if(voucher.bought(Hashdata))
					pw.write("Bought Successfully");
				else
					pw.write("Not Bought");
					
				break;
		}
		case "addtrader":
		{
			for(String a: datanamesAddTrader)
				Hashdata.put(a,((b=request.getParameter(a))!=null?b:""));
				;
				response.setContentType("plain/text");
				PrintWriter pw = response.getWriter();
				if(inventory.addTrader(Hashdata))
					pw.write("Trader Added Successfully");
				else
					pw.write("Trader Not Added");
				break;
								
				
		}
		case "addcount":
		{
			for(String a: datanamesAddCount)
				Hashdata.put(a,((b=request.getParameter(a))!=null?b:""));
				;
				response.setContentType("plain/text");
				PrintWriter pw = response.getWriter();
				if(inventory.addCount(Hashdata))
					pw.write("Count Added Successfully");
				else
					pw.write("Count Not Added");
				break;
		}
		default:
		{
			PrintWriter pw = response.getWriter();
			pw.write("Error 404 : Page not found");
		}
		}
		}
		catch(Exception e)
		{
			PrintWriter pw = response.getWriter();
			pw.write("Error 404 : Page not found");
		}
		
		
	}

}
