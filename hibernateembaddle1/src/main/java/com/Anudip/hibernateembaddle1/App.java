package com.Anudip.hibernateembaddle1;

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
    	Configuration con1=new Configuration().configure().addAnnotatedClass(User1.class);
        SessionFactory sessionFactory=con1.buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t1=session.beginTransaction();
        User1 u1 = new User1();
        u1.setUname("pinkey");
        u1.setEmail("pinkey@gmail.com");
        Address1 ad1=new Address1();
        ad1.setAddress1("C/68");
        ad1.setAddress2("1298,nagar palika");
        ad1.setCity("Solapur");
        ad1.setPincode("413217");
        u1.setAddress(ad1);
        session.persist(u1);
        t1.commit();
        System.out.println("hello worlds");
    }
}
       