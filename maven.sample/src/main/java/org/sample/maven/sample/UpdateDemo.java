package org.sample.maven.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDemo {

	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		// TODO Auto-generated method stub
        String password="fatimagmail";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","Prati123");
        
        PreparedStatement ps = con.prepareStatement("delete from register where password=?");
        
        ps.setString(1, password);
        int count = ps.executeUpdate();
		if(count >0)
        {System.out.println("deletion success");
        }
        else
        {System.out.println("deletion failed");
        }
		
	}

}
