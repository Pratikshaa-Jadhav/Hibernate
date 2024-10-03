package com.Anudip.hibernatejunittesting;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	   Configuration con1=new Configuration().configure().addAnnotatedClass(Student3.class);
           SessionFactory sessionFactory=con1.buildSessionFactory();
           Session session=sessionFactory.openSession();
           Transaction t1=session.beginTransaction();
           Student3 s1=new Student3();
           s1.setSid(1001);
           s1.setSname("Masood");
           s1.setEmail("Masood@gmail.com");
           s1.setScontact("7890546733");
           session.save(s1);
           t1.commit();
    }
}
