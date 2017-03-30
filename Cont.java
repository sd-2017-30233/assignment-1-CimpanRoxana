package businesslogic;
import java.sql.Date;
public class Cont {
	private int idAccount;
	private String type;
	private int money;
	private Date creationDate;
	private int idClient;
	private String currency;
	
public Cont (int idAccount, 
		     String type,
		     int money,
		     Date creationDate,
		     int idClient,
		     String currency
		     )
{
	super();
	this.idAccount=idAccount;
	this.type=type;
	this.money=money;
	this.creationDate=creationDate;
	this.currency=currency;
	this.idClient=idClient;
}

public int getIdAccount()
{
	return idAccount;
}

public void setIdAccount(int idAccount)
{
	this.idAccount=idAccount;
}

public int getIdClinet()
{
	return idClient;
}

public String getType()
{
	return type;
}

public void setType(String type)
{
	this.type=type;
}
	
public int getMoney()
{
	return money;
}

public void setMoney(int money)
{
	this.money=money;
}

public Date getCreationDate()
{
	return creationDate;
}

public void setCreationDate(Date creationDate)
{
	this.creationDate=creationDate;
}
	
public String getCurrency()
{
	return currency;
}

public void setCurrency(String currency)
{
	this.currency=currency;
}
}
	

