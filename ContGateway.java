package dataS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContGateway {
	private static String URL = "jdbc:mysql://127.0.0.1:3306/apbancara";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private Connection conexiune = null;
	private PreparedStatement preparedStatement = null;
	
	
	////////////////////getAccounts/////////////////////
	public List<Object[]> getAccounts(int idClient){
		 List<Object[]> acc = new ArrayList<>();
		 try{
			 	conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
			 	String selectQuerry = "select * from `account` where idClient = ?"; 
			 	preparedStatement = conexiune.prepareStatement(selectQuerry);
			 	preparedStatement.setInt(1, idClient);
        
			 	ResultSet res = preparedStatement.executeQuery();
			 	while(res.next()){
			 			Object[] account = new Object[6];
			 			account[0] = res.getInt(1);
			 			account[1] = res.getString(2);
			 			account[2] = res.getInt(3);
			 			account[3] = res.getDate(4);
        		 	account[4] = res.getInt(5);
        		 	account[5] = res.getString(6);
        		 	acc.add(account);
			 		}
			 		res.close();
	    			}
	    	catch(SQLException se){ se.printStackTrace();}
		    catch(Exception e){e.printStackTrace();}
		    finally{}
		
		 return acc;
	}
////////////////////insereaza/////////////////////	
public void insereaza(int idClient, String type, int money, String currency){
try{
conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
String insertTableSQL = "insert into account"+ "(type, money, idClient, currency) values" + "(?,?,?,?)";
preparedStatement = conexiune.prepareStatement(insertTableSQL);
preparedStatement.setString(1, type);
preparedStatement.setInt(2, money);
preparedStatement.setInt(3, idClient);
preparedStatement.setString(4, currency);
preparedStatement.executeUpdate();
}
catch(SQLException se){se.printStackTrace();}
catch(Exception e){e.printStackTrace();}
finally{}
}

////////////////////update/////////////////////		
public void update(int idClient, int idAccount, String type){
try{
conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
String updateQuerry = "update `account` set account.type = ? where idClient = ? and idAccount = ?";
preparedStatement = conexiune.prepareStatement(updateQuerry);
preparedStatement.setString(1, type);
preparedStatement.setInt(2, idClient);
preparedStatement.setInt(3, idAccount);
preparedStatement .executeUpdate();
}
catch(SQLException se){se.printStackTrace();}
catch(Exception e){e.printStackTrace();}
finally{
  try{
     if(preparedStatement!=null) conexiune.close();
  }
  catch(SQLException se){}
  try{
     if(conexiune!=null) conexiune.close();}
  catch(SQLException se){se.printStackTrace();}
}
}
////////////////////stergere////////////////////////////
	public void stergere(int idClient, int idAccount){
		try{
			conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
			String deleteQuerry = "delete from `account` where idClient = ? and idAccount = ?";
			preparedStatement = conexiune.prepareStatement(deleteQuerry);
	   	    preparedStatement.setInt(1, idClient);
	   	    preparedStatement.setInt(2, idAccount);
	   	    preparedStatement.executeUpdate();
		}
	    catch(SQLException se){ se.printStackTrace();}
	    catch(Exception e){e.printStackTrace();}
	    finally
	    {
	      try{
	         if(preparedStatement!=null) conexiune.close();}
	      catch(SQLException se){}
	      try{
	         if(conexiune!=null) conexiune.close();}
	      catch(SQLException se){se.printStackTrace();}
	   }
	}
////////////////////modificareCont/////////////////////
public boolean[] modificareCont(int suma, String sursa, String destinatie, int idClient){
		
    int sursaC = 0;
    int destinatieC = 0;
    String sursaCurrency = null;
    String destinatieCurrency = null;
    boolean []  ok = {true, true};
    	
    try{
        conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
        String selectQuerry = "select * from `account` where idClient = ? and type = ?"; 
        preparedStatement = conexiune.prepareStatement(selectQuerry);
        preparedStatement.setInt(1, idClient);
        preparedStatement.setString(2, sursa);
        
        ResultSet rs = preparedStatement.executeQuery();
        	 
        while(rs.next()){
        		   sursaC = rs.getInt("money");
        		   sursaCurrency = rs.getString("currency");
        	    }
        rs.close();
        	  
        String select = "select * from `account` where idClient = ? and type = ?";
        preparedStatement = conexiune.prepareStatement(select);
        preparedStatement.setInt(1, idClient);
        preparedStatement.setString(2, destinatie);
        	     
        ResultSet result = preparedStatement.executeQuery();
        	 
        while(result.next()){
      		         destinatieC = result.getInt("money");
      		         destinatieCurrency = result.getString("currency");
      	                     }
        	     result.close();
        if (sursaC < suma )  
        { 
        	ok[1] = false;
        }
 if (ok[1] == true) 
   {
        	  if(sursaCurrency.equals(destinatieCurrency))   
      {
        		
        sursaC = sursaC - suma;
        	 
        String updateQuerry = "update `account` set account.money = ? where type = ? and idClient = ?";
        preparedStatement = conexiune.prepareStatement(updateQuerry);
        preparedStatement.setInt(1, sursaC);
        preparedStatement.setString(2, sursa);
        preparedStatement.setInt(3,idClient);
        preparedStatement .executeUpdate();
      	     
        destinatieC = destinatieC+ suma;
      	     
        String update = "update `account` set account.money = ? where type = ? and idClient = ?";
        preparedStatement = conexiune.prepareStatement(update);
        preparedStatement.setInt(1, destinatieC);
        preparedStatement.setString(2, destinatie);
        preparedStatement.setInt(3,idClient);
        preparedStatement .executeUpdate();
      }
           	
        else{   ok[0] = false;}
  }
    	}
    	    catch(SQLException se){se.printStackTrace();}
            catch(Exception e){e.printStackTrace();}
    	    finally{
    	     
    	    try{
    	         if(preparedStatement!=null)
    	           conexiune.close();
    	       }
    	    catch(SQLException se){}
    	      
    	    try{
    	         if(conexiune!=null)
    	            conexiune.close();
    	       }
    	    catch(SQLException se){
    	         se.printStackTrace();
    	      }
    	   }
    	return ok;
    }
////////////////////platesteFactura/////////////////////
	public int platesteFactura(int idClient, int pret){
	   int suma = 0;
	   int val = 1;
	   
	   try{
	   conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
 	   String selectQuerry = "select * from `account` where idClient = ? and type = ?"; 
 	   preparedStatement = conexiune.prepareStatement(selectQuerry);
 	   preparedStatement.setInt(1, idClient);
 	   preparedStatement.setString(2, "spendingAccount");
 
 	   ResultSet res = preparedStatement.executeQuery();
 	 
 	   while(res.next()){
 		   suma = res.getInt("money");
 	    }
 	   res.close();
 	  if (suma < pret)
 	  { 
 		  val = 0;
      }else
      
      {
		  suma =suma-pret;;
		  String updateQuerry = "update `account` set account.money = ? where type = ?";
 		  preparedStatement = conexiune.prepareStatement(updateQuerry);
 		  preparedStatement.setInt(1, suma);
 		  preparedStatement.setString(2, "spendingAccount");
 		  preparedStatement .executeUpdate();
	   }
	   }  
	   catch(SQLException se){se.printStackTrace();}
	   catch(Exception e){e.printStackTrace();}
	   finally{
	     
	      try{
	         if(preparedStatement!=null) conexiune.close();
	      }
	      catch(SQLException se)
	      { }
	      try{
	         if(conexiune!=null) conexiune.close();
	      }
	      catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return val;
	}

	

}
