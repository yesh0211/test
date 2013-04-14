package lcijava;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
class Mysql
//ArrayList exeQuery(String);
  //String returnString(String);
  //int exeUpdate(String);
  //java.sql.Date getMax(String);
{
  
  Connection conn;
  
  public Mysql()
  {
  }
  public String qU(String data,boolean escape)
  {
	  if(escape)
		  return("'"+escape(data)+"'");
	  else
		  return("'"+data+"'");
	  
  }
  
  
public String dateFormat(String data)
  {
	  DateFormat format1=new SimpleDateFormat("dd-MMM-yyyy"),
			  format2= new SimpleDateFormat("yyyy-MM-dd");
	  Date h=new Date();
	  try {
		  System.out.println(data);
		h=format1.parse(data);
		System.out.println(h);
		return format2.format(h);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }
  
  public String escape(String s){
	    int length=s.length();
	    char c[];
	    String ans=new String("");
	    c=s.toCharArray();
	    for(int i=0;i<length;i++){
	      if(c[i]=='\''){
	        ans+="\\";
	        ans+="'";
	      }
	      else if(c[i]=='"'){
	        ans+="\\";
	        ans+="\"";
	      }
	      else{
	        ans=ans+Character.toString(c[i]);
	      }
	      
	    }
	    return ans;
  }
  
  public boolean connectDB(String DBname)
  {
    return connectDB(DBname,true);
  }
  
  public boolean connectDB(String DBname,boolean autocommit)
  {
    try
    {
     Class.forName("com.mysql.jdbc.Driver");
     conn= DriverManager.getConnection("JDBC:MYSQL://localhost:3306/"+DBname,"root","root");
     if (conn != null)
     {
    	 conn.setAutoCommit(autocommit);
		 return true;
     }
     else
     {
    	System.err.println ("ERROR: Connection NULL");
    	return false;
     }    	 
   }
    catch (Exception e)
    {
    	System.err.println  (e.toString());
    	return false;
    }
  }
  
  
  public boolean closeDB(String DBname)
  {
	  try
	    {
	    	conn.close();
	    	return true; 
	   }
	    catch (Exception e)
	    {
	    	System.err.println  (e.toString());
	    	return false;
	    }
   }
  
  public boolean closeDB(String DBname,boolean commit)
  {
    try
    {
    	if(commit)
    		conn.commit();
    	else
    		conn.rollback();
    	conn.close();
    	return true; 
   }
    catch (Exception e)
    {
    	System.err.println  (e.toString());
    	return false;
    }
  }
  
  String addRow(ArrayList <String> columnlist,ArrayList <String> datatype,ArrayList <String> datavalue,String tablename,boolean returnid)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String insert="Insert into ";
        String columns="";
        if (columnlist != null)
    	{
        	columns="(";
    		int colsize=columnlist.size();
    		int i=0;
    		for(String AL : columnlist )
    		{
    			i++;
    			columns+=AL;
        		if(i==colsize)
        			columns+=")";
        		else
        			columns+=",";
    			
    		}
    	}
    		
    	String values=" values ";
    	String data="(";
    	if (datavalue != null && datatype != null)
    	{
    		int datatypesize=datatype.size();
    		int datavalsize=datavalue.size();
    		if (datatypesize == datavalsize)
    		{
    		for(int i=0;i<datavalsize;i++)
    		{
    			if(datatype.get(i).equals("String"))
    			{
    				data+="'"+datavalue.get(i)+"'";    				
    			}
    			if(datatype.get(i).equals("Number"))
    			{
    				data+=datavalue.get(i);    				
    			}
    			if(i==(datavalsize-1))
        			data+=")";
        		else
        			data+=",";
    			
    		}
    		String query=insert+tablename+columns+values+data;
        			
    		System.err.println("Query : " + query);
    		int result = conn.createStatement().executeUpdate(query);
    		if (returnid)
    		{
    			ResultSet rs=conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
    			rs.next();
    			return(""+rs.getString(1));
    		}
    		else
    		{
    		return (""+result);
    		}		
    	}
    		else
    		{
    			System.err.println("Error : datatype and datavalue size mismatch");
    		}
    	}
    	
    	else
    	{
    		System.err.println("Error : datatype or datavalue is null");
    	}
    	
    	return null;
    	
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }

  String addNewRow(ArrayList <String> columnlist,ArrayList <String> datavalue,String tablename,boolean returnid)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String insert="Insert into ";
        String columns="";
        if (columnlist != null)
    	{
        	columns="(";
    		int colsize=columnlist.size();
    		int i=0;
    		for(String AL : columnlist )
    		{
    			i++;
    			columns+=AL;
        		if(i==colsize)
        			columns+=")";
        		else
        			columns+=",";
    			
    		}
    	}
    		
    	String values=" values ";
    	String data="(";
    	if (datavalue != null)
    	{
    		int datavalsize=datavalue.size();
    		for(int i=0;i<datavalsize;i++)
    		{
    			data+=datavalue.get(i);    				
    			if(i==(datavalsize-1))
        			data+=")";
        		else
        			data+=",";
    			
    		}
    		String query=insert+tablename+columns+values+data;
        			
    		System.err.println("Query : " + query);
    		int result = conn.createStatement().executeUpdate(query);
    		if (returnid)
    		{
    			ResultSet rs=conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
    			rs.next();
    			return(""+rs.getString(1));
    		}
    		else
    		{
    		return (""+result);
    		}	
    		//return null;
    	}
    		
    	
    	
    	else
    	{
    		System.err.println("Error : datatype or datavalue is null");
    		return null;
    	}
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }
  
  String addSecNewRow(ArrayList <String> columnlist,ArrayList <String> datavalue,String tablename,boolean returnid)
  {
    try
    {
    	PreparedStatement stmt;
      if (tablename != null && !tablename.equals(""))
      {
        String insert="Insert into ";
        String columns="";
        if (columnlist != null)
    	{
        	columns="(";
    		int colsize=columnlist.size();
    		int i=0;
    		for(String AL : columnlist )
    		{
    			i++;
    			columns+=AL;
        		if(i==colsize)
        			columns+=")";
        		else
        			columns+=",";
    			
    		}
    	}
    		
    	String values=" values ";
    	String data="(";
    	if (datavalue != null)
    	{
    		int datavalsize=datavalue.size();
    		for(int i=0;i<datavalsize;i++)
    		{
    			data+="?";
    			if(i==(datavalsize-1))
        			data+=")";
        		else
        			data+=",";
    			
    		}
    		String query=insert+tablename+columns+values+data;
    		System.err.println("Query : " + query);
    		stmt=conn.prepareStatement(query);
    		for(int i=0;i<datavalsize;i++)
    			stmt.setString(i+1,datavalue.get(i));
    		
    		System.err.println("Query : " + query);
    		System.out.println(stmt.execute());
    		
    		/*if (returnid)
    		{
    			ResultSet rs=conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
    			rs.next();
    			return(""+rs.getString(1));
    		}*/
    		/*else
    		{
    		return (""+result);
    		}*/	
    		return null;
    	}
    		
    	
    	
    	else
    	{
    		System.err.println("Error : datatype or datavalue is null");
    		return null;
    	}
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }
  
  String selectRows(ArrayList <String> columnlist,String whereclause,String tablename)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String select="Select ";
        String columns="";
        if (columnlist != null)
    	{
        	//columns="(";
    		int colsize=columnlist.size();
    		int i=0;
    		for(String AL : columnlist )
    		{
    			i++;
    			columns+=AL;
        		if(i==colsize)
        			columns+=" ";
        		else
        			columns+=",";
    			
    		}
    	}
        else
        {
        	columns="*";
        }
    		
    	String from=" from ";
    	String where=" where ";
    	if (whereclause != null && !whereclause.equals(""))
    	{
    		String query=select+columns+from+tablename+where+whereclause;
        			
    		System.err.println("Query : " + query);
    		/*int result = conn.createStatement().executeUpdate(query);
    		if (returnid)
    		{
    			ResultSet rs=conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
    			rs.next();
    			return(""+rs.getString(1));
    		}
    		else
    		{
    		return (""+result);
    		}*/	
    		return null;
    	}
    	else
    	{
    		System.err.println("Error : Whereclause is null" + whereclause);
    		return null;
    	}
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }
  
  String selectValue(String column,String whereclause,String tablename)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String select="Select ";
        String columns="";
        if (column != null && !column.equals(""))
        {
        	columns+=column+" ";
        	String from=" from ";
        	String where=" where ";
        	String query;
        	if (whereclause != null && !whereclause.equals(""))
        	{
        		query=select+columns+from+tablename+where+whereclause;
        	}
        	else
        	{
        		query=select+columns+from+tablename;
        	}
        	System.err.println("Query : " + query);
    		ResultSet rs=conn.prepareStatement(query).executeQuery();
    		rs.next();
    		return(rs.getString(1));
        }
        else
        {
        	System.err.println("Error : column is null" + whereclause);
    		return null;
        }
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }
  
  boolean updateValue(String data,String column,String whereclause,String tablename)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String update="Update ";
        String columns="";
        if (column != null && !column.equals("") && data != null && !data.equals(""))
        {
        	columns+=column+"="+data+" ";
        	String set=" set ";
        	String where=" where ";
        	String query;
        	if (whereclause != null && !whereclause.equals(""))
        	{
        		query=update+tablename+set+columns+where+whereclause;
        	}
        	else
        	{
        		query=update+tablename+set+columns;
        	}
        	System.err.println("Query : " + query);
    		return(conn.prepareStatement(query).execute());
        }
        else
        {
        	System.err.println("Error : column or data is null" + column + " " + data);
    		return false;
        }
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return false;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return false;
      
    }
  }
  
  
  LinkedHashMap<String, String> colListPair(String columnname1,String columnname2,String tablename, String condition)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
        String select="Select ";
        if (columnname1 != null && !columnname1.equals(" ") 
        		&& columnname2 != null && !columnname2.equals(" "))
    	{
        	select+=columnname1+" , " +columnname2;
        	String from=" from ";
        	String where=" where ";
        	String order=" order by ";
        	String asc=" asc";
        	String query;
        	if(condition!=null)
        	{
        		where +=condition;
        		query=select+from+tablename+where+order+columnname2+asc;
        	}
        	else
        	{
        	query=select+from+tablename+order+columnname2+asc;
        	}
            			
        	System.err.println("Query : " + query);
        	LinkedHashMap<String, String> AL=new LinkedHashMap<String, String>();
        	ResultSet rs=conn.prepareStatement(query).executeQuery();
        	while(rs.next())
        	{
        		AL.put(rs.getString(1),rs.getString(2));
        	}
        	return AL;
    	}
        else
        {
        	System.out.println("Columname is null");
        	return null;
        }
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return null;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return null;
      
    }
  }
  
  boolean auth(String username,String password,String tablename)
  {
    try
    {
      if (tablename != null && !tablename.equals(""))
      {
    	  PreparedStatement stmt;
    	  String query="Select * from ";
    	  query+=tablename+" where username=? and password=?";
    	  			  
    	  stmt=conn.prepareStatement(query);
    	  stmt.setString(1,username);    	  
    	  stmt.setString(2,password);
    	  if (stmt.executeQuery().next())
    	  {
    		  return true;
    	  }
    	  else
    	  {
    		  return false;
    	  }
      }
      else
      {
      System.err.println("Error : Tablename is null");
      return false;
      }
    }
    catch(Exception e)
    {
    	System.err.println("EXCEPTION :" + e);
      return false;
      
    }
  }
  
  
  /*public static void main(String args[])
  {
	  System.out.println("hello boss");
	  Mysql s = new Mysql();
	  s.connectDB();
	  ArrayList <String> ALdt=new ArrayList();
	  ArrayList <String> ALdv=new ArrayList();;
	  ArrayList <String> ALcol=new ArrayList();;
	  
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  ALdt.add("Number");
	  
	  ALdv.add(null);
	  ALdv.add(s.qU("" +(Integer.parseInt("6")+Double.parseDouble(".5"))));
	  ALdv.add("" +(Integer.parseInt("3")+Double.parseDouble(".52")));
	  ALdv.add("" +(Integer.parseInt("7")+Double.parseDouble(".53")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".54")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".57")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".51")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".52")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".53")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".54")));
	  ALdv.add("" +(Integer.parseInt("0")+Double.parseDouble(".55")));
	  
	  
	  System.out.println(s.addSecNewRow(null,ALdv,"`new table`",true));
	  
  }*/
  
  
/* ArrayList exeQuery(String str)
  {
    try
    {
      if (!str.equals(null))
      {
        ArrayList AL = new ArrayList();
        ResultSet rs= (conn.createStatement().executeQuery(str));  
        while(rs.next())
        {       
         AL.add(rs.getString(1));
        }
        return AL;
      }
      else
      {
      System.err.println("Statement is null");
      return null;
      }
    }
    catch(Exception e)
    {
      login.error(true,e.toString());
      return null;
      
    }
  }
   
  int exeUpdate(String str)
  {
    try
    {
      return (conn.createStatement().executeUpdate(str));
    }
    catch(Exception e)
    {
      login.error(true,e.toString());
      return 0;
      
    }
  }*/
  
  /*java.sql.Date getMax(String str)
  {
    try
    {
      if (!str.equals(null))
      {
        //ArrayList AL = new ArrayList();
         ResultSet rs= (conn.createStatement().executeQuery(str));  
        //while(
        rs.next();
          //)
        //{       
         return(rs.getDate(1));
        //}
       
      }
      else
      {
      System.err.println("Statement is null");
      return null;
      }
    }
    catch(Exception e)
    {
      login.error(true,e.toString());
      return null;
    }
    
  }
  String returnString(String s)
  {
    return s;
  }

  */
}


