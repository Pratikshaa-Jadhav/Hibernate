package org.sample.maven.sample;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertDemo2

{
	
	public static void addPassenger(PreparedStatement ps,int id,String name,String email,String phonenumber)
	{
		try {
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phonenumber);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void deletePassenger(PreparedStatement ps,int id)
	{
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public static void main( String[] args ) throws Exception
    {
    	try (Scanner scanner = new Scanner(System.in)) {
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/jdbc_db","root","Prati123");  
				
				PreparedStatement pStatement=con.prepareStatement("insert into Passenger values(?,?,?,?)");
				
				System.out.println("Enter passenger1 Id");
				
				int pid=scanner.nextInt();
				System.out.println("Enter the passenger1 name");
				String pname=scanner.next();
				
				System.out.println("Enter the passenger1 email");
				String pmail=scanner.next();
				
				System.out.println("Enter the passenger1 phonenumber");
				String pphonenumber=scanner.next();
				addPassenger(pStatement,pid,pname,pmail,pphonenumber);
				
				System.out.println("Enter Passenger Id");
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
    }
	
	
}   