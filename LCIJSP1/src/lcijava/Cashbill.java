package lcijava;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Cashbill {
	
	public LinkedHashMap cashbuyerList()
	{
		Inventory inv =new Inventory(); 
		return(inv.custList("C"));
	}
	
	public String billNumber()
	{
		String a;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{//
			if((a=mysql.selectValue("Max(bill_No)",null,"cashbill_table"))!=null)
			{
				mysql.closeDB("LCI");
				System.out.println(a);
				return ((Integer.parseInt(a)+1)+"");
			}
			else
			{
				mysql.closeDB("LCI");
				return new String();
			}
		}
		else
		{
			System.err.println("Error: connecting Database");
			return new String();
		}		
	}
	
	
	
	public String sell(Hashtable<String,String> Hashdata) 
	{
		String bill_no;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI",false))			
		{//
			
				
			ArrayList <String> ALcol=new ArrayList <String>();
			ArrayList <String> ALdata=new ArrayList <String>();
			
			//sales table
			ALcol.add("bill_date");
			ALcol.add("cust_id");
			ALcol.add("amount");
			ALcol.add("vat");
			ALcol.add("bill_amount");
			
			ALdata.add(mysql.qU(mysql.dateFormat(Hashdata.get("billdate")),false));
			ALdata.add(Hashdata.get("custid")); 
			ALdata.add(Hashdata.get("total"));
			ALdata.add(Hashdata.get("vatamt"));
			ALdata.add(Hashdata.get("billamt"));
			
			
			if((bill_no=mysql.addNewRow(ALcol,ALdata,"cashbill_table",true))!=null)
			{
				//customer balance update cust_table
				mysql.updateValue((""+((int)Float.parseFloat(mysql.selectValue("cust_credit_bal", "cust_id="+Hashdata.get("custid"),"cust_table"))+(int)Float.parseFloat(Hashdata.get("billamt")))),
		        		"cust_credit_bal", "cust_id="+Hashdata.get("custid"),"cust_table");
				String count[]={Hashdata.get("countid1"),Hashdata.get("countid2"),Hashdata.get("countid3"),Hashdata.get("countid4"),Hashdata.get("countid5")};
				String qty[]={Hashdata.get("qty1"),Hashdata.get("qty2"),Hashdata.get("qty3"),Hashdata.get("qty4"),Hashdata.get("qty5")};
				String rate[]={Hashdata.get("rate1"),Hashdata.get("rate2"),Hashdata.get("rate3"),Hashdata.get("rate4"),Hashdata.get("rate5")};
				//String amt[]={Hashdata.get("amt1"),Hashdata.get("amt2"),Hashdata.get("amt3"),Hashdata.get("amt4"),Hashdata.get("amt5")};
				
				
				for(int i=0;i<5 && (!count[i].equals("0"));i++)
				{
					//cashbill detail table
					ALcol.clear();
					ALcol.add("bill_no");
					ALcol.add("count_id");
					ALcol.add("quantity");
					ALcol.add("rate");
					
					ALdata.clear();
					ALdata.add(bill_no);
					ALdata.add(count[i]); 
					ALdata.add(qty[i]);
					ALdata.add(rate[i]);
					
					
					
					
					mysql.addNewRow(ALcol,ALdata,"cashbill_details_table",false);
					
					
					
					
					/*s=(String)(sql.exeQuery("select quantity from stock_table where count_id= "+s)).get(0);
			        s=Float.toString(new Float(s) -new Float(quantity[i].getText()));
			        sql.exeUpdate("update lci.stock_table set quantity="+s+" where count_id="+b);*/
			        
					//stock table update
			        mysql.updateValue((""+(Float.parseFloat(mysql.selectValue("quantity", "count_id="+count[i],"stock_table"))-Float.parseFloat(qty[i]))),
			        		"quantity","count_id="+count[i],"stock_table");
				
				
					
				}
				

				
				
				
				System.out.println(bill_no);
				mysql.closeDB("LCI",true);
				return bill_no;
			}
			else
			{
				mysql.closeDB("LCI",false);
				return new String();
			}
		}
		else
		{
			System.err.println("Error: connecting Database");
			return new String();
		}
		
	}

}
