package com.Anudip.hibernatejunittesting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest 

{
	 private static SessionFactory sessionFactory;
	    private Session session;
	     
	    @BeforeAll
	    public static void setup() {
	    	Configuration con1=new Configuration().configure().addAnnotatedClass(Student3.class);
	    	
	    	 sessionFactory=con1.buildSessionFactory();
	     //   sessionFactory = HibernateUtil.getSessionFactory();
	        System.out.println("SessionFactory created");
	    }
	     
	    @AfterAll
	    public static void tearDown() {
	        if (sessionFactory != null) 
	        	sessionFactory.close();
	        System.out.println("SessionFactory destroyed");
	    }
	     
	    @Test
	    public void testCreate() {
	    	System.out.println("Running testCreate...");
	    	Transaction t1=session.beginTransaction();
	    	Student3 s1=new Student3(101,"Masood","7890546733","Masood@gmail.com");
	    	 Integer id = (Integer)session.save(s1);
	    	t1.commit();
	    	Assertions.assertTrue(id>0);
	        
	       
	    }
	     
	    @Test
	    public void testUpdate() {
	    }
	     
	    @Test
	    public void testGet() {
	    	 System.out.println("Running testGet...");
    	 session.beginTransaction();
        Student3 student1 = new Student3(102,"Keya","9898411212","keya@gmail.com");
	        session.save(student1);
	       
	         
	        session.getTransaction().commit();
		         
   	    Integer id = 102;
    	     
    	    Student3 st = session.get(Student3.class, id);
    	     
	    	    assertEquals("Keya", st.getSname()); 
	    }
	     
	   
	     
	    @Test
	    public void testDelete() {
	    }  
	     
	    @BeforeEach
	    public void openSession() {
	        session = sessionFactory.openSession();
	        System.out.println("Session created");
	    }
	     
	    @AfterEach
	    public void closeSession() {
	        if (session != null) session.close();
	        System.out.println("Session closed\n");
	    }  


        @Test
	    public void testUpdate1() {
	    	session.beginTransaction();
    	    Integer id = 102;
    	     
    	    Student3 st = session.get(Student3.class, id);
    	    st.setSname("Masood");
    	    session.save(st);
    	    session.getTransaction().commit();
    	    Integer id1 = 102;
   	     
    	    Student3 st1 = session.get(Student3.class, id1);
    	    assertEquals("Masood", st1.getSname()); 
	    }
}