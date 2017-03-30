package dataS;

import java.sql.*;

public class LogareGateway {
	private static String URL = "jdbc:mysql://127.0.0.1:3306/apbancara";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private Connection conexiune = null;
    private PreparedStatement preparedStatement = null;

//getRole    
public int[] getRole(String userName, String password){
	int[] idRole=new int [3];
	idRole[2]=1;
	try
	{
	 conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
     String sel="select * from `login` where username=? and password=?";
     preparedStatement=conexiune.prepareStatement(sel);
     preparedStatement.setString(1, userName);
     preparedStatement.setString(2, password);
    
     ResultSet res=preparedStatement.executeQuery();
     if(!res.isBeforeFirst())
     {
     	 idRole[2]=0;
     }
     while (res.next())
     {
    	 idRole[0]=res.getInt("idLogin");
    	 idRole[1]=res.getInt("role");
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
	return idRole;
	}
  
public int inserare(String name, String password, int role){
	int idL=0;
	try
	{
     conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
	 String ins="insert into login"+"(username,password,role) values"+"(?,?,?)";
	 preparedStatement=conexiune.prepareStatement(ins);
	 preparedStatement.setString(1, name);
	 preparedStatement.setString(2, password);
	 preparedStatement.setInt(3,role);
	 preparedStatement.executeUpdate();
	
	 String sel="select * from `login` where username=? and password=?";
	 preparedStatement = conexiune.prepareStatement(sel);
	 preparedStatement.setString(1, name);
	 preparedStatement.setString(2, password);
	
	 ResultSet res=preparedStatement.executeQuery();
	
	 while(res.next())
	 {
	 	idL=res.getInt("idLogin");
	 }
	 res.close();
	}
	 catch(SQLException se){se.printStackTrace();}
	 catch(Exception e){e.printStackTrace();}
	 finally{}
	 return idL;
} 
  
public void update(int idLogin,String username){
	try
	{
     conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
     String up="update `login` set login.username=? where idLogin=?";
     preparedStatement=conexiune.prepareStatement(up);
     preparedStatement.setString(1, username);
     preparedStatement.setInt(2,idLogin);
     preparedStatement.executeUpdate();
	}
	catch(SQLException se){se.printStackTrace();}
	catch(Exception e){ e.printStackTrace();}
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
 
public void stergere(int id){
	try
	{
	 conexiune = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
	 String del="delete from `login` where idLogin=?";
	 preparedStatement=conexiune.prepareStatement(del);
	 preparedStatement.setInt(1, id);
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
	      catch(SQLException se){ se.printStackTrace();}
	     
	        }
}

}
