package businesslogic;

public class Logare {
	private int idLogin;
	private int role;
	private String username;
	private String password;
	
	public int getIdLogin()
	{
		return idLogin;
	}
	
	public void setIdLogin(int idLogin)
	{
		this.idLogin=idLogin;
	}
	
	public int getRole()
	{
		return role;
	}
	
	public void setRole(int role)
	{
		this.role=role;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
}
