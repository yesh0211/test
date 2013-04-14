package lcijava;

public class Login {
	public Login()
	{
		System.out.println("in lcijava login");
		
		
	}

	public boolean authenticate(String username, String password)
	{
		Mysql mysql = new Mysql();
		if (mysql.connectDB("LCI"))			
		{
			if(mysql.auth(username,password,"Login"))
			{
				mysql.closeDB("LCI");
				return true;
			}
			else
			{
				mysql.closeDB("LCI");
				return false;
			}
			//&& username.equals("somu") && password.equals("5")
			
		}
		else
		{
			System.err.println("Error: connecting Database");
			return false;
		}
	}
}
