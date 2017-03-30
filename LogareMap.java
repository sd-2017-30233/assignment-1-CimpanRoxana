package businesslogic;
import dataS.*;
public class LogareMap {

	private LogareGateway logGateway=new LogareGateway();

public int insesareLog(String name, String password, int role)
{
	int idL=logGateway.inserare(name, password, role);
	return idL;
}

public void updateLog(int idLogin, String username)
{
	logGateway.update(idLogin, username);
}

public void deleteLog(int id)
{
	logGateway.stergere(id);
}

public int[] conectare(String userName, String password){
	
	int[] idRole = logGateway.getRole(userName, password);
	return idRole;
}
}
