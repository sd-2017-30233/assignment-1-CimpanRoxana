package businesslogic;

public class Client {
	private int idClient;
	private String nameClient;
	private String cardNr;
	private String CNP;
	private String address;
	private int idLogin;

public Client(int idClient,
			String nameClient,
			String cardNr,
			String CNP,
			String address,
			int idLogin)
{
	super();
	this.idClient=idClient;
	this.nameClient=nameClient;
	this.cardNr=cardNr;
	this.CNP=CNP;
	this.address=address;
	this.idLogin=idLogin;
}

public int getIdClient()
{
	return idClient;
}

public void setIdClient(int idClient)
{
	this.idClient=idClient;
}

public int getIdLogin()
{
	return idLogin;
}

public void setIdLogin(int idLogin)
{
	this.idLogin=idLogin;
}

public String getNameClient()
{
	return nameClient;
}

public void setNameClient(String nameClient)
{
	this.nameClient=nameClient;
}

public String getCardNr()
{
	return cardNr;
}

public void setCardNr(String cardNr)
{
	this.cardNr=cardNr;
}

public String getCNP()
{
	return CNP;
}

public void setCNP(String CNP)
{
	this.CNP=CNP;
}

public String getAddress()
{
	return address;
}

public void setAddress(String address)
{
	this.address=address;
}
/*
public String getPhoneNr()
{
	return phoneNr;
}

public void setPhoneNr(String phoneNr)
{
	this.phoneNr=phoneNr;
}

public String getMail()
{
	return mail;
}

public void setMail(String mail)
{
	this.mail=mail;
}
*/
}





