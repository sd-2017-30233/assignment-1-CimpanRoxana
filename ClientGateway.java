package dataS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientGateway {
	private static String URL="jdbc:mysql://127.0.0.1:3306/apbancara";
	private static String USERNAME="root";
	private static String PASSWORD="root";
	private Connection conexiune=null;
    private PreparedStatement preparedStatement=null;
    
public List<Object[]> getClienti(){
	List<Object[]> clienti=new ArrayList<>();
	try
	{
		conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
		String selectQuerry = "select * from `client`"; 
		preparedStatement=conexiune.prepareStatement(selectQuerry);
		ResultSet res=preparedStatement.executeQuery();
		while (res.next())
		{
			Object[] cl=new Object[6];
			cl[0]=res.getInt(1);
			cl[1]=res.getString(2);
			cl[2]=res.getString(3);
			cl[3]=res.getString(4);
			cl[4]=res.getString(5);
			cl[5]=res.getInt(6);
			clienti.add(cl);
		}
		res.close();
		}
		
	catch(SQLException se){se.printStackTrace();}
	catch(Exception e){e.printStackTrace();}
	finally{}
	  
    return clienti;
}


public int getId(int idLogin){
	int id=0;
	try
	{
		conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		String selectQuerry="select * from `client` where idLogin=?";
		preparedStatement=conexiune.prepareStatement(selectQuerry);
		preparedStatement.setInt(1, idLogin);
		ResultSet res=preparedStatement.executeQuery();
		while(res.next())
		    {
			id=res.getInt("idClient");
			}
		res.close();
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
	      catch(SQLException se){se.printStackTrace();}
	        
	 }
	return id;
}

public void inserare(String name, String cardNumber,String CNP, String address,  int idLogin){
	try
	{
	conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);
	String inserareTabel="insert into `client`"
			+"(name,cardNumber,CNP,address, idLogin) values"
			+"(?,?,?,?,?)";
	preparedStatement=conexiune.prepareStatement(inserareTabel);
	preparedStatement.setString(1,name);
	preparedStatement.setString(2, cardNumber);
	preparedStatement.setString(3,CNP);
	preparedStatement.setString(4,address);
	preparedStatement.setInt(5, idLogin);
	preparedStatement.executeUpdate();
	}
	catch(SQLException se){se.printStackTrace();}
	catch(Exception e){e.printStackTrace();}
	finally{}
}

public void update(int idClient, String name, String address){
	try
	{
	conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);	
	String up="update `client` set client.name=?, client.address=? where idClient=?";
	preparedStatement=conexiune.prepareStatement(up);
	preparedStatement.setString(1,name);
	preparedStatement.setString(2, address);
	preparedStatement.setInt(3,idClient);
	preparedStatement.executeUpdate();
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
	      catch(SQLException se){se.printStackTrace();}
	     
	 }
	
}

public void updateAdresa(int idClient, String address){
	try
	{
	conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);	
	String up="update `client` set client.address = ? where idClient = ?";
	preparedStatement=conexiune.prepareStatement(up);
	preparedStatement.setString(1,address);
	preparedStatement.setInt(2,idClient);
	preparedStatement.executeUpdate();
	}
	catch(SQLException se){ se.printStackTrace();}
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
	      catch(SQLException se){se.printStackTrace();}
	      
	        }
}

public int updateName(int idClient, String name){
	int id=0;
	try
	{
	 conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);	
	 String up="update `client` set client.name=? where idClient=?";
	 preparedStatement=conexiune.prepareStatement(up);
	 preparedStatement.setString(1, name);
	 preparedStatement.setInt(1, idClient);
	 preparedStatement.executeUpdate();
	
	 String sel="select * from `client` where idClient=?";
	 preparedStatement=conexiune.prepareStatement(sel);
	 preparedStatement.setInt(1, idClient);
	
	 ResultSet res=preparedStatement.executeQuery();
	
	 while(res.next())
	 {
		 id=res.getInt("idLogin");
	 }
	 res.close();
	}
	catch(SQLException se){ se.printStackTrace();}
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
	      catch(SQLException se){se.printStackTrace();}
	      
	      }
return id;
}


public int stergere(int idClient){
	int id=0;
	try
	{
    conexiune=DriverManager.getConnection(URL,USERNAME,PASSWORD);	
	String sel="select * from `client` where idClient=?";
	preparedStatement=conexiune.prepareStatement(sel);
	preparedStatement.setInt(1, idClient);
	
	ResultSet res=preparedStatement.executeQuery();
	
	while (res.next())
	{
		id=res.getInt("idLogin");
	}
	res.close();
	
	String sterge="delete from `client` where idClient=?";
	preparedStatement=conexiune.prepareStatement(sterge);
	preparedStatement.setInt(1,idClient);
	preparedStatement.executeUpdate();
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
	      catch(SQLException se){se.printStackTrace();}
	        }
		return id;
	}


}