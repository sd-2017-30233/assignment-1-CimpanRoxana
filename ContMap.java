package businesslogic;
import java.util.ArrayList;

import businesslogic.Cont;
import dataS.*;

public class ContMap {
     private ContGateway accGateway=new ContGateway();
     public ArrayList<Cont> sendId(int idCont){
 		ArrayList<Cont> accounts = new ArrayList<>();
 		
 		accGateway.getAccounts(idCont).forEach((account) -> accounts.add(new Cont(
 					(int)account[0], 
 					(String)account[1], 
 					(int)account[2],
 					(java.sql.Date)account[3], 
 					(int)account[4],
 					(String)account[5]))
 				);
 		return accounts;
 	}
   
     public boolean[] tranzactie(int suma,String sursa, String destinatie, int idClient){
 		boolean[] val = accGateway.modificareCont(suma, sursa, destinatie, idClient);
 		return val;
 	}
 	
 	public int platesteFactura(int idClient, int price){
 		int val = accGateway.platesteFactura(idClient,price);
 		return val;
 	}
 	
 	public void inserareCont(int idClient, String type, int money, String currency){
 		accGateway.insereaza(idClient, type, money, currency);
 	}
 	
 	public void updateCont(int idClient, int idAccount,  String type){
 		accGateway.update(idClient, idAccount, type);
 	}
 	
 	public void stergeCont(int idClient, int idAccount){
 		accGateway.stergere(idClient, idAccount);
 	}
     
}
