package org.sample.maven.sample;


	
import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;

		public class InsertDemo {

			

			public static void main(String[] args) 
			{
				// TODO Auto-generated method stub
				  try
				    {
					  
				    	 
					    String driver = "com.mysql.cj.jdbc.Driver";
					    String url = "jdbc:mysql://localhost:3306/jdbc_db";
					    String username = "root";
					    String password = "Prati123";
					    
					    
					    Class.forName(driver);
					    
					    Connection con = DriverManager.getConnection(url, username, password);
					    System.out.println("Successfull");
					    
					    PreparedStatement ps =    con.prepareStatement("insert into register values('Prati','Pratigmail','Prati123','female')");
				    	int i = ps.executeUpdate();
				    }catch(Exception e) {
				    	e.printStackTrace();
				    }

			}

			}


