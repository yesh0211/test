package lcijava;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Voucher {
	
	public LinkedHashMap supplierList()
	{
		Inventory inv =new Inventory(); 
		return(inv.custList("S"));
	}

	
	public boolean bought(Hashtable <String,String> Hashdata)
	{
		String bill_id;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI",false))			
		{//
			
				
			ArrayList <String> ALcol=new ArrayList <String>();
			ArrayList <String> ALdata=new ArrayList <String>();
			
			//purchase table
			ALcol.add("bill_no");
			ALcol.add("bill_date");
			ALcol.add("cust_id");
			ALcol.add("total_amt");
			ALcol.add("vat");
			ALcol.add("bill_amt");
			
			ALdata.add(mysql.qU(Hashdata.get("billno"), true));
			ALdata.add(mysql.qU(mysql.dateFormat(Hashdata.get("billdate")),false));
			ALdata.add(Hashdata.get("custid")); 
			ALdata.add(Hashdata.get("total"));
			ALdata.add(Hashdata.get("vatamt"));
			ALdata.add(Hashdata.get("billamt"));
			/*
			sql.exeUpdate("insert into lci.purchase_table (bill_no,bill_date,cust_id,total_amt,vat,bill_amt) values('"
                    +login.escape(billno.getText())+"','"+b+"','" +login.escape(acount.get(0).toString())+"',"+totalamt.getText() +","+vat.getText()+","+billamt.getText()+ ");");*/
			
			
			if((bill_id=mysql.addNewRow(ALcol,ALdata,"purchase_table",true))!=null)
			{
				//customer balance update cust_table
				
				  /*s=(String)(sql.exeQuery("select cust_credit_bal from cust_table where cust_type='S' &&cust_id= "+acount.get(0))).get(0);
				    s=Float.toString(new Float(s) +new Float(billamt.getText()));
				    sql.exeUpdate("update lci.cust_table set cust_credit_bal="+s+" where cust_id="+acount.get(0) );*/
				    
				mysql.updateValue((""+(Float.parseFloat(mysql.selectValue("cust_credit_bal", "cust_id="+Hashdata.get("custid"),"cust_table"))-Float.parseFloat(Hashdata.get("billamt")))),
		        		"cust_credit_bal", "cust_id="+Hashdata.get("custid"),"cust_table");
				
				
				String count[]={Hashdata.get("countid1"),Hashdata.get("countid2"),Hashdata.get("countid3"),Hashdata.get("countid4"),Hashdata.get("countid5")};
				String qty[]={Hashdata.get("qty1"),Hashdata.get("qty2"),Hashdata.get("qty3"),Hashdata.get("qty4"),Hashdata.get("qty5")};
				String rate[]={Hashdata.get("rate1"),Hashdata.get("rate2"),Hashdata.get("rate3"),Hashdata.get("rate4"),Hashdata.get("rate5")};
				//String amt[]={Hashdata.get("amt1"),Hashdata.get("amt2"),Hashdata.get("amt3"),Hashdata.get("amt4"),Hashdata.get("amt5")};
				
				
				for(int i=0;i<5 && (!count[i].equals("0"));i++)
				{
					//sales detail table
					ALcol.clear();
					ALcol.add("bill_id");
					ALcol.add("count_id");
					ALcol.add("quantity");
					ALcol.add("rate");
					
					ALdata.clear();
					ALdata.add(bill_id);
					ALdata.add(count[i]); 
					ALdata.add(qty[i]);
					ALdata.add(rate[i]);
					
					
					/*sql.exeUpdate("insert into lci.purchase_details_table (bill_id,count_id,quantity,rate) values("
                        +bill_id+","
                        +s+"," +quantity[i].getText()+","+rate[i].getText()+")");*/
					
					mysql.addNewRow(ALcol,ALdata,"purchase_details_table",false);
					
					
					
					
					/*s=(String)((sql.exeQuery("select count_id from stock_table where count='"+login.escape(s)+"'")).get(0));
        
        //count table
        b=s;
        s=(String)(sql.exeQuery("select quantity from stock_table where count_id= "+s)).get(0);
        s=Float.toString(new Float(s) +new Float(quantity[i].getText()));
        sql.exeUpdate("update lci.stock_table set quantity="+s+" where count_id="+b);*/
			        
					//stock table update
			        mysql.updateValue((""+(Float.parseFloat(mysql.selectValue("quantity", "count_id="+count[i],"stock_table"))+Float.parseFloat(qty[i]))),
			        		"quantity","count_id="+count[i],"stock_table");
				
				
					
				}
				System.out.println(bill_id);
				mysql.closeDB("LCI",true);
				return true;
			}
			else
			{
				mysql.closeDB("LCI",false);
				return false;
			}
		}
		else
		{
			System.err.println("Error: connecting Database");
			return false;
		}
		
		
		
		
		}
}
