package com.Anudip.hibernateone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class App 
{
    public static void main( String[] args )
    {
      Configuration conf=new Configuration().configure().addAnnotatedClass(Student1.class).addAnnotatedClass(Laptop1.class);
      SessionFactory sFactory=conf.buildSessionFactory();
      Session session=sFactory.openSession();
      Transaction t1=session.beginTransaction();
      Student1 s1=new Student1();
      s1.setSid(1);
      s1.setSname("Aiman");
      s1.setContact("9099011122");
      Laptop1 l1=new Laptop1();
      l1.setLid(101);
      l1.setBrand("Dell");
      l1.setPrice(50000);
      s1.setLaptop(l1);
      session.persist(s1);
      t1.commit();
      
    }
}