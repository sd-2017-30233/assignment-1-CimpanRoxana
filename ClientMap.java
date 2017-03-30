package businesslogic;
import java.util.ArrayList;

import dataS.*;
public class ClientMap {
	
	private ClientGateway clGateway=new ClientGateway();
	
public int getIdClient(int idLogin)
{
     int idClient=clGateway.getId(idLogin);
     return idClient;
}

public ArrayList<Client> getClients()
{
	ArrayList<Client> clienti=new ArrayList<>();
	clGateway.getClienti().forEach((client)->clienti.add(new Client(
			(int)client[0], 
			(String)client[1], 
			(String)client[2],
			(String)client[3], 
			(String)client[4],
			(int)client[5]))
		);
    return clienti;
    }

public void inserareClient(String name, String cardNumber, String CNP, String address,  int idLogin)
{
	clGateway.inserare(name, cardNumber, CNP, address, idLogin);
}

public void updateCl(int idClient, String name, String address)
{
	clGateway.update(idClient, name, address);
}

public int updateName(int idClient, String name)
{
	int idLogin=clGateway.updateName(idClient, name);
	return idLogin;
}

public void updateAdr(int idClient, String address)
{
	clGateway.updateAdresa(idClient, address);
}

public void stergereClient(int idClient)
{
	clGateway.stergere(idClient);
}


}


