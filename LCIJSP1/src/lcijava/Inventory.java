package lcijava;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Inventory {

	public Inventory() {
		// TODO Auto-generated constructor stub
	}
	
	//add customer
	public boolean addTrader(Hashtable<String,String> Hashdata)
	{
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{
			String returndata;
			
			ArrayList <String> ALcol=new ArrayList <String>();
			ArrayList <String> ALdata=new ArrayList <String>();
			
			//customer table
			ALcol.add("cust_name");
			ALdata.add(mysql.qU(Hashdata.get("tradername"),true));
			
			ALcol.add("cust_type");
			ALdata.add(mysql.qU(Hashdata.get("custtype"),true));
			
			ALcol.add("cust_tngst");
			ALdata.add(Hashdata.get("tin"));
			
			ALcol.add("cust_address1");
			ALdata.add(mysql.qU(Hashdata.get("address"),true));
			
			String phone;
			if((phone=Hashdata.get("tel1"))!="")
			{
				ALcol.add("cust_phone1");
				ALdata.add(phone);
			}
					
			if((phone=Hashdata.get("tel2"))!="")
			{
				ALcol.add("cust_phone2");
				ALdata.add(phone);
			}
			
			if((phone=Hashdata.get("tel3"))!="")
			{
				ALcol.add("cust_phone3");
				ALdata.add(phone);
			}
			
			
			ALcol.add("cust_mailid");
			ALdata.add(mysql.qU(Hashdata.get("emailid"),true));
			
			
			/*
			 * {"tradername","custtype","address",
    		"tel1","tel2","tel3","tin","emailid"
    		};
			 * 
			 * 
			 * sql.exeUpdate("insert into lci.cust_table (cust_name,cust_type,cust_tngst,cust_address1,cust_phone1,cust_mailid) values('"
                        + login.escape(cusname.getText()) + "','" +login.escape(custype.getSelectedItem().toString()) +"',"+ login.escape(custngst.getText()) +",'"
                        +login.escape(cusadd.getText())+"',"+login.escape(cusphno.getText()) +",'"+login.escape(cusmailid.getText())+"');");
			 */
			
			if(((returndata=mysql.addNewRow(ALcol,ALdata,"cust_table",false))!=null) &&
					(!returndata.equals("0")))
			{
				mysql.closeDB("LCI");
				return true;
			}
		
			else
			{
				mysql.closeDB("LCI");
				return false;
			}
			
			
		}
		else
		{
			System.err.println("Error: connecting Database");
			return false;
		}
	}
	
	//customer list
	public LinkedHashMap custList(String cust_type)
	{
		LinkedHashMap a;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{
			if((a=mysql.colListPair("Cust_Id","Cust_Name","cust_table","Cust_Type = '"+cust_type+"'"))!=null)
			{
				mysql.closeDB("LCI");
				System.out.println(a.size());
				return a;
				
			}
			else
			{
				mysql.closeDB("LCI");
				return new LinkedHashMap();
			}
		}
		else
		{
			System.err.println("Error: connecting Database");
			return new LinkedHashMap();
		}
	}
	
	//add count
	public boolean addCount(Hashtable<String,String> Hashdata)
	{
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{
			String returndata;
			
			ArrayList <String> ALcol=new ArrayList <String>();
			ArrayList <String> ALdata=new ArrayList <String>();
			
			//stock table
			ALcol.add("Count");
			ALcol.add("Quantity");
			
			ALdata.add(mysql.qU(Hashdata.get("countname"),true));
			ALdata.add("0");
						
			
			if (Integer.parseInt(mysql.selectValue("Count(*)", "Count="+mysql.qU(Hashdata.get("countname"),false), "stock_table"))==0) {
				if (((returndata = mysql.addNewRow(ALcol, ALdata,
						"stock_table", false)) != null)
						&& (!returndata.equals("0"))) {
					mysql.closeDB("LCI");
					return true;
				} else {
					mysql.closeDB("LCI");
					return false;
				}
			} else {
				mysql.closeDB("LCI");
				return false;
			}
			
			
		}
		else
		{
			System.err.println("Error: connecting Database");
			return false;
		}
	}
	
	//countlist
	public LinkedHashMap countList()
	{
		LinkedHashMap a;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{//
			if((a=mysql.colListPair("Count_Id","Count","stock_table",null))!=null)
			{
				mysql.closeDB("LCI");
				return a;
			}
			else
			{
				mysql.closeDB("LCI");
				return new LinkedHashMap();
			}
		}
		else
		{
			System.err.println("Error: connecting Database");
			return new LinkedHashMap();
		}		
	}
	
	public String vat()
	{
		String a;
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{//
			if((a=mysql.selectValue("info_value2","info_detail = 'vat' ","info_table"))!=null)
			{
				mysql.closeDB("LCI");
				System.out.println(a);
				return a;
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

}

